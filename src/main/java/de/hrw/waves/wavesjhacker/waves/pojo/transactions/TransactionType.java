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
  GENESIS(1),
  PAYMENT(2),
  ISSUE(3),
  TRANSFER(4),
  REISSUE(5),
  BURN(6),
  EXCHANGE(7),
  LEASE(8),
  LEASE_CANCEL(9),
  ALIAS(10);

  private final byte type;

  private TransactionType(int type) {
    this.type = (byte) type;
  }
  
  public byte getType() {
    return type;
  }
}
