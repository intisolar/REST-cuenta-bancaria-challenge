package com.devsu.challenge.intigarcia.service.abstraction;

import com.devsu.challenge.intigarcia.model.request.CreateClienteRequest;
import com.devsu.challenge.intigarcia.model.response.ClienteResponse;

public interface ICreateCliente {

  ClienteResponse create(CreateClienteRequest request);
}
