package com.devsu.challenge.intigarcia.repository;

import com.devsu.challenge.intigarcia.model.entity.Cuenta;
import com.devsu.challenge.intigarcia.model.entity.Movimiento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovimientoRepository extends JpaRepository<Movimiento, String> {
  /*
    @Query("SELECT m FROM movimiento m WHERE m.cuentaID =: id AND CAST(m.fechaHora AS DATE) < DATE_ADD (CURDATE, INTERVAL 1 MONTH)")
    List<Movimiento> filterByDate(@Param("id") String id);
   */
  List<Movimiento> findByCuentaOrderByFecha(Cuenta cuenta);
}
