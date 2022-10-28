package com.devsu.challenge.intigarcia.service.abstraction;

import com.devsu.challenge.intigarcia.model.request.UpdateClienteRequest;
import com.devsu.challenge.intigarcia.model.response.ClienteResponse;

public interface IUpdateCliente {

  ClienteResponse update(UpdateClienteRequest request, String id);

}
