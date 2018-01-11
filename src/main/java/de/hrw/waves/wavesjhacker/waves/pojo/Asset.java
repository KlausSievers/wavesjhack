/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hrw.waves.wavesjhacker.waves.pojo;

/**
 *
 * @author Klaus
 */

public enum Asset {
  WAVES("WAVES", "WAVES", null),
  BTC("BTC", "Bitcoin", "8LQW8f7P5d5PZM7GtZEBgaqRPGSzS3DfPuiXrURJ4AJS");

  private String symbol;
  private String name;
  private String assetId;

  private Asset(String symbol, String name, String assetId) {
    this.symbol = symbol;
    this.name = name;
    this.assetId = assetId;
  }

  public String getSymbol() {
    return symbol;
  }

  public String getName() {
    return name;
  }

  public String getAssetId() {
    return assetId;
  }
  
  

}
