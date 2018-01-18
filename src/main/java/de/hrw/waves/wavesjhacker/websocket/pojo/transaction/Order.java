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
package de.hrw.waves.wavesjhacker.websocket.pojo.transaction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavesplatform.wavesj.PrivateKeyAccount;
import de.hrw.waves.wavesjhacker.waves.pojo.AssetPair;
import de.hrw.waves.wavesjhacker.waves.pojo.OrderType;
import de.hrw.waves.wavesjhacker.waves.security.Signature;
import java.nio.ByteBuffer;

public class Order  {

  private String id;
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

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getSenderKey() {
    return senderKey;
  }

  public void setSenderKey(String senderKey) {
    this.senderKey = senderKey;
  }

  public String getMatcherKey() {
    return matcherKey;
  }

  public void setMatcherKey(String matcherKey) {
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

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }
  
  
}
