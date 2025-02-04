package dav.doi.server.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dav.doi.server.repository.entity.Direccion;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface DireccionRepository extends JpaRepository<Direccion, Long> {

    @Query(value = "SELECT A.* FROM direcciones A, clientesdirecciones B "
            + " WHERE A.ID=B.iddireccion AND B.idcliente = :idcliente", nativeQuery = true)
    public List<Direccion> findAllByCliente(@Param("idcliente") Long idCliente);
}
