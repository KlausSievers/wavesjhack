/*
 * Copyright (c) 2017 Hochschule Ruhr West (HRW), Bottrop, Germany
 *
 *           - ALL RIGHTS RESERVED -
 *
 * project:  WavesJHacker
 *
 * file:     TransactionType.java 
 *
 * created:  07.12.2017
 *
 * author:   KS, AS
 */
package de.hrw.waves.wavesjhacker.waves.pojo.transactions;

public enum TransactionType {
  GENESIS(1, ""),
  PAYMENT(2, "/assets/broadcast/order"),
  ISSUE(3, "/assets/broadcast/issue"),
  TRANSFER(4, "/assets/broadcast/transfer"),
  REISSUE(5, "/assets/broadcast/reissue"),
  BURN(6, "/assets/broadcast/burn"),
  EXCHANGE(7, "/assets/broadcast/exchange"),
  LEASE(8, ""),
  LEASE_CANCEL(9, ""),
  ALIAS(10, "");

  private final byte type;
  private final String endpoint;

  private TransactionType(int type, String endpoint) {
    this.type = (byte) type;
    this.endpoint = endpoint;
  }
  
  public byte getType() {
    return type;
  }

  public String getEndpoint() {
    return endpoint;
  }
}
