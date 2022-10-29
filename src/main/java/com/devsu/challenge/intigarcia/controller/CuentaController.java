package com.devsu.challenge.intigarcia.controller;

import com.devsu.challenge.intigarcia.exception.DataNotFoundException;
import com.devsu.challenge.intigarcia.exception.InsufficientPermissionsException;
import com.devsu.challenge.intigarcia.exception.ResourceAlreadyExistsException;
import com.devsu.challenge.intigarcia.exception.ResourceNotFoundException;
import com.devsu.challenge.intigarcia.model.request.CreateCuentaRequest;
import com.devsu.challenge.intigarcia.model.request.UpdateCuentaSaldoRequest;
import com.devsu.challenge.intigarcia.model.response.CuentaConsultaSaldoResponse;
import com.devsu.challenge.intigarcia.model.response.CuentaResponse;
import com.devsu.challenge.intigarcia.model.response.ListCuentaResponse;
import com.devsu.challenge.intigarcia.service.abstraction.ICreateCuenta;
import com.devsu.challenge.intigarcia.service.abstraction.IDeleteCuenta;
import com.devsu.challenge.intigarcia.service.abstraction.IGetCuenta;
import com.devsu.challenge.intigarcia.service.abstraction.IUpdateCuenta;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

  @Autowired
  private ICreateCuenta createCuenta;
  @Autowired
  private IGetCuenta getCuenta;
  @Autowired
  private IUpdateCuenta updateCuenta;
  @Autowired
  private IDeleteCuenta deleteCuenta;

  @Transactional
  @PostMapping
  public ResponseEntity<CuentaResponse> create(@RequestBody @Validated CreateCuentaRequest request)
      throws ResourceAlreadyExistsException {
    return ResponseEntity.status(HttpStatus.CREATED).body(createCuenta.create(request));
  }

  @GetMapping
  public ResponseEntity<ListCuentaResponse> list() throws DataNotFoundException {
    return ResponseEntity.ok(getCuenta.list());
  }

  @GetMapping("/cte/{id}")
  public ResponseEntity<ListCuentaResponse> list(@PathVariable(value = "id") String id)
      throws DataNotFoundException, ResourceNotFoundException {
    return ResponseEntity.ok(getCuenta.listbyClient(id));
  }

  @GetMapping("/alta")
  public ResponseEntity<ListCuentaResponse> listActive() throws DataNotFoundException {
    return ResponseEntity.ok(getCuenta.listActive());
  }

  @GetMapping("/baja")
  public ResponseEntity<ListCuentaResponse> listInactive() throws DataNotFoundException {
    return ResponseEntity.ok(getCuenta.listInactive());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CuentaResponse> getBy(@PathVariable(value = "id") String id) throws
      ResourceNotFoundException {
    return ResponseEntity.ok(getCuenta.findBy(id));
  }

  @GetMapping("/{numero}")
  public ResponseEntity<CuentaResponse> getByNumeroCuenta(
      @PathVariable(value = "numero") String numero) throws
      ResourceNotFoundException {
    return ResponseEntity.ok(getCuenta.findByNumeroCuenta(numero));
  }


  @Transactional
  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable(value = "id") String id) {
    deleteCuenta.softDelete(id);
    return ResponseEntity.noContent().build();
  }

  @Transactional
  @DeleteMapping("{id}/hard")
  public ResponseEntity<Void> hardDelete(@PathVariable(value = "id") String id) {
    deleteCuenta.hardDelete(id);
    return ResponseEntity.noContent().build();
  }

  /*Saldo*/

  @Transactional
  @PatchMapping("/{id}/{numero}")
  public ResponseEntity<CuentaConsultaSaldoResponse> update(@PathVariable(value = "id") String id,
      @PathVariable(value = "numero") String numero,
      @RequestBody @Valid UpdateCuentaSaldoRequest request)
      throws ResourceNotFoundException, InsufficientPermissionsException {
    return ResponseEntity.ok(updateCuenta.update(request, id, numero));
  }

  @GetMapping("/{id}/{numero}")
  public ResponseEntity<CuentaConsultaSaldoResponse> getBy(@PathVariable(value = "id") String id,
      @PathVariable(value = "numero") String numero)
      throws ResourceNotFoundException, InsufficientPermissionsException {
    return ResponseEntity.ok(getCuenta.getSaldo(id,numero));
  }
}
