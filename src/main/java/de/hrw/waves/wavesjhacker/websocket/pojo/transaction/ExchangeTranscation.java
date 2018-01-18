/*
 * Copyright (c) 2017 Hochschule Ruhr West (HRW), Bottrop, Germany
 *
 *           - ALL RIGHTS RESERVED -
 *
 * project:  WavesJHacker
 *
 * file:     ExchangeTranscation.java 
 *
 * created:  07.12.2017
 *
 * author:   KS, AS
 */
package de.hrw.waves.wavesjhacker.websocket.pojo.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExchangeTranscation extends Transaction {

  @JsonProperty("order1")
  private Order buyOrder;

  @JsonProperty("order2")
  private Order sellOrder;

  private long price;
  private long amount;
  private long fee;

  private long buyMatcherFee;
  private long sellMatcherFee;

  public ExchangeTranscation() {
  }

  public ExchangeTranscation(Order buyOrder, Order sellOrder, long price, long amount, long fee) {
    this.buyOrder = buyOrder;
    this.sellOrder = sellOrder;
    this.price = price;
    this.amount = amount;
    this.fee = fee;
  }

  public Order getBuyOrder() {
    return buyOrder;
  }

  public void setBuyOrder(Order buyOrder) {
    this.buyOrder = buyOrder;
  }

  public Order getSellOrder() {
    return sellOrder;
  }

  public void setSellOrder(Order sellOrder) {
    this.sellOrder = sellOrder;
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

  public long getFee() {
    return fee;
  }

  public void setFee(long fee) {
    this.fee = fee;
  }

  public long getBuyMatcherFee() {
    return buyMatcherFee;
  }

  public void setBuyMatcherFee(long buyMatcherFee) {
    this.buyMatcherFee = buyMatcherFee;
  }

  public long getSellMatcherFee() {
    return sellMatcherFee;
  }

  public void setSellMatcherFee(long sellMatcherFee) {
    this.sellMatcherFee = sellMatcherFee;
  }

  @Override
  public String toString() {
    return super.toString() + " ExchangeTranscation{" + "buyOrder=" + buyOrder + ", sellOrder=" + sellOrder + ", price=" + price + ", amount=" + amount + ", fee=" + fee + ", buyMatcherFee=" + buyMatcherFee + ", sellMatcherFee=" + sellMatcherFee + '}';
  }
  
  
}
