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
package de.hrw.waves.wavesjhacker.waves.pojo.transactions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.hrw.waves.wavesjhacker.waves.pojo.Order;
import java.nio.ByteBuffer;
import lombok.Data;

@Data
public class ExchangeTranscation extends Transaction {

  @JsonProperty("order1")
  private Order buyOrder;

  @JsonProperty("order2")
  private Order sellOrder;

  private long price;
  private long amount;
  private long fee;

  public ExchangeTranscation(Order buyOrder, Order sellOrder, long price, long amount, long fee) {
    super(TransactionType.EXCHANGE);
    this.buyOrder = buyOrder;
    this.sellOrder = sellOrder;
    this.price = price;
    this.amount = amount;
    this.fee = fee;
  }

  public long getBuyMatcherFee() {
    return buyOrder.getMatcherFee();
  }

  public long getSellMatcherFee() {
    return sellOrder.getMatcherFee();
  }

  @Override
  @JsonIgnore
  public ByteBuffer getDataToSign() {
    byte[] buyOrderData = buyOrder.getDataToSign().array();
    byte[] sellOrderData = sellOrder.getDataToSign().array();

    ByteBuffer buffer = ByteBuffer.allocate(getBufferSize(buyOrderData, sellOrderData));
    buffer.put(getTransactionType().getType());
    buffer.putInt(buyOrderData.length);
    buffer.putInt(sellOrderData.length);
    buffer.put(buyOrder.getDataToSign());
    buffer.put(sellOrder.getDataToSign());
    buffer.putLong(price);
    buffer.putLong(amount);
    buffer.putLong(getBuyMatcherFee());
    buffer.putLong(getSellMatcherFee());
    buffer.putLong(fee);
    buffer.putLong(getTimestamp());

    return buffer;
  }

  private int getBufferSize(byte[] buyOrderData, byte[] sellOrderData) {
    int bufferSize = 57 + buyOrderData.length + sellOrderData.length;

    return bufferSize;
  }

}
