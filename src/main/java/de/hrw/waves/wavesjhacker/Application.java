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

import java.net.URI;
import java.net.URISyntaxException;
import com.wavesplatform.wavesj.Account;
import com.wavesplatform.wavesj.PrivateKeyAccount;
import de.hrw.waves.wavesjhacker.waves.pojo.AssetPair;
import de.hrw.waves.wavesjhacker.waves.pojo.Node;
import de.hrw.waves.wavesjhacker.waves.pojo.Order;
import de.hrw.waves.wavesjhacker.waves.pojo.OrderType;
import de.hrw.waves.wavesjhacker.waves.pojo.transactions.ExchangeTranscation;
import de.hrw.waves.wavesjhacker.waves.pojo.transactions.Transaction;
import de.hrw.waves.wavesjhacker.websocket.UnconfirmedExchangeTxListener;
import de.hrw.waves.wavesjhacker.websocket.WavesMessageHandler;
import de.hrw.waves.wavesjhacker.websocket.WebsocketClientEndpoint;
import de.hrw.waves.wavesjhacker.websocket.pojo.WavesWsMessage;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {

  private static long HOUR = 3_600_000;

  public static void main(String[] args) throws URISyntaxException {
    initWsConnection();

    sendTestExchangeTx();
  }

  private static void initWsConnection() throws URISyntaxException {
    URI uri = new URI("ws://ws.wavesplatform.com/api");
    WavesMessageHandler wavesMessageHandler = new WavesMessageHandler();
    wavesMessageHandler.addUtxListener(new UnconfirmedExchangeTxListener());
    
    final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(uri, wavesMessageHandler);
    clientEndPoint.sendMessage(WavesWsMessage.SUBSCRIBE_UTX);
  }

  private static void sendTestExchangeTx() {
    PrivateKeyAccount klaus = new PrivateKeyAccount(
            "evidence unit market inject swamp quote just know control equal file avoid metal scout video", 0, '0');
    PrivateKeyAccount andree = new PrivateKeyAccount(
            "pistol assist festival craft library force orphan amateur bullet scissors abstract among leisure hamster model", 0, '0');

    Date now = new Date();

    Order order = new Order();
    order.setOrderType(OrderType.BUY);
    order.setAmount(1);
    order.setPrice(1);
    order.setMatcherFee(1);
    order.setSenderKey(klaus.getAddress());
    order.setMatcherFee(1);
    order.setMatcherKey("12345"); //???
    order.setExpiration(now.getTime() + HOUR);
    order.setTimestamp(now.getTime());
    order.setAssetPair(new AssetPair("", ""));
    order.updateSignature(klaus);

    Order order2 = new Order();
    order2.setOrderType(OrderType.BUY);
    order2.setAmount(1);
    order2.setPrice(1);
    order2.setMatcherFee(1);
    order2.setSenderKey(andree.getAddress());
    order2.setMatcherFee(1);
    order2.setMatcherKey("12345"); //???
    order2.setExpiration(now.getTime() + HOUR);
    order2.setTimestamp(now.getTime());
    order2.setAssetPair(new AssetPair("", ""));
    order2.updateSignature(andree);

    Transaction testTx = new ExchangeTranscation(order, order2, 1, 1, 1);
    testTx.updateSignature(klaus);

    Node node = new Node();
    try {
      String response = node.send(testTx);
      Logger.getLogger(Application.class.getName()).log(Level.WARNING, response);
    } catch (IOException ex) {
      Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
