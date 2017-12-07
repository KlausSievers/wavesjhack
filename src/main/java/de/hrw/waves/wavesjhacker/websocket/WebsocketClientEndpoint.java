/*
 * Copyright (c) 2017 Hochschule Ruhr West (HRW), Bottrop, Germany
 *
 *           - ALL RIGHTS RESERVED -
 *
 * project:  WavesJHacker
 *
 * file:     WebSocketClientEndpoint.java
 *
 * created:  07.12.2017
 *
 * author:   KS, AS
 */
package de.hrw.waves.wavesjhacker.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.hrw.waves.wavesjhacker.websocket.pojo.WavesWsMessage;
import java.io.IOException;
import java.net.URI;
import java.util.Timer;
import java.util.TimerTask;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ClientEndpoint
public class WebsocketClientEndpoint {

  private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketClientEndpoint.class);

  private final MessageHandler messageHandler;
  private final ObjectMapper mapper = new ObjectMapper();
  private Session userSession = null;
  private Timer timer = new Timer();

  public WebsocketClientEndpoint(URI endpointURI, MessageHandler messageHandler) {
    this.messageHandler = messageHandler;
    try {
      WebSocketContainer container = ContainerProvider.getWebSocketContainer();
      container.connectToServer(this, endpointURI);
    } catch (DeploymentException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Callback hook for Connection open events.
   *
   * @param userSession the userSession which is opened.
   */
  @OnOpen
  public void onOpen(Session userSession) {
    LOGGER.info("Opened webosocket to {}", userSession);
    this.userSession = userSession;
    timer.scheduleAtFixedRate(new Watchdog(this), 0, 30000);
  }

  /**
   * Callback hook for Connection close events.
   *
   * @param userSession the userSession which is getting closed.
   * @param reason the reason for connection close
   */
  @OnClose
  public void onClose(Session userSession, CloseReason reason) {
    LOGGER.info("Closed webosocket to {} because of ", userSession, reason);
    this.userSession = null;
    System.exit(-666);
  }

  /**
   * Callback hook for Message Events. This method will be invoked when a client send a message.
   *
   * @param message The text message
   */
  @OnMessage
  public void onMessage(String message) {
    LOGGER.info("Received message from session:{}, message:{}", userSession.getId(), message);
    this.messageHandler.handleMessage(message);
  }

  public void sendMessage(WavesWsMessage wavesMessage) {
    try {
      String message = mapper.writeValueAsString(wavesMessage);
      LOGGER.info("Send {}", message);
      this.sendMessage(message);
    } catch (JsonProcessingException ex) {
      LOGGER.error("Failed to convert PING to json", ex);
    }
  }

  public void sendMessage(String message) {
    this.userSession.getAsyncRemote().sendText(message);
  }

  private static class Watchdog extends TimerTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(Watchdog.class);
    private final WebsocketClientEndpoint client;

    public Watchdog(WebsocketClientEndpoint client) {
      this.client = client;
    }

    @Override
    public void run() {
      client.sendMessage(WavesWsMessage.PING);
    }

  }
}
