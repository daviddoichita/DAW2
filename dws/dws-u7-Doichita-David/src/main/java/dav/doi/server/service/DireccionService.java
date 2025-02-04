package dav.doi.server.service;

import java.util.List;

import dav.doi.server.model.dto.ClienteDireccionDTO;
import dav.doi.server.model.dto.DireccionDTO;

public interface DireccionService {

    public List<ClienteDireccionDTO> findAllByCliente(Long idCliente);

    public List<DireccionDTO> findAll();

    public DireccionDTO findById(Long id);

    public void save(DireccionDTO direccionDTO, Long idCliente);

    public void addExistente(DireccionDTO direccionDTO, Long idCliente);

    public void deleteById(Long id);
}
