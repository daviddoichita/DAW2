package dav.doi.server.repository.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dav.doi.server.repository.entity.ClienteAgente;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface ClienteAgenteRepository extends JpaRepository<ClienteAgente, Long> {

    @Query("SELECT c FROM ClienteAgente c WHERE c.cliente.id = :idcliente AND c.agente.id = :idagente")
    Optional<ClienteAgente> findByClienteIdAndAgenteId(@Param("idcliente") Long idCliente,
            @Param("idagente") Long idAgente);

    @Query("SELECT c FROM ClienteAgente c WHERE c.cliente.id = :idcliente")
    List<ClienteAgente> findByClienteId(@Param("idcliente") Long idCliente);
}
