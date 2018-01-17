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
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wavesplatform.wavesj.Base58;
import com.wavesplatform.wavesj.PrivateKeyAccount;
import de.hrw.waves.wavesjhacker.waves.BytesSerializer;
import de.hrw.waves.wavesjhacker.waves.security.Signature;
import java.nio.ByteBuffer;
import lombok.Data;

@Data
public class Order implements Signable {

  @JsonProperty("senderPublicKey")
  @JsonSerialize(using = BytesSerializer.class)
  private byte[] senderKey;

  @JsonProperty("matcherPublicKey")
  @JsonSerialize(using = BytesSerializer.class)
  private byte[] matcherKey;

  private AssetPair assetPair;

  private OrderType orderType;
  private long price;
  private long amount;
  private long timestamp;
  private long expiration;
  private long matcherFee;
  @JsonSerialize(using = BytesSerializer.class)
  private byte[] signature;

  //todo order should hold the account. public key is memer and here private key is needed
  public void updateSignature(PrivateKeyAccount account) {
    signature = Signature.sign(account, getDataToSign());
  }

  @Override
  @JsonIgnore
  public ByteBuffer getDataToSign() {
    ByteBuffer buffer = ByteBuffer.allocate(getBufferSize());
    buffer.put(senderKey);
    buffer.put(matcherKey);

    buffer.put(convertAssetFlagToByte(assetPair.useAmountAsset()));
    if (assetPair.useAmountAsset()) {
      buffer.put(Base58.decode(assetPair.getAmountAsset().getAssetId()));
    }

    buffer.put(convertAssetFlagToByte(assetPair.usePriceAsset()));
    if (assetPair.usePriceAsset()) {
      buffer.put(Base58.decode(assetPair.getPriceAsset().getAssetId()));
    }

    buffer.put(orderType.getType());
    buffer.putLong(price);
    buffer.putLong(amount);
    buffer.putLong(timestamp);
    buffer.putLong(expiration);
    buffer.putLong(matcherFee);

    return buffer;
  }

  @JsonIgnore
  public ByteBuffer toByteBuffer() {
    ByteBuffer toSign = getDataToSign();
    ByteBuffer result = ByteBuffer.allocate(toSign.limit() + 64);
    result.put(toSign.array());
    result.put(signature);

    return result;
  }

  private int getBufferSize() {
    int bufferSize = 107;
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
