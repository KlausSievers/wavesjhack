/*
 * Copyright (c) 2017 Hochschule Ruhr West (HRW), Bottrop, Germany
 *
 *           - ALL RIGHTS RESERVED -
 *
 * project:  WavesJHacker
 *
 * file:     Transaction.java 
 *
 * created:  07.12.2017
 *
 * author:   KS, AS
 */
package de.hrw.waves.wavesjhacker.waves.pojo.transactions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wavesplatform.wavesj.PrivateKeyAccount;
import de.hrw.waves.wavesjhacker.waves.BytesSerializer;
import de.hrw.waves.wavesjhacker.waves.pojo.Signable;
import de.hrw.waves.wavesjhacker.waves.security.Signature;

public abstract class Transaction implements Signable {

  @JsonIgnore
  private TransactionType transactionType;

  @JsonProperty("senderPublicKey")
  @JsonSerialize(using = BytesSerializer.class)
  private byte[] senderKey;

  private long timestamp = System.currentTimeMillis();
  @JsonSerialize(using = BytesSerializer.class)
  private byte[] signature;

  public Transaction(TransactionType type) {
    this.transactionType = type;
  }

  public void updateSignature(PrivateKeyAccount account) {
    senderKey = account.getPublicKey();
    signature = Signature.sign(account, getDataToSign());
  }

  public TransactionType getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(TransactionType transactionType) {
    this.transactionType = transactionType;
  }

  public byte[] getSenderKey() {
    return senderKey;
  }

  public void setSenderKey(byte[] senderKey) {
    this.senderKey = senderKey;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public byte[] getSignature() {
    return signature;
  }

  public void setSignature(byte[] signature) {
    this.signature = signature;
  }
  
  
}
