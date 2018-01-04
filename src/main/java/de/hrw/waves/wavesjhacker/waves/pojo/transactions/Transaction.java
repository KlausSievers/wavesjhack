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
import lombok.Data;

@Data
public abstract class Transaction implements Signable {

  @JsonIgnore
  private TransactionType transactionType;

  @JsonProperty("senderPublicKey")
  @JsonSerialize(using = BytesSerializer.class)
  private byte[] senderKey;

  private long timestamp;
  private String signature;

  public Transaction(TransactionType type) {
    this.transactionType = type;
  }

  public void updateSignature(PrivateKeyAccount account) {
    senderKey = account.getPublicKey();
    signature = Signature.sign(account, getDataToSign());
  }
}
