package com.devsu.challenge.intigarcia.mapper;

import com.devsu.challenge.intigarcia.model.entity.Cliente;
import com.devsu.challenge.intigarcia.model.entity.Cuenta;
import com.devsu.challenge.intigarcia.model.request.CreateClienteRequest;
import com.devsu.challenge.intigarcia.model.request.UpdateClienteRequest;
import com.devsu.challenge.intigarcia.model.response.ClienteResponse;
import com.devsu.challenge.intigarcia.model.response.ListClienteResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

  public Cliente map(CreateClienteRequest request) {
    Cliente cliente = new Cliente();
    cliente.setNombre(request.getNombre());
    cliente.setApellido(request.getApellido());
    cliente.setPassword(request.getPassword());
    cliente.setGenero(request.getGenero());
    cliente.setDni(request.getDni());
    cliente.getCuentas().add(new Cuenta());
    cliente.setEdad(request.getEdad());
    cliente.setDireccion(
        request.getCalle() + " " + request.getAltura() + ", " + request.getCiudad() + ", "
            + request.getProvincia() + ", " + request.getPais());
    cliente.setTelefono(
        "+" + request.getCodigoPais() + request.getCodigoArea() + request.getTelefono());
    cliente.setSoftDelete(false);
    return cliente;

  }

  public Cliente map(Cliente cliente, UpdateClienteRequest updateClienteRequest) {

    if (!updateClienteRequest.getCalle().isEmpty() && !updateClienteRequest.getAltura().isEmpty()) {
      cliente.setDireccion(
          updateClienteRequest.getCalle() + " " + updateClienteRequest.getAltura());
    }
    if (updateClienteRequest.getCodigoPais() != null && updateClienteRequest.getCodigoArea() != null
        && updateClienteRequest.getTelefono() != null) {
      cliente.setTelefono(
          "+" + updateClienteRequest.getCodigoPais() + updateClienteRequest.getCodigoArea()
              + updateClienteRequest.getTelefono());
    }
    return cliente;
  }

  public ClienteResponse map(Cliente cliente) {
    ClienteResponse response = new ClienteResponse();
    response.setId(cliente.getId());
    response.setNombreCompleto(cliente.getNombre() + " " + cliente.getApellido());
    response.setDni(cliente.getDni());
    response.setEdad(cliente.getEdad());
    response.setGenero(cliente.getGenero());
    response.setDireccion(cliente.getDireccion());
    response.setTelefono(cliente.getTelefono());
    response.setAlta(!cliente.getSoftDelete());
    return response;
  }

  public ListClienteResponse map(List<Cliente> clientes) {
    ListClienteResponse responses = new ListClienteResponse();
    List<ClienteResponse> clienteResponseList = new ArrayList<>();
    clientes.forEach(cliente -> clienteResponseList.add(map(cliente)));
    responses.setClienteResponses(clienteResponseList);

    return responses;
  }

}
