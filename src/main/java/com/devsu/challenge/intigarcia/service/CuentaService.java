package com.devsu.challenge.intigarcia.service;

import com.devsu.challenge.intigarcia.exception.DataNotFoundException;
import com.devsu.challenge.intigarcia.exception.ResourceNotFoundException;
import com.devsu.challenge.intigarcia.mapper.CuentaMapper;
import com.devsu.challenge.intigarcia.model.entity.Cliente;
import com.devsu.challenge.intigarcia.model.entity.Cuenta;
import com.devsu.challenge.intigarcia.model.request.CreateCuentaRequest;
import com.devsu.challenge.intigarcia.model.request.UpdateCuentaSaldoRequest;
import com.devsu.challenge.intigarcia.model.response.CuentaConsultaSaldoResponse;
import com.devsu.challenge.intigarcia.model.response.CuentaResponse;
import com.devsu.challenge.intigarcia.model.response.ListCuentaResponse;
import com.devsu.challenge.intigarcia.repository.IClienteRepository;
import com.devsu.challenge.intigarcia.repository.ICuentaRepository;
import com.devsu.challenge.intigarcia.service.abstraction.ICreateCuenta;
import com.devsu.challenge.intigarcia.service.abstraction.IDeleteCuenta;
import com.devsu.challenge.intigarcia.service.abstraction.IGetCuenta;
import com.devsu.challenge.intigarcia.service.abstraction.IUpdateCuenta;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CuentaService extends AuthorizationService implements ICreateCuenta, IGetCuenta,
    IUpdateCuenta, IDeleteCuenta {

  @Autowired
  private CuentaMapper mapper;

  @Override
  public CuentaResponse create(CreateCuentaRequest request) {

    String nroCuenta = numeroCuentaGenerator();
    Cuenta cuenta = mapper.map(request, clienteRepository.findByIdAndSoftDeleteFalse(
            request.getClienteID()),
        nroCuenta);
    cuentaRepository.save(cuenta);
    return mapper.map(cuenta);
  }

  @Override
  public Cuenta create(BigDecimal saldoInicial, String tipo, Cliente cliente) {
    Cuenta cuenta = new Cuenta();
    cuenta.setNumeroCuenta(numeroCuentaGenerator());
    cuenta.setTipo(tipo);
    cuenta.setSaldoInicial(saldoInicial);
    cuenta.setSaldo(saldoInicial);
    cuenta.setSoftDelete(false);
    cuenta.setCliente(cliente);
    cuenta.setUltimaTransaccion(LocalDateTime.now());
    cuentaRepository.save(cuenta);
    return cuenta;
  }

  @Override
  public CuentaResponse findBy(String id) {
    Cuenta cuenta = isPresent(cuentaRepository.findById(id).get());
    return mapper.map(cuenta);
  }

  @Override
  public CuentaResponse findByNumeroCuenta(String nroCuenta) {
    Cuenta cuenta = findByNumeroCuentaReturnCuenta(nroCuenta);
    return mapper.map(cuenta);
  }

  @Override
  public Cuenta findByNumeroCuentaReturnCuenta(String nroCuenta) {
    Cuenta cuenta = findByNumeroCuentaReturnCuenta(nroCuenta);
    return cuenta;
  }


  @Override
  public ListCuentaResponse list() {
    List<Cuenta> cuentas = cuentaRepository.findAll(Sort.by("tipo"));
    isListEmpty(cuentas);
    return mapper.map(cuentas);
  }

  @Override
  public ListCuentaResponse listActive() {
    List<Cuenta> cuentas = cuentaRepository.findBySoftDeleteFalseOrderByTipo();
    isListEmpty(cuentas);
    return mapper.map(cuentas);
  }

  @Override
  public ListCuentaResponse listInactive() {
    List<Cuenta> cuentas = cuentaRepository.findBySoftDeleteTrueOrderByTipo();
    isListEmpty(cuentas);
    return mapper.map(cuentas);
  }

  @Override
  public ListCuentaResponse listbyClient(String clienteID) {
    List<Cuenta> cuentas = cuentaRepository.findByCliente(findActiveBy(clienteID));
    isListEmpty(cuentas);
    return mapper.map(cuentas);
  }

  @Override
  public CuentaConsultaSaldoResponse getSaldo(String clienteId, String numeroCuenta) {
    Cliente cliente = findActiveBy(clienteId);
    Cuenta cuenta = hasPermissions(cliente, numeroCuenta);
    return mapper.map(cliente.getNombre()+" "+cliente.getApellido(), cuenta.getSaldo());
  }



  @Override
  public CuentaConsultaSaldoResponse update(UpdateCuentaSaldoRequest request, String id,
      String nroCuenta) {
    Cliente cliente = findActiveBy(id);
    Cuenta cuenta = hasPermissions(cliente, nroCuenta);
    BigDecimal nuevoSaldo = cuenta.getSaldo();
    nuevoSaldo = nuevoSaldo.add(request.getSaldo());
    cuenta.setSaldo(nuevoSaldo);

    cuentaRepository.save(cuenta);
    return mapper.map(cliente.getNombre() + " " + cliente.getApellido(), cuenta.getSaldo());
  }

  @Override
  public void hardDelete(String id) {
    Optional<Cuenta> cuenta = cuentaRepository.findById(id);
    if (!cuenta.isPresent()) {
      throw new ResourceNotFoundException("La cuenta no se encuentra en la base de datos");
    }
    cuentaRepository.delete(cuenta.get());
  }

  @Override
  public void softDelete(String id) {
    Cuenta cuenta = findActiveCuentaBy(id);
    cuenta.setSoftDelete(true);
    cuentaRepository.save(cuenta);
  }

  private Cuenta findActiveCuentaBy(String id) {
    Cuenta cuenta = cuentaRepository.findByIdAndSoftDeleteFalse(id);
    if (cuenta == null) {
      throw new ResourceNotFoundException("La cuenta no existe o se dio de baja.");
    }
    return cuenta;
  }

  private String numeroCuentaGenerator() {
    boolean validador = true;
    Integer nroCuenta = 0;
    while (validador) {
      validador = false;
      nroCuenta = (int) (Math.random() * (999999 - 100000 + 1) + 100000);
      System.out.println(nroCuenta);
      if (isDuplicateCuenta(nroCuenta.toString())) {
        validador = true;
      }
    }
    return nroCuenta.toString();
  }

  private Boolean isDuplicateCuenta(String numeroCuenta) {
    Optional<Cuenta> resultado = cuentaRepository.findByNumeroCuenta(numeroCuenta);
    return resultado.isPresent();
  }



  private void isListEmpty(List<Cuenta> cuentas) {
    if (cuentas.size() == 0) {
      throw new DataNotFoundException("La tabla aún no tiene clientes.");
    }
  }




}

