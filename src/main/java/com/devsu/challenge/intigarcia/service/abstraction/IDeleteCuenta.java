package com.devsu.challenge.intigarcia.service.abstraction;

public interface IDeleteCuenta {
  void hardDelete(String id);

  void softDelete(String id);
}
