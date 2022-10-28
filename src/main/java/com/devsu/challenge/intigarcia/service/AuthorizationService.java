package com.devsu.challenge.intigarcia.service;

import com.devsu.challenge.intigarcia.exception.InsufficientPermissionsException;
import com.devsu.challenge.intigarcia.exception.ResourceNotFoundException;
import com.devsu.challenge.intigarcia.model.entity.Cliente;
import com.devsu.challenge.intigarcia.model.entity.Cuenta;
import com.devsu.challenge.intigarcia.repository.IClienteRepository;
import com.devsu.challenge.intigarcia.repository.ICuentaRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

  @Autowired
  protected IClienteRepository clienteRepository;
  @Autowired
  protected ICuentaRepository cuentaRepository;

  protected Cliente findActiveBy(String id) {
    Cliente cliente = clienteRepository.findByIdAndSoftDeleteFalse(id);
    if (cliente == null) {
      throw new ResourceNotFoundException("El cliente solicitado no existe o se dio de baja");
    }
    return cliente;
  }

  protected Cuenta isPresent(Cuenta cuenta) {
    if (cuenta == null) {
      throw new ResourceNotFoundException("La cuenta no existe en base de datos.");
    }
    return cuenta;
  }
  protected Cliente isPresent(Optional<Cliente> cliente) {
    if (!cliente.isPresent()) {
      throw new ResourceNotFoundException(
          "El cliente solicitado no existe en la base de datos");
    }
    return cliente.get();
  }
  protected Cuenta findCuentaByNumeroCuenta(String nroCuenta) {
    Cuenta cuenta = isPresent(cuentaRepository.findByNumeroCuenta(nroCuenta).get());
    return cuenta;
  }

  protected Cuenta hasPermissions(Cliente cliente, String numeroCuenta) {
    Cuenta cuenta = findCuentaByNumeroCuenta(numeroCuenta);
    if (!cliente.equals(cuenta.getCliente())) {
      throw new InsufficientPermissionsException(
          "No posee permisos para operar sobre esta cuenta.");
    }
    return cuenta;
  }

}
