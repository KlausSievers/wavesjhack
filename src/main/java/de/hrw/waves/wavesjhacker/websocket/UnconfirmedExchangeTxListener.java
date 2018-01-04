/*
 * Copyright (c) 2017 Hochschule Ruhr West (HRW), Bottrop, Germany
 *
 *           - ALL RIGHTS RESERVED -
 *
 * project:  WavesJHacker
 *
 * file:     UETXListener.java 
 *
 * created:  Dec 7, 2017
 *
 * author:   KS, AS
 */
package de.hrw.waves.wavesjhacker.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.hrw.waves.wavesjhacker.websocket.pojo.WavesWsDataMessage;
import de.hrw.waves.wavesjhacker.websocket.pojo.transaction.ExchangeTranscation;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnconfirmedExchangeTxListener implements UnconfirmedTxListener {

  private static final Logger LOGGER = LoggerFactory.getLogger(UnconfirmedExchangeTxListener.class);
  private final ObjectMapper mapper = new ObjectMapper();

  @Override
  public void onUtx(WavesWsDataMessage message) {
    JsonNode type = message.getMsg().get("type");
    if (type.asInt() == 7) {
      try {
        //exchangeTransaction
        ExchangeTranscation tx = mapper.readValue(message.getMsg().toString(), ExchangeTranscation.class);
        
        //@TODO now check assets
        //@TODO if right asset, then check amount and create your own transaction with your own data
        //@TODO send your transaction to the network
      } catch (IOException ex) {
        LOGGER.error("Failed to parse exchange transaction", ex);
      }

    }
  }

}
