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

import com.wavesplatform.wavesj.Account;
import com.wavesplatform.wavesj.Base58;
import java.net.URI;
import java.net.URISyntaxException;
import com.wavesplatform.wavesj.PrivateKeyAccount;
import de.hrw.waves.wavesjhacker.waves.pojo.Asset;
import de.hrw.waves.wavesjhacker.waves.pojo.AssetPair;
import de.hrw.waves.wavesjhacker.waves.pojo.Order;
import de.hrw.waves.wavesjhacker.waves.pojo.OrderType;
import de.hrw.waves.wavesjhacker.waves.pojo.transactions.ExchangeTranscation;
import de.hrw.waves.wavesjhacker.waves.pojo.transactions.Transaction;
import de.hrw.waves.wavesjhacker.waves.security.Signature;
import de.hrw.waves.wavesjhacker.websocket.UnconfirmedExchangeTxListener;
import de.hrw.waves.wavesjhacker.websocket.WavesMessageHandler;
import de.hrw.waves.wavesjhacker.websocket.WebsocketClientEndpoint;
import de.hrw.waves.wavesjhacker.websocket.pojo.WavesWsMessage;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

  private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
  private static final long HOUR = 24 * 3_600_000 * 10;

  public static void main(String[] args) throws URISyntaxException {
//    initWsConnection();
//    byte[] bytes = Base58.decode("WA1QuEjJX2b6w1xKGaqyFZwPDN9DMiAoTYVfe2TUyd7UUTmqmTkswJmCWfZJEDANAnPfjENZJqE6HN1t5MVLTWU");
//    System.out.println(bytes.length);
//    System.out.println("Decode: " + String.valueOf(Base58.decode("8LQW8f7P5d5PZM7GtZEBgaqRPGSzS3DfPuiXrURJ4AJS")));
//    sendTestExchangeTx();
    LOGGER.info("Application started");
  }

  private static void initWsConnection() throws URISyntaxException {
    URI uri = new URI("ws://ws.wavesplatform.com/api");
    WavesMessageHandler wavesMessageHandler = new WavesMessageHandler();
    wavesMessageHandler.addUtxListener(new UnconfirmedExchangeTxListener());

    final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(uri, wavesMessageHandler);
    clientEndPoint.sendMessage(WavesWsMessage.SUBSCRIBE_UTX);
  }

  private static void sendTestExchangeTx() {
    PrivateKeyAccount matcher = PrivateKeyAccount.fromSeed(
            "pistol assist festival craft library force orphan amateur bullet scissors abstract among leisure hamster model", 0,
            Account.TESTNET);
    PrivateKeyAccount klaus = PrivateKeyAccount.fromSeed(
            "evidence unit market inject swamp quote just know control equal file avoid metal scout video", 0, Account.TESTNET);
    PrivateKeyAccount andree = PrivateKeyAccount.fromSeed(
            "pistol assist festival craft library force orphan amateur bullet scissors abstract among leisure hamster model", 0,
            Account.TESTNET);

    Date now = new Date();

    Order order = new Order();
    order.setOrderType(OrderType.BUY);
    order.setAmount(100000000);
    order.setPrice(1);
    order.setMatcherFee(1);
    order.setSenderKey(klaus.getPublicKey());
    order.setMatcherKey(matcher.getPublicKey());
    order.setExpiration(now.getTime() + HOUR);
    order.setTimestamp(now.getTime());
    order.setAssetPair(new AssetPair(Asset.WAVES, Asset.BTC));
    order.updateSignature(klaus);

    Order order2 = new Order();
    order2.setOrderType(OrderType.SELL);
    order2.setAmount(100000000);
    order2.setPrice(1);
    order2.setMatcherFee(1);
    order2.setSenderKey(andree.getPublicKey());
    order2.setMatcherKey(matcher.getPublicKey());
    order2.setExpiration(now.getTime() + HOUR);
    order2.setTimestamp(now.getTime());
    order2.setAssetPair(new AssetPair(Asset.WAVES, Asset.BTC));
    order2.updateSignature(andree);

    Transaction testTx = new ExchangeTranscation(order, order2, 1, 100000000, 300000);
    testTx.updateSignature(matcher);

    boolean b = Signature.cipher.verifySignature(matcher.getPublicKey(), testTx.getDataToSign().array(), testTx.getSignature());
    System.out.println("OUR FUCKING TEST" + b);

//    Node node = new Node();
//    try {
//      String response = node.send(testTx);
//      Logger.getLogger(Application.class.getName()).log(Level.WARNING, response);
//    } catch (IOException ex) {
//      Logger.getLogger(Application.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
//    }
  }
}
