/*
 * Copyright (c) 2017 Hochschule Ruhr West (HRW), Bottrop, Germany
 *
 *           - ALL RIGHTS RESERVED -
 *
 * project:  WavesJHacker
 *
 * file:     UTXListener.java 
 *
 * created:  Dec 7, 2017
 *
 * author:   KS, AS
 */
package de.hrw.waves.wavesjhacker.websocket;

import de.hrw.waves.wavesjhacker.websocket.pojo.WavesWsDataMessage;

public interface UnconfirmedTxListener {

  void onUtx(WavesWsDataMessage message);
}
