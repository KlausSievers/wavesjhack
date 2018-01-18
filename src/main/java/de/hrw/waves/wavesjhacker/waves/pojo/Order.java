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

  public byte[] getSenderKey() {
    return senderKey;
  }

  public void setSenderKey(byte[] senderKey) {
    this.senderKey = senderKey;
  }

  public byte[] getMatcherKey() {
    return matcherKey;
  }

  public void setMatcherKey(byte[] matcherKey) {
    this.matcherKey = matcherKey;
  }

  public AssetPair getAssetPair() {
    return assetPair;
  }

  public void setAssetPair(AssetPair assetPair) {
    this.assetPair = assetPair;
  }

  public OrderType getOrderType() {
    return orderType;
  }

  public void setOrderType(OrderType orderType) {
    this.orderType = orderType;
  }

  public long getPrice() {
    return price;
  }

  public void setPrice(long price) {
    this.price = price;
  }

  public long getAmount() {
    return amount;
  }

  public void setAmount(long amount) {
    this.amount = amount;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public long getExpiration() {
    return expiration;
  }

  public void setExpiration(long expiration) {
    this.expiration = expiration;
  }

  public long getMatcherFee() {
    return matcherFee;
  }

  public void setMatcherFee(long matcherFee) {
    this.matcherFee = matcherFee;
  }

  public byte[] getSignature() {
    return signature;
  }

  public void setSignature(byte[] signature) {
    this.signature = signature;
  }
  
  

}
