/*
 * Copyright (c) 2018 Hochschule Ruhr West (HRW), Bottrop, Germany
 *
 *           - ALL RIGHTS RESERVED -
 *
 * project:  WavesJHacker
 *
 * file:     DexOrder.java 
 *
 * created:  17.01.2018
 *
 * author:   KS, AS
 */
package de.hrw.waves.wavesjhacker;

import com.wavesplatform.wavesj.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class DexOrder {

  public static void main(String[] args) throws URISyntaxException, IOException {
    Node matcher = new Node("http://127.0.0.1:6869");
    String matcherKey = matcher.getMatcherKey();

    String seed = "evidence unit market inject swamp quote just know control equal file avoid metal scout video";
    PrivateKeyAccount klaus = PrivateKeyAccount.fromSeed(seed, 0, Account.TESTNET);

    final String WBTC = "Fmg13HEHJHuZYbtJq8Da8wifJENq8uBxDuWoP9pVe2Qe";

    // Learn address balance
    System.out.println("Alice's balance: " + matcher.getBalance(klaus.getAddress()));
    // Same, with the specified number of confirmations
    System.out.println("With 100 confirmations: " + matcher.getBalance(klaus.getAddress(), 100));
    // How much WBTC does Alice have?
    System.out.println("Alice's WBTC balance: " + matcher.getBalance(klaus.getAddress(), WBTC));

   
    AssetPair market = new AssetPair(Asset.WAVES, WBTC);
    Order order = matcher.createOrder(klaus, matcherKey,
            new com.wavesplatform.wavesj.AssetPair("WAVES", WBTC),
            // buy 10 WAVES at 0.00090000 WBTC each
            Order.Type.SELL, 50_000, 10 * 100_000_000L,
            // make order valid for 1 hour
            System.currentTimeMillis() + 3_600_000, 300_000);
    String orderId = order.id;
    System.out.printf("Filed order %s to %s %d WAVES at %.8f\n",
            order.id, order.type, order.amount / Asset.TOKEN, ((float) order.price) / Asset.TOKEN);
    
    System.out.println(matcher.getOrderStatus(orderId, market));
    
    List<Order> orders = matcher.getOrders(klaus, new com.wavesplatform.wavesj.AssetPair("WAVES", WBTC));
    
    System.out.println("Anz. Order " + orders.size());
  }

}
