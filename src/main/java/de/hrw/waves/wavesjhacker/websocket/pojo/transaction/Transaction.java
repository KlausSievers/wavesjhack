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
package de.hrw.waves.wavesjhacker.websocket.pojo.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Objects;

public class Transaction {

  private int type;
  private String id;
  private String sender;
  private String senderPublicKey;
  private long fee;
  private long timestamp;
  private String signature;

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public String getSenderPublicKey() {
    return senderPublicKey;
  }

  public void setSenderPublicKey(String senderPublicKey) {
    this.senderPublicKey = senderPublicKey;
  }

  public long getFee() {
    return fee;
  }

  public void setFee(long fee) {
    this.fee = fee;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  @Override
  public String toString() {
    return "Transaction{" + "type=" + type + ", id=" + id + ", sender=" + sender + ", senderPublicKey=" + senderPublicKey + ", fee=" + fee + ", timestamp=" + timestamp + ", signature=" + signature + '}';
  }
  
  
  @Override
  public int hashCode() {
    int hash = 7;
    hash = 97 * hash + this.type;
    hash = 97 * hash + Objects.hashCode(this.id);
    hash = 97 * hash + Objects.hashCode(this.sender);
    hash = 97 * hash + Objects.hashCode(this.senderPublicKey);
    hash = 97 * hash + Objects.hashCode(this.signature);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Transaction other = (Transaction) obj;
    if (this.type != other.type) {
      return false;
    }
    if (!Objects.equals(this.id, other.id)) {
      return false;
    }
    if (!Objects.equals(this.sender, other.sender)) {
      return false;
    }
    if (!Objects.equals(this.senderPublicKey, other.senderPublicKey)) {
      return false;
    }
    if (!Objects.equals(this.signature, other.signature)) {
      return false;
    }
    return true;
  }
  
  
  
}
