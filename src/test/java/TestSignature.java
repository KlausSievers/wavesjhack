
import com.wavesplatform.wavesj.Base58;
import de.hrw.waves.wavesjhacker.waves.pojo.AssetPair;
import de.hrw.waves.wavesjhacker.waves.pojo.Order;
import de.hrw.waves.wavesjhacker.waves.pojo.OrderType;



/*
 * Copyright (c) 2018 Hochschule Ruhr West (HRW), Bottrop, Germany
 *
 *           - ALL RIGHTS RESERVED -
 *
 * project:  WavesJHacker
 *
 * file:     TestSignature.java 
 *
 * created:  Jan 4, 2018
 *
 * author:   KS, AS
 */

public class TestSignature {

  public void testOrderSignature() {
   

//             "    \"order1\" : {\n"
//            + "      \"id\" : \"282N9Vkx4TdNWg7hcnGbAYNwEBFXFQZqK7J4pw71tiCU\",\n"
//            + "      \"senderPublicKey\" : \"BLBZ6ror7Nma9LeMkeuY39ufQmgGXrxWt3WLmmefJUnK\",\n"
//            + "      \"matcherPublicKey\" : \"7kPFrHDiGw1rCm7LPszuECwWYL3dMf6iMifLRDJQZMzy\",\n"
//            + "      \"assetPair\" : {\n"
//            + "        \"amountAsset\" : null,\n"
//            + "        \"priceAsset\" : \"8LQW8f7P5d5PZM7GtZEBgaqRPGSzS3DfPuiXrURJ4AJS\"\n"
//            + "      },\n"
//            + "      \"orderType\" : \"buy\",\n"
//            + "      \"price\" : 57189,\n"
//            + "      \"amount\" : 29230000000,\n"
//            + "      \"timestamp\" : 1512651598140,\n"
//            + "      \"expiration\" : 1514379598140,\n"
//            + "      \"matcherFee\" : 300000,\n"
//            + "      \"signature\" : \"4iBjAdtQRfWogjcisbfqHWc99majHaeH4Pm1gL3wM8kjeSNYHeo1fMTCkfQjLpBGpNN5Xdv87TPcEVunkXhGuHYm\"\n"
//            + "    }";
    Order order = new Order();
    order.setOrderType(OrderType.BUY);
    order.setAmount(29230000000L);
    order.setPrice(57189);
    order.setMatcherFee(1);
    order.setSenderKey(Base58.decode("BLBZ6ror7Nma9LeMkeuY39ufQmgGXrxWt3WLmmefJUnK"));
    order.setMatcherFee(1);
    order.setMatcherKey(Base58.decode("7kPFrHDiGw1rCm7LPszuECwWYL3dMf6iMifLRDJQZMzy")); 
    order.setExpiration(1514379598140L);
    order.setTimestamp(1512651598140L);
    order.setAssetPair(new AssetPair(null, "8LQW8f7P5d5PZM7GtZEBgaqRPGSzS3DfPuiXrURJ4AJS"));
   // order.updateSignature(klaus);
  }
}
