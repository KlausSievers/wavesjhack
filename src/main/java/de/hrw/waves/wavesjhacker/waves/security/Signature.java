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

import com.wavesplatform.wavesj.PrivateKeyAccount;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.whispersystems.curve25519.Curve25519;

public class Signature {

  public static final Curve25519 cipher = Curve25519.getInstance(Curve25519.BEST);
/**
 * From WavesJ because it was private!
 * static String sign(PrivateKeyAccount account, ByteBuffer buffer) {
        byte[] bytesToSign = new byte[buffer.position()];
        buffer.position(0);
        buffer.get(bytesToSign);
        byte[] signature = cipher.calculateSignature(account.getPrivateKey(), bytesToSign);
        return Base58.encode(signature);
    }
 * @param account
 * @param buffer
 * @return 
 */
  public static byte[] sign(PrivateKeyAccount account, ByteBuffer buffer) {
    byte[] bytesToSign = new byte[buffer.position()];
    buffer.position(0);
    buffer.get(bytesToSign);
    
    System.out.println("Sign: " + Arrays.toString(bytesToSign));
    System.out.println("Sign pk: " + Arrays.toString(account.getPrivateKey()));
    byte[] signature = cipher.calculateSignature(account.getPrivateKey(), bytesToSign);
    System.out.println("Sign after: " + Arrays.toString(signature));
    return signature;
  }
}
