/*
 * Copyright (c) 2018 Hochschule Ruhr West (HRW), Bottrop, Germany
 *
 *           - ALL RIGHTS RESERVED -
 *
 * project:  WavesJHacker
 *
 * file:     BytesSerializer.java 
 *
 * created:  Jan 4, 2018
 *
 * author:   KS, AS
 */
package de.hrw.waves.wavesjhacker.waves;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.wavesplatform.wavesj.Base58;
import java.io.IOException;

public class BytesSerializer extends JsonSerializer<byte[]> {

  @Override
  public void serialize(byte[] t, JsonGenerator jg, SerializerProvider sp) throws IOException {
    jg.writeString(Base58.encode(t));
  }

  byte[] toPrimitives(Byte[] oBytes) {
    byte[] bytes = new byte[oBytes.length];

    for (int i = 0; i < oBytes.length; i++) {
      bytes[i] = oBytes[i];
    }

    return bytes;
  }
}
