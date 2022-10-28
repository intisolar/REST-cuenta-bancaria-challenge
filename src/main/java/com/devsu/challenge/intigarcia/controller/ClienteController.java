package com.devsu.challenge.intigarcia.controller;

import com.devsu.challenge.intigarcia.exception.DataNotFoundException;
import com.devsu.challenge.intigarcia.exception.InvalidCredentialsException;
import com.devsu.challenge.intigarcia.exception.ResourceAlreadyExistsException;
import com.devsu.challenge.intigarcia.exception.ResourceNotFoundException;
import com.devsu.challenge.intigarcia.model.request.CreateClienteRequest;
import com.devsu.challenge.intigarcia.model.request.UpdateClienteRequest;
import com.devsu.challenge.intigarcia.model.response.ClienteResponse;
import com.devsu.challenge.intigarcia.model.response.ListClienteResponse;
import com.devsu.challenge.intigarcia.service.abstraction.ICreateCliente;
import com.devsu.challenge.intigarcia.service.abstraction.IDeleteCliente;
import com.devsu.challenge.intigarcia.service.abstraction.IGetCliente;
import com.devsu.challenge.intigarcia.service.abstraction.IUpdateCliente;
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
@RequestMapping("/clientes")
public class ClienteController {

  @Autowired
  private IGetCliente getCliente;
  @Autowired
  private IUpdateCliente updateCliente;
  @Autowired
  private ICreateCliente createCliente;
  @Autowired
  private IDeleteCliente deleteCliente;

  @Transactional
  @PostMapping
  public ResponseEntity<ClienteResponse> create(@RequestBody @Validated CreateClienteRequest request)
      throws ResourceAlreadyExistsException {
    return ResponseEntity.status(HttpStatus.CREATED).body(createCliente.create(request));
  }

  @GetMapping
  public ResponseEntity<ListClienteResponse> list() throws DataNotFoundException {
    return ResponseEntity.ok(getCliente.list());
  }

  @GetMapping("/alta")
  public ResponseEntity<ListClienteResponse> listActive() throws DataNotFoundException {
    return ResponseEntity.ok(getCliente.listActive());
  }

  @GetMapping("/baja")
  public ResponseEntity<ListClienteResponse> listInactive() throws DataNotFoundException {
    return ResponseEntity.ok(getCliente.listInactive());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ClienteResponse> getBy(@PathVariable(value = "id") String id) throws
      ResourceNotFoundException {
    return ResponseEntity.ok(getCliente.findBy(id));
  }

  @GetMapping("/dni/{dni}")
  public ResponseEntity<ClienteResponse> getBy(@PathVariable(value = "dni") int dni) throws
      ResourceNotFoundException {
    return ResponseEntity.ok(getCliente.findBy(dni));
  }

  @Transactional
  @PatchMapping("/{id}")
  public ResponseEntity<ClienteResponse> update(@PathVariable(value = "id") String id,
      @RequestBody @Valid UpdateClienteRequest request)
      throws ResourceNotFoundException, InvalidCredentialsException {
    return ResponseEntity.ok(updateCliente.update(request, id));
  }

  @Transactional
  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable(value = "id") String id) {
    deleteCliente.softDelete(id);
    return ResponseEntity.noContent().build();
  }

  @Transactional
  @DeleteMapping("{id}/hard")
  public ResponseEntity<Void> hardDelete(@PathVariable(value = "id") String id) {
    deleteCliente.hardDelete(id);
    return ResponseEntity.noContent().build();
  }
}
