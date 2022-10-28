package com.devsu.challenge.intigarcia.service.abstraction;

import com.devsu.challenge.intigarcia.model.entity.Cliente;
import com.devsu.challenge.intigarcia.model.entity.Cuenta;
import com.devsu.challenge.intigarcia.model.request.CreateCuentaRequest;
import com.devsu.challenge.intigarcia.model.response.CuentaResponse;
import java.math.BigDecimal;

public interface ICreateCuenta {

  CuentaResponse create(CreateCuentaRequest request);
  Cuenta create(BigDecimal saldoInicial, String tipo, Cliente cliente);
}
