package com.devsu.challenge.intigarcia.service.abstraction;

import com.devsu.challenge.intigarcia.model.response.ClienteResponse;
import com.devsu.challenge.intigarcia.model.response.ListClienteResponse;

public interface IGetCliente {

  ClienteResponse findBy(String id);

  ClienteResponse findBy(int dni);

  ListClienteResponse list();

  ListClienteResponse listActive();

  ListClienteResponse listInactive();
}
