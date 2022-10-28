package com.devsu.challenge.intigarcia.controller;

import com.devsu.challenge.intigarcia.exception.InvalidOperationException;
import com.devsu.challenge.intigarcia.exception.ResourceAlreadyExistsException;
import com.devsu.challenge.intigarcia.model.request.CreateMovimientoRequest;
import com.devsu.challenge.intigarcia.model.response.MovimientoResponse;
import com.devsu.challenge.intigarcia.service.abstraction.ICreateMovimiento;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

  @Autowired
  private ICreateMovimiento createMovimiento;

  @Transactional
  @PostMapping("/{id}/{numero}")
  public ResponseEntity<MovimientoResponse> create(
      @RequestBody @Validated CreateMovimientoRequest request,
      @PathVariable(value = "id") String id, @PathVariable(value = "numero") String numero)
      throws InvalidOperationException {
    return ResponseEntity.status(HttpStatus.CREATED).body(createMovimiento.create(request, id, numero));
  }


}
