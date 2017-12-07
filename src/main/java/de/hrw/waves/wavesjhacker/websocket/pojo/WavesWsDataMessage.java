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
import lombok.Data;

@Data
public class WavesWsDataMessage extends WavesWsMessage {

  private JsonNode msg;
  private String status;
}
