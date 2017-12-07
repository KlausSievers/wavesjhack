/*
 * Copyright (c) 2017 Hochschule Ruhr West (HRW), Bottrop, Germany
 *
 *           - ALL RIGHTS RESERVED -
 *
 * project:  WavesJHacker
 *
 * file:     OrderType.java 
 *
 * created:  07.12.2017
 *
 * author:   KS, AS
 */
package de.hrw.waves.wavesjhacker.waves.pojo;

public enum OrderType {
  BUY(0), SELL(1);

  private final byte type;

  private OrderType(int type) {
    this.type = (byte) type;
  }

  public byte getType() {
    return type;
  } 
}
