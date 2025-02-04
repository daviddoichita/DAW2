package dav.doi.server.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dav.doi.server.model.dto.CuentaDTO;
import dav.doi.server.repository.dao.ClienteRepository;
import dav.doi.server.repository.dao.CuentaRepository;
import dav.doi.server.repository.dao.MovimientoRepository;
import dav.doi.server.repository.entity.Cliente;
import dav.doi.server.repository.entity.Movimiento;

@Service
public class CuentaServiceImpl implements CuentaService {

    private static final Logger log = LoggerFactory.getLogger(CuentaServiceImpl.class);

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Override
    public List<CuentaDTO> findAllByCliente(Long idCliente) {
        log.info(this.getClass().getSimpleName()
                + " findAllByCliente: devolver todas las cuentas del cliente con id: {}", idCliente);

        return this.cuentaRepository.findAllByCliente(idCliente).stream().map(c -> CuentaDTO.convertToDTO(c))
                .collect(Collectors.toList());
    }

    @Override
    public CuentaDTO findById(Long id) {
        log.info(this.getClass().getSimpleName() + " findById: devolver cuenta con id: {}", id);

        return this.cuentaRepository.findById(id).map(CuentaDTO::convertToDTO).orElse(null);
    }

    @Override
    public void save(CuentaDTO cuentaDTO, Long idCliente) {
        log.info(this.getClass().getSimpleName() + " save: guardar cuenta con datos: {}", cuentaDTO);

        if (cuentaDTO.getId() != null) {
            Set<Movimiento> movimientosOrigen = this.movimientoRepository.findAllByCuentaOrigenId(cuentaDTO.getId())
                    .stream().collect(Collectors.toSet());
            Set<Movimiento> movimientosDestino = this.movimientoRepository.findAllByCuentaDestinoId(cuentaDTO.getId())
                    .stream().collect(Collectors.toSet());
            Cliente cliente = this.clienteRepository.findById(idCliente).orElse(null);

            this.cuentaRepository
                    .save(CuentaDTO.convertToEntity(cuentaDTO, cliente, movimientosOrigen, movimientosDestino));

        } else {
            this.cuentaRepository
                    .save(CuentaDTO.convertToEntity(cuentaDTO,
                            this.clienteRepository.findById(idCliente).orElse(null), new HashSet<>(), new HashSet<>()));
        }

    }

    @Override
    public void deleteById(Long id) {
        log.info(this.getClass().getSimpleName() + " deleteById: borrar cuenta con id: {}", id);

        this.cuentaRepository.deleteById(id);
    }
}
