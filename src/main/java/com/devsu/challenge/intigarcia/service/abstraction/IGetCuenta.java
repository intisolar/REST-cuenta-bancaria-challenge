package com.devsu.challenge.intigarcia.service.abstraction;

import com.devsu.challenge.intigarcia.model.entity.Cuenta;
import com.devsu.challenge.intigarcia.model.response.CuentaConsultaSaldoResponse;
import com.devsu.challenge.intigarcia.model.response.CuentaResponse;
import com.devsu.challenge.intigarcia.model.response.ListCuentaResponse;

public interface IGetCuenta {

  CuentaResponse findBy(String id);

  CuentaResponse findByNumeroCuenta(String nroCuenta);

  Cuenta findByNumeroCuentaReturnCuenta(String nroCuenta);

  ListCuentaResponse list();

  ListCuentaResponse listActive();

  ListCuentaResponse listInactive();

  ListCuentaResponse listbyClient(String id);

  CuentaConsultaSaldoResponse getSaldo(String clienteId, String numeroCuenta);

}
