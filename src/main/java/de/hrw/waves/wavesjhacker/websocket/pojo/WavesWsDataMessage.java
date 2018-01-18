/*
 * Copyright (c) 2017 Hochschule Ruhr West (HRW), Bottrop, Germany
 *
 *           - ALL RIGHTS RESERVED -
 *
 * project:  WavesJHacker
 *
 * file:     WavesWsDataMessage.java 
 *
 * created:  Dec 7, 2017
 *
 * author:   KS, AS
 */
package de.hrw.waves.wavesjhacker.websocket.pojo;

import com.fasterxml.jackson.databind.JsonNode;

public class WavesWsDataMessage extends WavesWsMessage {

  private JsonNode msg;
  private String status;

  public JsonNode getMsg() {
    return msg;
  }

  public void setMsg(JsonNode msg) {
    this.msg = msg;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
  
  
}
