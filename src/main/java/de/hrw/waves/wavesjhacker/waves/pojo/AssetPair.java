/*
 * Copyright (c) 2017 Hochschule Ruhr West (HRW), Bottrop, Germany
 *
 *           - ALL RIGHTS RESERVED -
 *
 * project:  WavesJHacker
 *
 * file:     AssetPair.java 
 *
 * created:  07.12.2017
 *
 * author:   KS, AS
 */
package de.hrw.waves.wavesjhacker.waves.pojo;

import lombok.Data;

@Data
public class AssetPair {

  private String amountAsset;
  private String priceAsset;

  public AssetPair() {
  }

  public AssetPair(String amountAsset, String priceAsset) {
    this.amountAsset = amountAsset;
    this.priceAsset = priceAsset;
  }

  public boolean useAmountAsset() {
    return amountAsset != null && amountAsset.length() > 0;
  }

  public boolean usePriceAsset() {
    return priceAsset != null && priceAsset.length() > 0;
  }
}
