/*
 * Copyright (c) 2017 Hochschule Ruhr West (HRW), Bottrop, Germany
 *
 *           - ALL RIGHTS RESERVED -
 *
 * project:  WavesJHacker
 *
 * file:     Node.java 
 *
 * created:  07.12.2017
 *
 * author:   KS, AS
 */
package de.hrw.waves.wavesjhacker.waves.pojo;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import de.hrw.waves.wavesjhacker.waves.pojo.transactions.Transaction;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Copied by
 * https://github.com/wavesplatform/WavesJ/blob/master/src/main/java/com/wavesplatform/wavesj/Node.java
 *
 * only the required part and adjusted to own transaction class
 *
 */
public class Node {

  public static final String DEFAULT_NODE = "https://testnode1.wavesnodes.com";
  private final CloseableHttpClient client = HttpClients.createDefault();

  private final URI uri;

  public Node() {
    try {
      this.uri = new URI(DEFAULT_NODE);
    } catch (URISyntaxException e) {
      // should not happen
      throw new RuntimeException(e);
    }
  }

  public String send(Transaction tx) throws IOException {
    return parseResponse(exec(request(tx)), "id", String.class);
  }

  private HttpUriRequest request(Transaction tx) throws IOException {
    HttpPost request = new HttpPost(uri + tx.getTransactionType().getEndpoint());
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    String jsonTx = mapper.writeValueAsString(tx);
    System.out.println(jsonTx); 

    request.setEntity(new StringEntity(jsonTx));
    request.setHeader("Content-Type", "application/json");
    request.setHeader("Accept", "application/json");
    return request;
  }

  private HttpResponse exec(HttpUriRequest request) throws IOException {
    HttpResponse r = client.execute(request);
    if (r.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
      Map<String, Object> responseMap = parseResponse(r, new ObjectMapper());
      String error = responseMap.get("message").toString();
      error += "\n" + responseMap.get("validationErrors").toString();
      throw new IOException(error);
    }
    return r;
  }

  private static <T> T parseResponse(HttpResponse r, String key, Class<T> type) throws IOException {
    ObjectMapper mapper = type == Long.class ? longObjectMapper() : new ObjectMapper();
    return type.cast(parseResponse(r, mapper).get(key));
  }

  private static Map<String, Object> parseResponse(HttpResponse r, ObjectMapper mapper) throws IOException {
    return mapper.readValue(r.getEntity().getContent(), Map.class);
  }

  private static ObjectMapper longObjectMapper() {
    return new ObjectMapper().enable(DeserializationFeature.USE_LONG_FOR_INTS);
  }

}
