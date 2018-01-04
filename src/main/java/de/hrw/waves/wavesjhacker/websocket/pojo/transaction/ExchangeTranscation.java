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
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
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
}
