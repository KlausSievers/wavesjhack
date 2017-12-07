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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavesplatform.wavesj.PrivateKeyAccount;
import de.hrw.waves.wavesjhacker.waves.security.Signature;
import java.nio.ByteBuffer;
import lombok.Data;

@Data
public class Order implements Signable {

  @JsonProperty("senderPublicKey")
  private String senderKey;

  @JsonProperty("matcherPublicKey")
  private String matcherKey;

  private AssetPair assetPair;

  private OrderType orderType;
  private long price;
  private long amount;
  private long timestamp;
  private long expiration;
  private long matcherFee;
  private String signature;

  //todo order should hold the account. public key is memer and here private key is needed
  public void updateSignature(PrivateKeyAccount account) {
    signature = Signature.sign(account, getDataToSign());
  }

  @Override
  @JsonIgnore
  public ByteBuffer getDataToSign() {
    ByteBuffer buffer = ByteBuffer.allocate(getBufferSize());
    buffer.put(senderKey.getBytes());
    buffer.put(matcherKey.getBytes());

    buffer.put(convertAssetFlagToByte(assetPair.useAmountAsset()));
    if (assetPair.useAmountAsset()) {
      buffer.put(assetPair.getAmountAsset().getBytes());
    }

    buffer.put(convertAssetFlagToByte(assetPair.useAmountAsset()));
    if (assetPair.usePriceAsset()) {
      buffer.put(assetPair.getPriceAsset().getBytes());
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
    if (assetPair.useAmountAsset()) {
      bufferSize += 32;
    }
    if (assetPair.usePriceAsset()) {
      bufferSize += 32;
    }

    return bufferSize;
  }

  private byte convertAssetFlagToByte(boolean assetFlag) {
    return (byte) (assetFlag ? 1 : 0);
  }

}