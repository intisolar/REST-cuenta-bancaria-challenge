package com.devsu.challenge.intigarcia.service.abstraction;

import com.devsu.challenge.intigarcia.model.response.ListMovimientoResponse;

public interface IGetMovimiento {

  ListMovimientoResponse list(String id, String numeroCuenta);

}
