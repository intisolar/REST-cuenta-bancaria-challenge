package com.devsu.challenge.intigarcia.mapper;

import com.devsu.challenge.intigarcia.model.entity.Cliente;
import com.devsu.challenge.intigarcia.model.entity.Cuenta;
import com.devsu.challenge.intigarcia.model.request.CreateCuentaRequest;
import com.devsu.challenge.intigarcia.model.response.CuentaConsultaSaldoResponse;
import com.devsu.challenge.intigarcia.model.response.CuentaResponse;
import com.devsu.challenge.intigarcia.model.response.ListCuentaResponse;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CuentaMapper {

  public Cuenta map(CreateCuentaRequest request, Cliente cliente,
      String numeroCuenta) {
    Cuenta cuenta = new Cuenta();
    cuenta.setCliente(cliente);
    cuenta.setNumeroCuenta(numeroCuenta);
    cuenta.setTipo(request.getTipoDeCuenta());
    cuenta.setSaldoInicial(request.getSaldoInicial());
    cuenta.setSoftDelete(false);
    cuenta.setUltimaTransaccion(LocalDateTime.now());
    return cuenta;
  }

  public CuentaResponse map(Cuenta cuenta){
    CuentaResponse response = new CuentaResponse();
    response.setNumeroCuenta(cuenta.getNumeroCuenta());
    response.setTipo(cuenta.getTipo());
    response.setSaldoInicial(cuenta.getSaldoInicial());
    response.setAlta(cuenta.getSoftDelete());
    response.setCliente(cuenta.getCliente().getNombre()
    +" "+cuenta.getCliente().getApellido());
    return response;
  }

  public CuentaConsultaSaldoResponse map(String nombreTitular, BigDecimal nuevoSaldo){
    CuentaConsultaSaldoResponse saldoResponse = new CuentaConsultaSaldoResponse();
    saldoResponse.setSaldo(nuevoSaldo);
    saldoResponse.setNombreTitular(nombreTitular);
    return saldoResponse;
  }
  public ListCuentaResponse map(List<Cuenta> cuentas){
    ListCuentaResponse response = new ListCuentaResponse();
    List<CuentaResponse> cuentaResponseList = new ArrayList<>();
    cuentas.forEach(cuenta -> cuentaResponseList.add(map(cuenta)));
    response.setResponses(cuentaResponseList);
    return response;
  }
}
