package com.devsu.challenge.intigarcia.repository;

import com.devsu.challenge.intigarcia.model.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovimientoRepository extends JpaRepository<Movimiento, String> {


}
