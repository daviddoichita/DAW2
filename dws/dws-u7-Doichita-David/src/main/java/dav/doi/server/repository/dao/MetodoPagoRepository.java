package dav.doi.server.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dav.doi.server.repository.entity.MetodoPago;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Long> {

    @Query(value = "SELECT A.* from metodopago A, clientemetodopago B WHERE A.id = B.idmetodopago AND B.idcliente = :idcliente", nativeQuery = true)
    public List<MetodoPago> findAllByCliente(@Param("idcliente") Long idCliente);
}
