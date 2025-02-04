package dav.doi.server.service;

import java.util.List;

import dav.doi.server.model.dto.ClienteMetodoPagoDTO;
import dav.doi.server.model.dto.MetodoPagoDTO;

public interface MetodoPagoService {

    public List<ClienteMetodoPagoDTO> findAllByCliente(Long idCliente);

    public List<MetodoPagoDTO> findAll();

    public MetodoPagoDTO findById(Long id);

    public void save(MetodoPagoDTO metodoPagoDTO, Long idCliente);

    public void deleteById(Long id);
}
