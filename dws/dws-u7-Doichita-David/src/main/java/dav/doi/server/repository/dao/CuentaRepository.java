package dav.doi.server.repository.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dav.doi.server.repository.entity.Cuenta;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    @Query(value = "SELECT * FROM cuentas WHERE idcliente = :id", nativeQuery = true)
    public Set<Cuenta> findAllByCliente(@Param("id") Long idCliente);
}
