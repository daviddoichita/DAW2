package dav.doi.server.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dav.doi.server.repository.entity.Movimiento;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    @Query(value = "SELECT * FROM movimientos WHERE idcuentaorigen = ?1", nativeQuery = true)
    public List<Movimiento> findAllByCuentaOrigenId(Long idCuentaOrigen);

    @Query(value = "SELECT * FROM movimientos WHERE idcuentadestino = ?1", nativeQuery = true)
    public List<Movimiento> findAllByCuentaDestinoId(Long idCuentaDestino);

    @Query(value = "SELECT * FROM movimientos WHERE idcuentaorigen = ?1 OR idcuentadestino = ?1", nativeQuery = true)
    public List<Movimiento> findAllByCuenta(Long idCuenta);
}