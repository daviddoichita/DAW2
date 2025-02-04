package dav.doi.server.repository.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dav.doi.server.repository.entity.ClienteMetodoPago;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface ClienteMetodoPagoRepository extends JpaRepository<ClienteMetodoPago, Long> {

    @Query("SELECT c FROM ClienteMetodoPago c WHERE c.cliente.id = :idcliente AND c.metodoPago.id = :idmetodopago")
    Optional<ClienteMetodoPago> findByClienteIdAndMetodoPagoId(@Param("idcliente") Long idCliente,
            @Param("idmetodopago") Long idMetodoPago);

    @Query("SELECT c FROM ClienteMetodoPago c WHERE c.cliente.id = :idcliente")
    List<ClienteMetodoPago> findByClienteId(@Param("idcliente") Long idCliente);
}
