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

import lombok.Data;

@Data
public class WavesWsMessage {

  public enum ReceivedType {
    PONG,
    UTX;

    public static ReceivedType cast(String type) {
      if (type != null && !type.isEmpty()) {
        for (ReceivedType value : ReceivedType.values()) {
          if (value.toString().toLowerCase().equals(type.toLowerCase())) {
            return value;
          }
        }
      }
      return null;
    }
  }

  public static final WavesWsMessage PING = new WavesWsMessage("ping");
  public static final WavesWsMessage SUBSCRIBE_UTX = new WavesWsMessage("subscribe utx");

  public WavesWsMessage() {
  }

  public WavesWsMessage(String op) {
    this.op = op;
  }
  private String op;
}
