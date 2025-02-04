package dav.doi.server.service;

import java.util.List;

import dav.doi.server.model.dto.ClienteDTO;

public interface ClienteService {

    public List<ClienteDTO> findAll();

    public ClienteDTO findById(Long id);

    public void save(ClienteDTO clienteDTO);

    public void deleteById(Long id);
}
