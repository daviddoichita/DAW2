package dav.doi.server.service;

import java.util.List;

import dav.doi.server.model.dto.CuentaDTO;

public interface CuentaService {

    public List<CuentaDTO> findAllByCliente(Long idCliente);

    public CuentaDTO findById(Long id);

    public void save(CuentaDTO cuentaDTO, Long idCliente);

    public void deleteById(Long id);
}
