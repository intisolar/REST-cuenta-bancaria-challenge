package com.devsu.challenge.intigarcia.repository;

import com.devsu.challenge.intigarcia.model.entity.Cliente;
import com.devsu.challenge.intigarcia.model.entity.Cuenta;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICuentaRepository extends JpaRepository<Cuenta, String> {

  Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);

  Cuenta findByIdAndSoftDeleteFalse(String id);

  List<Cuenta> findBySoftDeleteFalseOrderByTipo();

  List<Cuenta> findBySoftDeleteTrueOrderByTipo();

  List<Cuenta> findByCliente(Cliente cliente);
}
