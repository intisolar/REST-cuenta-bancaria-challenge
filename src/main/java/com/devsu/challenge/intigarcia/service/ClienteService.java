package com.devsu.challenge.intigarcia.service;

import com.devsu.challenge.intigarcia.exception.DataNotFoundException;
import com.devsu.challenge.intigarcia.exception.InvalidCredentialsException;
import com.devsu.challenge.intigarcia.exception.ResourceAlreadyExistsException;
import com.devsu.challenge.intigarcia.exception.ResourceNotFoundException;
import com.devsu.challenge.intigarcia.mapper.ClienteMapper;
import com.devsu.challenge.intigarcia.model.entity.Cliente;
import com.devsu.challenge.intigarcia.model.entity.Cuenta;
import com.devsu.challenge.intigarcia.model.request.CreateClienteRequest;
import com.devsu.challenge.intigarcia.model.request.UpdateClienteRequest;
import com.devsu.challenge.intigarcia.model.response.ClienteResponse;
import com.devsu.challenge.intigarcia.model.response.ListClienteResponse;
import com.devsu.challenge.intigarcia.repository.IClienteRepository;
import com.devsu.challenge.intigarcia.service.abstraction.ICreateCliente;
import com.devsu.challenge.intigarcia.service.abstraction.IDeleteCliente;
import com.devsu.challenge.intigarcia.service.abstraction.IGetCliente;
import com.devsu.challenge.intigarcia.service.abstraction.IUpdateCliente;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService extends AuthorizationService implements ICreateCliente, IDeleteCliente, IUpdateCliente, IGetCliente {

  @Autowired
  private ClienteMapper mapper;
  @Autowired
  private CuentaService cuentaService;

  @Override
  public ClienteResponse create(CreateClienteRequest request) {
    isDuplicateDNI(request.getDni());
    Cliente cliente = clienteRepository.save(mapper.map(request));
    Cuenta cuenta = cuentaService.create(request.getSaldoInicial(),
        request.getTipoDeCuentaInicial(), clienteRepository.findByDni(cliente.getDni()).get());
    return mapper.map(cliente);
  }

  @Override
  public ClienteResponse findBy(String id) {
    Cliente cliente = isPresent(clienteRepository.findById(id));
    return mapper.map(cliente);
  }

  @Override
  public ClienteResponse findBy(int dni) {
    Cliente cliente = isPresent(clienteRepository.findByDni(dni));
    return mapper.map(cliente);
  }


  @Override
  public ListClienteResponse list() {
    List<Cliente> clientes = clienteRepository.findAll();
    isListEmpty(clientes);
    return mapper.map(clientes);
  }

  @Override
  public ListClienteResponse listInactive() {
    List<Cliente> clientes = clienteRepository.findBySoftDeleteTrue();
    isListEmpty(clientes);
    return mapper.map(clientes);
  }

  @Override
  public ListClienteResponse listActive() {
    List<Cliente> clientes = clienteRepository.findBySoftDeleteFalse();
    isListEmpty(clientes);
    return mapper.map(clientes);
  }

  @Override
  public ClienteResponse update(UpdateClienteRequest request, String clienteID) {
    Cliente cliente = findActiveBy(clienteID);
    if (!cliente.getPassword().equals(request.getPassword())) {
      throw new InvalidCredentialsException("Su contraseña no coincide.");
    }
    cliente = mapper.map(cliente, request);
    return mapper.map(clienteRepository.save(cliente));
  }

  @Override
  public void hardDelete(String id) {
    Optional<Cliente> cliente = clienteRepository.findById(id);
    if (!cliente.isPresent()) {
      throw new ResourceNotFoundException("El cliente no se encuentra en la base de datos");
    }
    clienteRepository.delete(cliente.get());
  }

  @Override
  public void softDelete(String id) {
    Cliente cliente = findActiveBy(id);
    cliente.setSoftDelete(true);
    clienteRepository.save(cliente);
  }

  private void isDuplicateDNI(int dni) {
    Optional<Cliente> cliente = clienteRepository.findByDni(dni);
    if (cliente.isPresent()) {
      String message = "";
      if (cliente.get().isSoftDelete()) {
        message = " Está inactivo. Dele de alta para operar.";
      }
      throw new ResourceAlreadyExistsException("El cliente ya se encuentra"
          + "en la base de datos." + message);
    }
  }

  private void isListEmpty(List<Cliente> clientes) {
    if (clientes.size() == 0) {
      throw new DataNotFoundException("La tabla aún no tiene clientes.");
    }
  }


}
