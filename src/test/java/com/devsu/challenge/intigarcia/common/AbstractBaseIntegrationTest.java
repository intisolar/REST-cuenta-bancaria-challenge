package com.devsu.challenge.intigarcia.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.devsu.challenge.intigarcia.exception.ErrorResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class AbstractBaseIntegrationTest {

  protected TestRestTemplate restTemplate = new TestRestTemplate();
  protected HttpHeaders headers = new HttpHeaders();
  @Value(value="${local.server.port}")
  private int port;

  protected String createURLWithPort(String uri) {
    return "http://localhost:" + port + uri;
  }

  protected String getFirstMessageError(ResponseEntity<ErrorResponse> response) {
    return response.getBody().getMessages().get(0);
  }

  protected int getStatusValue(ResponseEntity<ErrorResponse> response) {
    return response.getBody().getStatus();
  }

  protected int getAmountMessages(ResponseEntity<ErrorResponse> response) {
    return response.getBody().getMessages().size();
  }

  protected void assertOneEmptyOrNullFieldInRequest(ResponseEntity<ErrorResponse> response,
      String expectedErrorMessage) {
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    assertNotNull(response.getBody());
    assertEquals(400, getStatusValue(response));
    assertEquals(1, getAmountMessages(response));
    assertEquals(expectedErrorMessage, getFirstMessageError(response));
  }
}
