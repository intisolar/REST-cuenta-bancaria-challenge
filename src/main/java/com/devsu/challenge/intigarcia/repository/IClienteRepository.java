package com.devsu.challenge.intigarcia.repository;

import com.devsu.challenge.intigarcia.model.entity.Cliente;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, String> {

  Cliente findByIdAndSoftDeleteFalse(String id);

  List<Cliente> findBySoftDeleteFalse();

  List<Cliente> findBySoftDeleteTrue();
  Optional<Cliente> findByDni(int dni);
}
