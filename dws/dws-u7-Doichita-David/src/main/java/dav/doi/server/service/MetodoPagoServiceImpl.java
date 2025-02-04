package dav.doi.server.service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dav.doi.server.model.dto.ClienteDTO;
import dav.doi.server.model.dto.ClienteMetodoPagoDTO;
import dav.doi.server.model.dto.MetodoPagoDTO;
import dav.doi.server.repository.dao.ClienteMetodoPagoRepository;
import dav.doi.server.repository.dao.ClienteRepository;
import dav.doi.server.repository.dao.MetodoPagoRepository;
import dav.doi.server.repository.entity.Cliente;
import dav.doi.server.repository.entity.ClienteMetodoPago;
import dav.doi.server.repository.entity.MetodoPago;

@Service
public class MetodoPagoServiceImpl implements MetodoPagoService {

    private static final Logger log = LoggerFactory.getLogger(MetodoPagoServiceImpl.class);

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMetodoPagoRepository clienteMetodoPagoRepository;

    @Override
    public List<ClienteMetodoPagoDTO> findAllByCliente(Long idCliente) {
        log.info(
                this.getClass().getSimpleName()
                        + " findAllByCliente: devolver todos los metodos de pago asociados al cliente con id: {}",
                idCliente);

        Cliente cliente = this.clienteRepository.findById(idCliente).orElse(null);
        List<ClienteMetodoPago> listaClienteMetodoPago = this.clienteMetodoPagoRepository.findByClienteId(idCliente);
        List<ClienteMetodoPagoDTO> listaClienteMetodoPagoDTO = listaClienteMetodoPago.stream()
                .map(c -> ClienteMetodoPagoDTO.convertToDTO(c, ClienteDTO.convertToDTO(cliente),
                        MetodoPagoDTO.convertToDTO(c.getMetodoPago())))
                .collect(Collectors.toList());

        return listaClienteMetodoPagoDTO;
    }

    @Override
    public List<MetodoPagoDTO> findAll() {
        log.info(this.getClass().getSimpleName() + " findAll: devolver todos los metodos de pago");

        return this.metodoPagoRepository.findAll().stream().map(MetodoPagoDTO::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MetodoPagoDTO findById(Long id) {
        log.info(this.getClass().getSimpleName() + " findById: devolver metodo de pago con id: {}", id);

        return MetodoPagoDTO.convertToDTO(this.metodoPagoRepository.findById(id).orElse(null));
    }

    @Override
    public void save(MetodoPagoDTO metodoPagoDTO, Long idCliente) {
        log.info(this.getClass().getSimpleName() + " save: guardar metodo de pago con datos: {}", metodoPagoDTO);

        Set<ClienteMetodoPago> listaClienteMetodosPago = this.clienteMetodoPagoRepository.findByClienteId(idCliente)
                .stream().collect(Collectors.toSet());
        MetodoPago metodoPago = MetodoPagoDTO.convertToEntity(metodoPagoDTO, listaClienteMetodosPago);
        this.metodoPagoRepository.save(metodoPago);

        if (metodoPagoDTO.getId() == null) {

            Cliente cliente = this.clienteRepository.findById(idCliente).orElse(null);
            cliente.getListaClienteMetodoPago().add(ClienteMetodoPago.builder().cliente(cliente).metodoPago(metodoPago)
                    .fechaAlta(new Date()).predeterminado(false).build());
            this.clienteRepository.save(cliente);
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info(this.getClass().getSimpleName() + " deleteById: borrar metodo de pago con id: {}", id);

        this.metodoPagoRepository.deleteById(id);
    }
}
