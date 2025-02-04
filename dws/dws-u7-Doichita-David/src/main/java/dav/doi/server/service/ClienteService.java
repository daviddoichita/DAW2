package dav.doi.server.service;

import java.util.List;

import dav.doi.server.model.dto.ClienteDTO;

public interface ClienteService {

    public List<ClienteDTO> findAll();

    public List<ClienteDTO> findByApellidos(String apellidos);

    public ClienteDTO findById(Long id);

    public ClienteDTO save(ClienteDTO clienteDTO);

    public void deleteById(Long id);
}
