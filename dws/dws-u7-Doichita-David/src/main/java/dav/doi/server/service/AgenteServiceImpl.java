package dav.doi.server.service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dav.doi.server.model.dto.AgenteDTO;
import dav.doi.server.model.dto.ClienteAgenteDTO;
import dav.doi.server.model.dto.ClienteDTO;
import dav.doi.server.repository.dao.AgenteRepository;
import dav.doi.server.repository.dao.ClienteAgenteRepository;
import dav.doi.server.repository.dao.ClienteRepository;
import dav.doi.server.repository.entity.Agente;
import dav.doi.server.repository.entity.Cliente;
import dav.doi.server.repository.entity.ClienteAgente;

@Service
public class AgenteServiceImpl implements AgenteService {

    private static final Logger log = LoggerFactory.getLogger(AgenteServiceImpl.class);

    @Autowired
    private AgenteRepository agenteRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteAgenteRepository clienteAgenteRepository;

    @Override
    public List<ClienteAgenteDTO> findAllByCliente(Long idCliente) {
        log.info(
                this.getClass().getSimpleName()
                        + " findAllByCliente: devolver todos los agentes asociados al cliente con id: {}",
                idCliente);

        Cliente cliente = this.clienteRepository.findById(idCliente).orElse(null);
        List<ClienteAgente> listaClienteAgente = this.clienteAgenteRepository.findByClienteId(idCliente);
        List<ClienteAgenteDTO> listaClienteAgenteDTO = listaClienteAgente.stream()
                .map(c -> ClienteAgenteDTO.convertToDTO(c, ClienteDTO.convertToDTO(cliente),
                        AgenteDTO.convertToDTO(c.getAgente())))
                .collect(Collectors.toList());

        return listaClienteAgenteDTO;
    }

    @Override
    public List<AgenteDTO> findAll() {
        log.info(this.getClass().getSimpleName() + " findAll: devolver todos los agentes");

        return this.agenteRepository.findAll().stream().map(AgenteDTO::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public AgenteDTO findById(Long id) {
        log.info(this.getClass().getSimpleName() + " findById: devolver el agente con id: {}", id);

        return AgenteDTO.convertToDTO(this.agenteRepository.findById(id).orElse(null));
    }

    @Override
    public void save(AgenteDTO agenteDTO, Long idCliente) {
        log.info(this.getClass().getSimpleName() + " save: guardar agente con datos: {}", agenteDTO);

        Set<ClienteAgente> listaClienteAgente = this.clienteAgenteRepository.findByClienteId(idCliente).stream()
                .collect(Collectors.toSet());
        Agente agente = AgenteDTO.convertToEntity(agenteDTO, listaClienteAgente);
        this.agenteRepository.save(agente);

        if (agenteDTO.getId() == null) {

            Cliente cliente = this.clienteRepository.findById(idCliente).orElse(null);
            cliente.getListaClienteAgente().add(ClienteAgente.builder().cliente(cliente).agente(agente)
                    .fechaAsignacion(new Date()).tipoRelacion("Venta").build());
            this.clienteRepository.save(cliente);
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info(this.getClass().getSimpleName() + " deleteById: borrar agente con id: {}", id);

        this.agenteRepository.deleteById(id);
    }

}
