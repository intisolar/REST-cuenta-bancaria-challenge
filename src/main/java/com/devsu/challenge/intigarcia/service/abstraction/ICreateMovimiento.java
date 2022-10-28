package com.devsu.challenge.intigarcia.service.abstraction;

import com.devsu.challenge.intigarcia.model.request.CreateMovimientoRequest;
import com.devsu.challenge.intigarcia.model.response.MovimientoResponse;

public interface ICreateMovimiento {

  MovimientoResponse create(CreateMovimientoRequest request, String clienteID, String numeroCuenta);
}
