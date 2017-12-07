/*
 * Copyright (c) 2017 Hochschule Ruhr West (HRW), Bottrop, Germany
 *
 *           - ALL RIGHTS RESERVED -
 *
 * project:  WavesJHacker
 *
 * file:     Transaction.java 
 *
 * created:  Dec 7, 2017
 *
 * author:   KS, AS
 */
package de.hrw.waves.wavesjhacker.waves.pojo;

import java.util.Objects;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Transaction {

  private int type;
  private String id;
  private String sender;
  private String senderPublicKey;
  private long fee;
  private long timestamp;
  private String signature;
}
