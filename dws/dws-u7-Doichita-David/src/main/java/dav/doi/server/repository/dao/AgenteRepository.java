package dav.doi.server.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dav.doi.server.repository.entity.Agente;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface AgenteRepository extends JpaRepository<Agente, Long> {

    @Query(value = "SELECT A.* from agente A, clienteagente B WHERE A.id = B.idagente AND B.idcliente = :idcliente", nativeQuery = true)
    public List<Agente> findAllByCliente(@Param("idcliente") Long idCliente);
}
