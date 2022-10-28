package com.devsu.challenge.intigarcia.integration;

import static org.mockito.Mockito.when;

import com.devsu.challenge.intigarcia.common.AbstractBaseIntegrationTest;
import com.devsu.challenge.intigarcia.exception.ErrorResponse;
import com.devsu.challenge.intigarcia.model.entity.Cliente;
import com.devsu.challenge.intigarcia.model.entity.Cuenta;
import com.devsu.challenge.intigarcia.model.request.CreateClienteRequest;
import com.devsu.challenge.intigarcia.model.response.ClienteResponse;
import com.devsu.challenge.intigarcia.repository.IClienteRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public abstract class AbstractClienteIntegrationTest extends AbstractBaseIntegrationTest {


  protected static final String CLIENTE_ID = "87a1bcf0-e025-4738-9127-a39fbd50c4b6";
  protected static final String PATH = "/clientes";
  protected static final String PATH_ID = PATH + "/" + CLIENTE_ID;
  protected static final String NOMBRE = "Juan";
  protected static final String APELLIDO = "Perez";
  protected static final String GENERO = "NO BINARIE";
  protected static final Integer EDAD = 18;
  protected static final Integer DNI = 45987341;
  protected static final String CALLE = "Pasco";
  protected static final String ALTURA = "1846";
  protected static final String CIUDAD = "Rosario";
  protected static final String PROVINCIA = "Santa Fe";
  protected static final String PAIS = "Argentina";
  protected static final String DIRECCION = CALLE+" "+ALTURA+" "+CIUDAD+" "+PROVINCIA+" "+PAIS;
 protected static final String CODIGO_PAIS = "54" ;
  protected static final String CODIGO_AREA = "321" ;
  protected static final String NUM_TELEFONO = "4822222";
  protected static final String TELEFONO = "+"+CODIGO_PAIS+CODIGO_AREA+ NUM_TELEFONO;
  protected static final String PASSWORD = "Challenge2022";
  protected static final List<Cuenta> CUENTA = new ArrayList<>();

  protected static final String TIPO_CUENTA_INICIAL = "Caja de ahorros";

  protected static final BigDecimal SALDO_INICIAL = BigDecimal.valueOf(1000.00);


  @MockBean
  protected IClienteRepository clienteRepository;


  @Before
  public void checkFindMethods() {
    when(clienteRepository.findByIdAndSoftDeleteFalse(CLIENTE_ID))
        .thenReturn(StubCliente());
  }

  protected Cliente StubCliente() {
    Cliente cliente = new Cliente();
    cliente.setId(CLIENTE_ID);
    cliente.setNombre(NOMBRE);
    cliente.setApellido(APELLIDO);
    cliente.setGenero(GENERO);
    cliente.setEdad(EDAD);
    cliente.setDni(DNI);
    cliente.setDireccion(DIRECCION);
    cliente.setTelefono(TELEFONO);
    cliente.setPassword(PASSWORD);
    cliente.setCuentas(CUENTA);
    cliente.setCreationTimestamp(LocalDateTime.now());
    cliente.setSoftDelete(false);
    return cliente;
  }

  protected List<Cliente> buildClienteStubList() {
    List<Cliente> clientes = new ArrayList<>(1);
    clientes.add(StubCliente());
    return clientes;
  }

  protected ResponseEntity<ErrorResponse> getErrorResponseEntity(
      CreateClienteRequest createRequest) {
    HttpEntity<CreateClienteRequest> httpRequest =
        new HttpEntity<>(createRequest, headers);
    return restTemplate.exchange(createURLWithPort(PATH),
        HttpMethod.POST, httpRequest, ErrorResponse.class);
  }

  protected ResponseEntity<ClienteResponse> getClienteResponse(
      HttpMethod httpMethod, HttpEntity<?> request){
    return restTemplate.exchange(createURLWithPort(PATH), httpMethod,request,
        ClienteResponse.class);
  }

}
