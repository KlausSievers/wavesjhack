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

import com.wavesplatform.wavesj.Base58;
import com.wavesplatform.wavesj.PrivateKeyAccount;
import de.hrw.waves.wavesjhacker.waves.pojo.Signable;
import de.hrw.waves.wavesjhacker.waves.security.Signature;
import java.nio.ByteBuffer;
import lombok.Data;

@Data
public abstract class Transaction implements Signable{

  private TransactionType transactionType;
  private long timestamp;
  private String signature;

  public Transaction(TransactionType type) {
    this.transactionType = type;
  }

  public void  updateSignature(PrivateKeyAccount account) {
    signature = Signature.sign(account, getDataToSign());
  }
}
