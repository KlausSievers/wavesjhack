/*
 * Copyright (c) 2017 Hochschule Ruhr West (HRW), Bottrop, Germany
 *
 *           - ALL RIGHTS RESERVED -
 *
 * project:  WavesJHacker
 *
 * file:     Application.java
 *
 * created:  07.12.2017
 *
 * author:   KS, AS
 */
package de.hrw.waves.wavesjhacker;

import de.hrw.waves.wavesjhacker.websocket.WavesMessageHandler;
import de.hrw.waves.wavesjhacker.websocket.WebsocketConnector;
import java.net.URI;
import java.net.URISyntaxException;

public class Application {

  public static void main(String[] args) throws URISyntaxException {
    URI uri = new URI("ws://ws.wavesplatform.com/api");
    WebsocketConnector connector = new WebsocketConnector();
    connector.connect(uri, new WavesMessageHandler());
  }
}
