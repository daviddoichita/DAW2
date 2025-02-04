package dav.doi.server.service;

import java.util.List;

import dav.doi.server.model.dto.AgenteDTO;
import dav.doi.server.model.dto.ClienteAgenteDTO;

public interface AgenteService {

    public List<ClienteAgenteDTO> findAllByCliente(Long idCliente);

    public List<AgenteDTO> findAll();

    public AgenteDTO findById(Long id);

    public void save(AgenteDTO agenteDTO, Long idCliente);

    public void deleteById(Long id);
}
