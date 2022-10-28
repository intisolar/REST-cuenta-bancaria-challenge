package com.devsu.challenge.intigarcia.service.abstraction;

import com.devsu.challenge.intigarcia.model.request.UpdateCuentaSaldoRequest;
import com.devsu.challenge.intigarcia.model.response.CuentaConsultaSaldoResponse;

public interface IUpdateCuenta {

  CuentaConsultaSaldoResponse update(UpdateCuentaSaldoRequest request, String id, String nroCuenta);
}
