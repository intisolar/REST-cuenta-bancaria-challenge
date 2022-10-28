package com.devsu.challenge.intigarcia.mapper;

import com.devsu.challenge.intigarcia.model.entity.Movimiento;
import com.devsu.challenge.intigarcia.model.request.CreateMovimientoRequest;
import com.devsu.challenge.intigarcia.model.response.MovimientoResponse;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class MovimientoMapper {

  public Movimiento map(CreateMovimientoRequest request, BigDecimal nuevoSaldo){

    Movimiento movimiento = new Movimiento();
    movimiento.setFecha(LocalDateTime.now());
    movimiento.setTipo(request.getTipo());
    movimiento.setValor(request.getMonto());
    movimiento.setSaldo(nuevoSaldo);
    movimiento.setSoftDelete(false);
    return movimiento;
  }

  public MovimientoResponse map(Movimiento movimiento){
    MovimientoResponse response = new MovimientoResponse();
    response.setTimestamp(LocalDateTime.now());
    response.setTipo(movimiento.getTipo());
    response.setValor(movimiento.getValor());
    response.setSaldo(movimiento.getSaldo());
    return response;
  }

}
