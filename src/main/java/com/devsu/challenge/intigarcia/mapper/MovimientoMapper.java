package com.devsu.challenge.intigarcia.mapper;

import com.devsu.challenge.intigarcia.model.entity.Cuenta;
import com.devsu.challenge.intigarcia.model.entity.Movimiento;
import com.devsu.challenge.intigarcia.model.request.CreateMovimientoRequest;
import com.devsu.challenge.intigarcia.model.response.ListMovimientoResponse;
import com.devsu.challenge.intigarcia.model.response.MovimientoResponse;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class MovimientoMapper {

  public Movimiento map(CreateMovimientoRequest request, Cuenta cuenta) {

    Movimiento movimiento = new Movimiento();
    movimiento.setFecha(LocalDateTime.now());
    movimiento.setTipo(request.getTipo());
    movimiento.setValor(request.getMonto());
    movimiento.setSaldo(cuenta.getSaldo());
    movimiento.setSoftDelete(false);
    movimiento.setCuenta(cuenta);
    return movimiento;
  }

  public MovimientoResponse map(Movimiento movimiento) {
    MovimientoResponse response = new MovimientoResponse();
    response.setTimestamp(LocalDateTime.now());
    response.setTipo(movimiento.getTipo());
    response.setValor(movimiento.getValor());
    response.setSaldo(movimiento.getSaldo());
    return response;
  }

  public ListMovimientoResponse map(List<Movimiento> movimientos) {
    ListMovimientoResponse response = new ListMovimientoResponse();
    List<MovimientoResponse> responses = new ArrayList<>();
    movimientos.forEach(movimiento -> responses.add(map(movimiento)));
    response.setMovimientoResponses(responses);
    return response;
  }

}
