/*
 * Copyright (c) 2017 Hochschule Ruhr West (HRW), Bottrop, Germany
 *
 *           - ALL RIGHTS RESERVED -
 *
 * project:  WavesJHacker
 *
 * file:     Order.java 
 *
 * created:  07.12.2017
 *
 * author:   KS, AS
 */
package de.hrw.waves.wavesjhacker.waves.pojo;

import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Order implements Signable {

  private String senderKey;
  private String matcherKey;
  private boolean useAsset;
  private String asset;
  private boolean usePriceAsset;
  private String priceAsset;
  private OrderType orderType;
  private long price;
  private long amount;
  private long timestamp;
  private long expiration;
  private long matcherFee;
  private String signature;

  @Override
  public ByteBuffer getDataToSign() {
    ByteBuffer buffer = ByteBuffer.allocate(getBufferSize());
    buffer.put(senderKey.getBytes());
    buffer.put(matcherKey.getBytes());
    
    buffer.put(convertAssetFlagToByte(useAsset));
    if (useAsset) {
      buffer.put(asset.getBytes());
    }
    
    buffer.put(convertAssetFlagToByte(usePriceAsset));
    if (usePriceAsset) {
      buffer.put(priceAsset.getBytes());
    }
    
    buffer.put(orderType.getType());
    buffer.putLong(price);
    buffer.putLong(amount);
    buffer.putLong(timestamp);
    buffer.putLong(expiration);
    buffer.putLong(matcherFee);

    return buffer;
  }

  private int getBufferSize() {
    int bufferSize = 99;
    if (useAsset) {
      bufferSize += 32;
    }
    if (usePriceAsset) {
      bufferSize += 32;
    }

    return bufferSize;
  }

  private byte convertAssetFlagToByte(boolean assetFlag) {
    return (byte) (assetFlag ? 1 : 0);
  }

}
