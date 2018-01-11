
import com.wavesplatform.wavesj.Base58;
import com.wavesplatform.wavesj.Order;
import com.wavesplatform.wavesj.PrivateKeyAccount;
import com.wavesplatform.wavesj.Transaction;
import de.hrw.waves.wavesjhacker.waves.pojo.Asset;
import de.hrw.waves.wavesjhacker.waves.pojo.AssetPair;
import de.hrw.waves.wavesjhacker.waves.pojo.OrderType;
import de.hrw.waves.wavesjhacker.waves.security.Signature;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

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

  private static final int MIN_BUFFER_SIZE = 120;

  @Test
  public void testOrderSignature() {
    long timestamp = 1512651598140L;
    long expiration = 1514379598140L;
    long matcherFee = 300000;
    long amount = 29230000000L;
    long price = 57189;
    Asset assetPrice = Asset.BTC;
    String matcherKey = "7kPFrHDiGw1rCm7LPszuECwWYL3dMf6iMifLRDJQZMzy";
    PrivateKeyAccount klaus = new PrivateKeyAccount(
            "evidence unit market inject swamp quote just know control equal file avoid metal scout video", 0, '0');
    de.hrw.waves.wavesjhacker.waves.pojo.Order order = new de.hrw.waves.wavesjhacker.waves.pojo.Order();
    order.setOrderType(OrderType.BUY);
    order.setAmount(amount);
    order.setPrice(price);
    order.setMatcherFee(matcherFee);
    order.setSenderKey(klaus.getPublicKey());
    order.setMatcherKey(Base58.decode(matcherKey));
    order.setExpiration(expiration);
    order.setTimestamp(timestamp);
    order.setAssetPair(new AssetPair(Asset.WAVES, assetPrice));
    ByteBuffer toSign = order.getDataToSign();
    

    ByteBuffer expectedToSign = makeOrderTx(klaus, matcherKey, Order.Type.BUY, 
            null, assetPrice.getAssetId(), price, amount, expiration, timestamp, matcherFee);
    
    order.updateSignature(klaus);
    //System.out.println(Arrays.toString(expectedToSign.array()));
    //System.out.println(Arrays.toString(toSign.array()));
    
    Assert.assertArrayEquals(expectedToSign.array(), toSign.array());
    
    //byte[] expectedSign = Signature.sign(klaus, expectedToSign);
    //System.out.println(Arrays.toString(expectedSign));
    //System.out.println(Arrays.toString(order.getSignature()));
//    Assert.assertArrayEquals(expectedSign, order.getSignature());
  }

  public static ByteBuffer makeOrderTx(PrivateKeyAccount sender, String matcherKey, Order.Type orderType,
          String amountAssetId, String priceAssetId, long price, long amount, long expiration, long timestamp, long matcherFee) {
    int datalen = MIN_BUFFER_SIZE
            + (amountAssetId == null ? 0 : 32)
            + (priceAssetId == null ? 0 : 32);
    if (datalen == MIN_BUFFER_SIZE) {
      throw new IllegalArgumentException("Both spendAsset and receiveAsset are WAVES");
    }
    ByteBuffer buf = ByteBuffer.allocate(datalen);
    buf.put(sender.getPublicKey()).put(Base58.decode(matcherKey));
    putAsset(buf, amountAssetId);
    putAsset(buf, priceAssetId);
    buf.put((byte) orderType.ordinal()).putLong(price).putLong(amount)
            .putLong(timestamp).putLong(expiration).putLong(matcherFee);
    return buf;
  }

  private static void putAsset(ByteBuffer buffer, String assetId) {
    if (assetId == null || assetId.isEmpty()) {
      buffer.put((byte) 0);
    } else {
      buffer.put((byte) 1).put(Base58.decode(assetId));
    }
  }

  private static Map<String, String> assetPair(String amountAssetId, String priceAssetId) {
    Map<String, String> assetPair = new HashMap<>();
    assetPair.put("amountAsset", amountAssetId);
    assetPair.put("priceAsset", priceAssetId);
    return assetPair;
  }
}
