package dav.doi.server.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dav.doi.server.model.dto.MovimientoDTO;
import dav.doi.server.repository.dao.CuentaRepository;
import dav.doi.server.repository.dao.MovimientoRepository;
import dav.doi.server.repository.entity.Cuenta;

@Service
public class MovimientoServiceImpl implements MovimientoService {

    private static final Logger log = LoggerFactory.getLogger(MovimientoServiceImpl.class);

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public List<MovimientoDTO> findAllByCuentaOrigen(Long idCuenta) {
        log.info(this.getClass().getSimpleName()
                + " findAllByCuentaOrigen: devolver todos los movimientos origen de la cuenta con id: {}", idCuenta);

        return this.movimientoRepository.findAllByCuentaOrigenId(idCuenta).stream().map(MovimientoDTO::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovimientoDTO> findAllByCuentaDestino(Long idCuenta) {
        log.info(this.getClass().getSimpleName()
                + " findAllByCuentaDestino: devolver todos los movimientos destino de la cuenta con id: {}", idCuenta);

        return this.movimientoRepository.findAllByCuentaDestinoId(idCuenta).stream().map(MovimientoDTO::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MovimientoDTO findById(Long id) {
        log.info(this.getClass().getSimpleName() + " findById: devolver movimiento con id: {}", id);

        return this.movimientoRepository.findById(id).map(MovimientoDTO::convertToDTO).orElse(null);
    }

    @Override
    public void save(MovimientoDTO movimientoDTO, Long idCuentaOrigen, Long idCuentaDestino) {
        log.info(this.getClass().getSimpleName() + " save: guardar movimiento con datos: {}", movimientoDTO);

        Cuenta cuentaOrigen = this.cuentaRepository.findById(idCuentaOrigen).orElse(null);
        Cuenta cuentaDestino = this.cuentaRepository.findById(idCuentaDestino).orElse(null);

        movimientoDTO.setFechaOperacion(new Date());

        this.movimientoRepository.save(MovimientoDTO.convertToEntity(movimientoDTO, cuentaOrigen, cuentaDestino));
    }

    @Override
    public void deleteById(Long id) {
        log.info(this.getClass().getSimpleName() + " delete: borrar movimiento con id: {}", id);

        this.movimientoRepository.deleteById(id);
    }

}
