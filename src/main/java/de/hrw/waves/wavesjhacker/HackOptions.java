/*
 * Copyright (c) 2017 Hochschule Ruhr West (HRW), Bottrop, Germany
 *
 *           - ALL RIGHTS RESERVED -
 *
 * project:  WavesJHacker
 *
 * file:     HackOptions.java 
 *
 * created:  Dec 14, 2017
 *
 * author:   KS, AS
 */
package de.hrw.waves.wavesjhacker;

import lombok.Data;

@Data
public class HackOptions {

  private final String assetId;
  private final String matcherPrivateKey;
  private final String myPrivateKey;
/**
 * 
 * @param assetId assetId that should be observed
 * @param matcherPrivateKey the matcher that should sign the forerrunned transactions
 * @param myPrivateKey my private key to sign the fake sell or buy order
 */
  public HackOptions(String assetId, String matcherPrivateKey, String myPrivateKey) {
    this.assetId = assetId;
    this.matcherPrivateKey = matcherPrivateKey;
    this.myPrivateKey = myPrivateKey;
  }

}
