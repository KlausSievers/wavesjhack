/*
 * Copyright (c) 2017 Hochschule Ruhr West (HRW), Bottrop, Germany
 *
 *           - ALL RIGHTS RESERVED -
 *
 * project:  WavesJHacker
 *
 * file:     WavesMessageHandler.java 
 *
 * created:  Dec 7, 2017
 *
 * author:   KS, AS
 */
package de.hrw.waves.wavesjhacker.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hrw.waves.wavesjhacker.websocket.pojo.WavesWsDataMessage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WavesMessageHandler implements MessageHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(WavesMessageHandler.class);
  private final ObjectMapper mapper = new ObjectMapper();
  private final List<UnconfirmedTxListener> utxListener = new LinkedList<>();

  @Override
  public void handleMessage(String message) {
    try {
      WavesWsDataMessage wavesMessage = mapper.readValue(message, WavesWsDataMessage.class);
      handle(wavesMessage);
    } catch (IOException ex) {
      LOGGER.error("Failed to parse message {}", message, ex);
    }
  }

  public void addUtxListener(UnconfirmedTxListener listener) {
    utxListener.add(listener);
  }

  private void handle(WavesWsDataMessage message) throws IOException {
    if (message != null) {
      switch (message.getOp()) {
        case PONG:
          handlePong(message);
          break;
        case UTX:
          handleUtx(message);
          break;
        case SUBSCRIBE_UTX:
          handleSubscribeUtx(message);
          break;
        default:
          LOGGER.warn("Type not supported", message);
      }
    } else {
      LOGGER.error("Failed to parse message. It is null", message);
    }
  }

  private void handlePong(WavesWsDataMessage message) {
    LOGGER.info("Received pong", message);
  }

  private void handleUtx(WavesWsDataMessage message) throws IOException {
    LOGGER.info("Received utx", message);
    utxListener.forEach(o -> o.onUtx(message));
  }

  private void handleSubscribeUtx(WavesWsDataMessage message) {
    LOGGER.info("Subscribed {}", message);
  }
}
