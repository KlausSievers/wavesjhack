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

  private Asset amountAsset;
  private Asset priceAsset;

  public AssetPair() {
  }

  public AssetPair(Asset amountAsset, Asset priceAsset) {
    this.amountAsset = amountAsset;
    this.priceAsset = priceAsset;
  }

  public boolean useAmountAsset() {
    return amountAsset != Asset.WAVES;
  }

  public boolean usePriceAsset() {
    return priceAsset != Asset.WAVES;
  }

  public Asset getAmountAsset() {
    return amountAsset;
  }

  public Asset getPriceAsset() {
    return priceAsset;
  }
  
}
