/*
 * Copyright (c) 2017 Hochschule Ruhr West (HRW), Bottrop, Germany
 *
 *           - ALL RIGHTS RESERVED -
 *
 * project:  WavesJHacker
 *
 * file:     Signature.java 
 *
 * created:  07.12.2017
 *
 * author:   KS, AS
 */
package de.hrw.waves.wavesjhacker.waves.security;

import com.wavesplatform.wavesj.Base58;
import com.wavesplatform.wavesj.PrivateKeyAccount;
import java.nio.ByteBuffer;
import org.whispersystems.curve25519.Curve25519;

public class Signature {

  private static final Curve25519 cipher = Curve25519.getInstance(Curve25519.BEST);

  public static String sign(PrivateKeyAccount account, ByteBuffer buffer) {
    byte[] bytesToSign = new byte[buffer.position()];
    buffer.position(0);
    buffer.get(bytesToSign);
    byte[] signature = cipher.calculateSignature(account.getPrivateKey(), bytesToSign);
    return Base58.encode(signature);
  }
}
