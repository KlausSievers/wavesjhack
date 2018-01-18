/*
 * Copyright (c) 2017 Hochschule Ruhr West (HRW), Bottrop, Germany
 *
 *           - ALL RIGHTS RESERVED -
 *
 * project:  WavesJHacker
 *
 * file:     WavesWsMessage.java 
 *
 * created:  Dec 7, 2017
 *
 * author:   KS, AS
 */
package de.hrw.waves.wavesjhacker.websocket.pojo;

import com.fasterxml.jackson.annotation.JsonValue;

public class WavesWsMessage {

  public enum Type {
    PING("ping"),
    PONG("pong"),
    UTX("utx"),
    SUBSCRIBE_UTX("subscribe utx");
    private final String name;

    private Type(String name) {
      this.name = name;
    }

    @JsonValue
    public String getName() {
      return name;
    }
  }

  public static final WavesWsMessage PING = new WavesWsMessage(Type.PING);
  public static final WavesWsMessage SUBSCRIBE_UTX = new WavesWsMessage(Type.SUBSCRIBE_UTX);

  public WavesWsMessage() {
  }

  public WavesWsMessage(Type type) {
    this.op = type;
  }

  private Type op;

  public Type getOp() {
    return op;
  }

  public void setOp(Type op) {
    this.op = op;
  }
  
  
}
