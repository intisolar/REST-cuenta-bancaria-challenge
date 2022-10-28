package com.devsu.challenge.intigarcia.integration;

import static org.junit.Assert.assertEquals;

import com.devsu.challenge.intigarcia.model.request.CreateClienteRequest;
import com.devsu.challenge.intigarcia.model.response.ClienteResponse;
import java.math.BigDecimal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CreateClienteIntegrationTest extends AbstractClienteIntegrationTest {

  @Test
  public void shouldCreateCliente() {

    CreateClienteRequest createClienteRequest = buildRequest();
    headers.setAccessControlAllowCredentials(true);
    HttpEntity<CreateClienteRequest> requestHttpEntity =
        new HttpEntity<>(createClienteRequest, headers);

    ResponseEntity<ClienteResponse> response = restTemplate
        .exchange(createURLWithPort(PATH), HttpMethod.POST, requestHttpEntity,
            ClienteResponse.class);

    //getClienteResponse(HttpMethod.POST, requestHttpEntity);
    System.out.println(response.getStatusCode());
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
  }

  private CreateClienteRequest buildRequestWithEmptyPassword() {
    return buildRequest(PASSWORD, NOMBRE, APELLIDO, GENERO, EDAD, DNI,
        CALLE, ALTURA, CIUDAD, PROVINCIA, PAIS, CODIGO_PAIS, CODIGO_AREA, TELEFONO,
        TIPO_CUENTA_INICIAL, SALDO_INICIAL);
  }

  private CreateClienteRequest buildRequestWithEmptyNombre() {
    return buildRequest(PASSWORD, NOMBRE, APELLIDO, GENERO, EDAD, DNI,
        CALLE, ALTURA, CIUDAD, PROVINCIA, PAIS, CODIGO_PAIS, CODIGO_AREA, TELEFONO,
        TIPO_CUENTA_INICIAL, SALDO_INICIAL);
  }

  private CreateClienteRequest buildRequestWithEmptyApellido() {
    return buildRequest(PASSWORD, NOMBRE, APELLIDO, GENERO, EDAD, DNI,
        CALLE, ALTURA, CIUDAD, PROVINCIA, PAIS, CODIGO_PAIS, CODIGO_AREA, TELEFONO,
        TIPO_CUENTA_INICIAL, SALDO_INICIAL);
  }

  private CreateClienteRequest buildRequestWithEmptyGenero() {
    return buildRequest(PASSWORD, NOMBRE, APELLIDO, GENERO, EDAD, DNI,
        CALLE, ALTURA, CIUDAD, PROVINCIA, PAIS, CODIGO_PAIS, CODIGO_AREA, TELEFONO,
        TIPO_CUENTA_INICIAL, SALDO_INICIAL);
  }

  private CreateClienteRequest buildRequestWithEmptyEdad() {
    return buildRequest(PASSWORD, NOMBRE, APELLIDO, GENERO, EDAD, DNI,
        CALLE, ALTURA, CIUDAD, PROVINCIA, PAIS, CODIGO_PAIS, CODIGO_AREA, TELEFONO,
        TIPO_CUENTA_INICIAL, SALDO_INICIAL);
  }

  private CreateClienteRequest buildRequestWithEmptyDni() {
    return buildRequest(PASSWORD, NOMBRE, APELLIDO, GENERO, EDAD, DNI,
        CALLE, ALTURA, CIUDAD, PROVINCIA, PAIS, CODIGO_PAIS, CODIGO_AREA, TELEFONO,
        TIPO_CUENTA_INICIAL, SALDO_INICIAL);
  }

  private CreateClienteRequest buildRequestWithEmptyCalle() {
    return buildRequest(PASSWORD, NOMBRE, APELLIDO, GENERO, EDAD, DNI,
        CALLE, ALTURA, CIUDAD, PROVINCIA, PAIS, CODIGO_PAIS, CODIGO_AREA, TELEFONO,
        TIPO_CUENTA_INICIAL, SALDO_INICIAL);
  }

  private CreateClienteRequest buildRequestWithEmptyAltura() {
    return buildRequest(PASSWORD, NOMBRE, APELLIDO, GENERO, EDAD, DNI,
        CALLE, ALTURA, CIUDAD, PROVINCIA, PAIS, CODIGO_PAIS, CODIGO_AREA, TELEFONO,
        TIPO_CUENTA_INICIAL, SALDO_INICIAL);
  }

  private CreateClienteRequest buildRequestWithEmptyCiudad() {
    return buildRequest(PASSWORD, NOMBRE, APELLIDO, GENERO, EDAD, DNI,
        CALLE, ALTURA, CIUDAD, PROVINCIA, PAIS, CODIGO_PAIS, CODIGO_AREA, TELEFONO,
        TIPO_CUENTA_INICIAL, SALDO_INICIAL);
  }

  private CreateClienteRequest buildRequestWithEmptyProvincia() {
    return buildRequest(PASSWORD, NOMBRE, APELLIDO, GENERO, EDAD, DNI,
        CALLE, ALTURA, CIUDAD, PROVINCIA, PAIS, CODIGO_PAIS, CODIGO_AREA, TELEFONO,
        TIPO_CUENTA_INICIAL, SALDO_INICIAL);
  }

  private CreateClienteRequest buildRequestWithEmptyPais() {
    return buildRequest(PASSWORD, NOMBRE, APELLIDO, GENERO, EDAD, DNI,
        CALLE, ALTURA, CIUDAD, PROVINCIA, PAIS, CODIGO_PAIS, CODIGO_AREA, TELEFONO,
        TIPO_CUENTA_INICIAL, SALDO_INICIAL);
  }

  private CreateClienteRequest buildRequestWithEmptyCodigoPais() {
    return buildRequest(PASSWORD, NOMBRE, APELLIDO, GENERO, EDAD, DNI,
        CALLE, ALTURA, CIUDAD, PROVINCIA, PAIS, CODIGO_PAIS, CODIGO_AREA, TELEFONO,
        TIPO_CUENTA_INICIAL, SALDO_INICIAL);
  }

  private CreateClienteRequest buildRequestWithEmptyCodigoArea() {
    return buildRequest(PASSWORD, NOMBRE, APELLIDO, GENERO, EDAD, DNI,
        CALLE, ALTURA, CIUDAD, PROVINCIA, PAIS, CODIGO_PAIS, CODIGO_AREA, TELEFONO,
        TIPO_CUENTA_INICIAL, SALDO_INICIAL);
  }


  private CreateClienteRequest buildRequestWithEmptyTelefono() {
    return buildRequest(PASSWORD, NOMBRE, APELLIDO, GENERO, EDAD, DNI,
        CALLE, ALTURA, CIUDAD, PROVINCIA, PAIS, CODIGO_PAIS, CODIGO_AREA, TELEFONO,
        TIPO_CUENTA_INICIAL, SALDO_INICIAL);
  }

  private CreateClienteRequest buildRequest() {
    return buildRequest(PASSWORD, NOMBRE, APELLIDO, GENERO, EDAD, DNI,
        CALLE, ALTURA, CIUDAD, PROVINCIA, PAIS, CODIGO_PAIS, CODIGO_AREA, TELEFONO,
        TIPO_CUENTA_INICIAL, SALDO_INICIAL);
  }

  private CreateClienteRequest buildRequest(String password, String nombre,
      String apellido, String genero, Integer edad, Integer dni,
      String calle, String altura, String ciudad, String provincia,
      String pais, String codigoPais, String codigoArea, String telefono, String tipo,
      BigDecimal saldoInicial) {

    return new CreateClienteRequest(password, nombre, apellido, genero, edad, dni,
        calle, altura, ciudad, provincia, pais, codigoPais, codigoArea, telefono, tipo,
        saldoInicial);
  }
}
