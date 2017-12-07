/*
 * Copyright (c) 2017 Hochschule Ruhr West (HRW), Bottrop, Germany
 *
 *           - ALL RIGHTS RESERVED -
 *
 * project:  WavesJHacker
 *
 * file:     WebsocketConnector.java 
 *
 * created:  Dec 7, 2017
 *
 * author:   KS, AS
 */
package de.hrw.waves.wavesjhacker.websocket;

import java.net.URI;

public class WebsocketConnector {

  public void connect(URI uri, MessageHandler handler) {
    try {
      // open websocket
      final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(uri);

      // add listener
      clientEndPoint.addMessageHandler(handler);

      // send message to websocket
      clientEndPoint.sendMessage("{'event':'addChannel','channel':'ok_btccny_ticker'}");

      // wait 5 seconds for messages from websocket
      Thread.sleep(5000);

    } catch (InterruptedException ex) {
      System.err.println("InterruptedException exception: " + ex.getMessage());
    }
  }
}
