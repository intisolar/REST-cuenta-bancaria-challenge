package com.devsu.challenge.intigarcia.service;

import com.devsu.challenge.intigarcia.exception.DataNotFoundException;
import com.devsu.challenge.intigarcia.exception.InvalidOperationException;
import com.devsu.challenge.intigarcia.mapper.MovimientoMapper;
import com.devsu.challenge.intigarcia.model.entity.Cliente;
import com.devsu.challenge.intigarcia.model.entity.Cuenta;
import com.devsu.challenge.intigarcia.model.entity.Movimiento;
import com.devsu.challenge.intigarcia.model.request.CreateMovimientoRequest;
import com.devsu.challenge.intigarcia.model.response.ListMovimientoResponse;
import com.devsu.challenge.intigarcia.model.response.MovimientoResponse;
import com.devsu.challenge.intigarcia.repository.IMovimientoRepository;
import com.devsu.challenge.intigarcia.service.abstraction.ICreateMovimiento;
import com.devsu.challenge.intigarcia.service.abstraction.IDeleteMovimiento;
import com.devsu.challenge.intigarcia.service.abstraction.IGetMovimiento;
import com.devsu.challenge.intigarcia.service.abstraction.IUpdateMovimiento;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovimientoService extends AuthorizationService implements ICreateMovimiento,
    IGetMovimiento, IUpdateMovimiento,
    IDeleteMovimiento {

  @Autowired
  private IMovimientoRepository movimientoRepository;
  @Autowired
  private MovimientoMapper mapper;

  @Override
  public MovimientoResponse create(CreateMovimientoRequest request, String clienteID,
      String numeroCuenta) {

    Cliente cliente = findActiveBy(clienteID);
    Cuenta cuenta = hasPermissionReturnCuenta(cliente, numeroCuenta);
    cuenta.setDisponible(updateDisponible(cuenta.getUltimaTransaccion(), cuenta.getSaldo(),
        cuenta.getDisponible()));
    cuenta = operacionMovimiento(request.getTipo(), cuenta, request.getMonto());

    Movimiento movimiento = mapper.map(request, cuenta);
    movimientoRepository.save(movimiento);

    saveMovimientoInCuenta(cuenta, movimiento);
    return mapper.map(movimiento);
  }

  @Override
  public ListMovimientoResponse list(String id, String numeroCuenta) {
    Cliente cliente = findActiveBy(id);
    Cuenta cuenta = hasPermissionReturnCuenta(cliente, numeroCuenta);
    System.out.println(cuenta);
    List<Movimiento> movimientos = movimientoRepository.findByCuentaOrderByFecha(cuenta);
    movimientos.forEach(movimiento -> System.out.println(movimiento));
    isListEmpty(movimientos);
    return mapper.map(movimientos);
  }

  private void saveMovimientoInCuenta(Cuenta cuenta, Movimiento movimiento) {
    List<Movimiento> movimientos = cuenta.getMovimientos();
    movimientos.add(movimiento);
    cuenta.setMovimientos(movimientos);
    cuenta.setSaldo(movimiento.getSaldo());
    cuenta.setUltimaTransaccion(movimiento.getFecha());
    cuentaRepository.save(cuenta);
  }

  private Cuenta operacionMovimiento(String tipoMovimiento, Cuenta cuenta, BigDecimal monto) {

    switch (tipoMovimiento.toUpperCase()) {
      case "CREDITO":
        cuenta.setSaldo(cuenta.getSaldo().add(monto));
        break;
      case "DEBITO":
        suficienteSaldoValidation(cuenta.getSaldo(), cuenta.getDisponible(), monto);
        cuenta.setSaldo(cuenta.getSaldo().subtract(monto));
        cuenta.setDisponible(substractDisponible(cuenta.getDisponible(), monto));
        break;
      default:
        throw new InvalidOperationException("La operación que intenta realizar no existe.");
    }
    return cuenta;
  }

  private void suficienteSaldoValidation(BigDecimal saldoActual, BigDecimal disponible,
      BigDecimal montoOperacion) {
    if (saldoActual.compareTo(montoOperacion) < 0) {
      throw new InvalidOperationException(
          "No tiene el suficiente saldo para realizar esta transacción");
    } else if (disponible.compareTo(montoOperacion) < 0) {
      throw new InvalidOperationException(
          "No se pudo realizar esta transacción. Su saldo disponible restante del día es "
              + disponible + ".");
    }
  }

  private BigDecimal substractDisponible(BigDecimal disponible, BigDecimal monto) {
    return disponible.subtract(monto);
  }

  private BigDecimal updateDisponible(LocalDateTime fechaUltimaTransaccion, BigDecimal saldoActual,
      BigDecimal disponible) {

    LocalDate ultimaTransaccion = fechaUltimaTransaccion.toLocalDate();
    LocalDate hoy = LocalDate.now();

    if (!ultimaTransaccion.equals(hoy)) {
      if (isSaldoMenorADisponible(saldoActual, disponible)) {
        disponible = saldoActual;
      } else {
        disponible = BigDecimal.valueOf(1000);
      }
      return disponible;
    }
    return disponible;
  }

  private Boolean isSaldoMenorADisponible(BigDecimal saldoActual,
      BigDecimal disponible) {
    return saldoActual.compareTo(disponible) < 0;
  }

  private void isListEmpty(List<Movimiento> movimientos) {
    if (movimientos.size() == 0) {
      throw new DataNotFoundException("La tabla aún no tiene movimientos.");
    }
  }

}

