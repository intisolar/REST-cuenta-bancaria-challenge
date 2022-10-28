package com.devsu.challenge.intigarcia.service.abstraction;

public interface IDeleteCliente {

  void hardDelete(String id);

  void softDelete(String id);

}
