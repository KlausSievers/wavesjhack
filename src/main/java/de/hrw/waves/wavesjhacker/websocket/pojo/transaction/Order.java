/*
 * Copyright (c) 2017 Hochschule Ruhr West (HRW), Bottrop, Germany
 *
 *           - ALL RIGHTS RESERVED -
 *
 * project:  WavesJHacker
 *
 * file:     Order.java 
 *
 * created:  07.12.2017
 *
 * author:   KS, AS
 */
package de.hrw.waves.wavesjhacker.websocket.pojo.transaction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wavesplatform.wavesj.PrivateKeyAccount;
import de.hrw.waves.wavesjhacker.waves.pojo.AssetPair;
import de.hrw.waves.wavesjhacker.waves.pojo.OrderType;
import de.hrw.waves.wavesjhacker.waves.security.Signature;
import java.nio.ByteBuffer;
import lombok.Data;

@Data
public class Order  {

  private String id;
  @JsonProperty("senderPublicKey")
  private String senderKey;

  @JsonProperty("matcherPublicKey")
  private String matcherKey;

  private AssetPair assetPair;

  private OrderType orderType;
  private long price;
  private long amount;
  private long timestamp;
  private long expiration;
  private long matcherFee;
  private String signature;
}
