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

import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderType {
  BUY(0, "buy"), SELL(1, "sell");

  private final byte type;
  private final String name;

  private OrderType(int type, String name) {
    this.type = (byte) type;
    this.name = name;
  }

  public byte getType() {
    return type;
  } 

  @JsonValue
  public String getName() {
    return name;
  } 
  
}
