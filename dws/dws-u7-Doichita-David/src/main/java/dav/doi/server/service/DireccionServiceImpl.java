package dav.doi.server.service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dav.doi.server.model.dto.ClienteDireccionDTO;
import dav.doi.server.model.dto.DireccionDTO;
import dav.doi.server.model.dto.ClienteDTO;
import dav.doi.server.repository.dao.ClienteDireccionRepository;
import dav.doi.server.repository.dao.ClienteRepository;
import dav.doi.server.repository.dao.DireccionRepository;
import dav.doi.server.repository.entity.Cliente;
import dav.doi.server.repository.entity.ClienteDireccion;
import dav.doi.server.repository.entity.Direccion;

@Service
public class DireccionServiceImpl implements DireccionService {

        private static final Logger log = LoggerFactory.getLogger(DireccionServiceImpl.class);

        @Autowired
        private DireccionRepository direccionRepository;

        @Autowired
        private ClienteRepository clienteRepository;

        @Autowired
        private ClienteDireccionRepository clienteDireccionRepository;

        @Override
        public List<ClienteDireccionDTO> findAllByCliente(Long idCliente) {
                log.info(this.getClass().getSimpleName()
                                + " findAllByCliente: devolver todas las direccion asociadas al cliente con id: {}",
                                idCliente);

                Cliente cliente = this.clienteRepository.findById(idCliente).orElse(null);
                List<ClienteDireccion> listaClienteDirecciones = this.clienteDireccionRepository
                                .findByClienteId(idCliente);
                List<ClienteDireccionDTO> listaClienteDireccionesDTO = listaClienteDirecciones.stream()
                                .map(c -> ClienteDireccionDTO.convertToDTO(c, ClienteDTO.convertToDTO(cliente),
                                                DireccionDTO.convertToDTO(c.getDireccion())))
                                .collect(Collectors.toList());

                return listaClienteDireccionesDTO;
        }

        @Override
        public List<DireccionDTO> findAll() {
                log.info(this.getClass().getSimpleName() + " findAll: devolver todas las direcciones");

                return this.direccionRepository.findAll().stream().map(DireccionDTO::convertToDTO)
                                .collect(Collectors.toList());
        }

        @Override
        public DireccionDTO findById(Long id) {
                log.info(this.getClass().getSimpleName() + " findById: devolver direccion con id: {}", id);

                return this.direccionRepository.findById(id).map(DireccionDTO::convertToDTO).orElse(null);
        }

        @Override
        public void save(DireccionDTO direccionDTO, Long idCliente) {
                log.info(this.getClass().getSimpleName() + " save: guardar direccion con datos: {}", direccionDTO);

                Set<ClienteDireccion> listaClienteDirecciones = this.clienteDireccionRepository
                                .findByClienteId(idCliente)
                                .stream().collect(Collectors.toSet());
                Direccion direccion = DireccionDTO.convertToEntity(direccionDTO, listaClienteDirecciones);
                this.direccionRepository.save(direccion);

                if (direccionDTO.getId() == null) {
                        Cliente cliente = this.clienteRepository.findById(idCliente).orElse(null);
                        cliente.getListaClienteDirecciones()
                                        .add(ClienteDireccion.builder().cliente(cliente).direccion(direccion)
                                                        .fechaAlta(new Date()).build());
                        this.clienteRepository.save(cliente);
                }
        }

        @Override
        public void addExistente(DireccionDTO direccionDTO, Long idCliente) {
                log.info(this.getClass().getSimpleName()
                                + " addExistente: anyadir direccion existente a cliente con id: {}",
                                idCliente);

                Set<ClienteDireccion> listaClienteDirecciones = this.clienteDireccionRepository
                                .findByClienteId(idCliente)
                                .stream().collect(Collectors.toSet());
                Direccion direccion = DireccionDTO.convertToEntity(direccionDTO, listaClienteDirecciones);
                this.direccionRepository.save(direccion);

                ClienteDireccion clienteDireccion = this.clienteDireccionRepository.findByClienteIdAndDireccionId(
                                idCliente,
                                direccion.getId()).orElse(
                                                ClienteDireccion.builder()
                                                                .cliente(this.clienteRepository.findById(idCliente)
                                                                                .orElse(null))
                                                                .direccion(direccion).fechaAlta(new java.util.Date())
                                                                .build());
                Cliente cliente = this.clienteRepository.findById(idCliente).orElse(null);
                cliente.getListaClienteDirecciones().add(clienteDireccion);
                this.clienteRepository.save(cliente);
        }

        @Override
        public void deleteById(Long id) {
                log.info(this.getClass().getSimpleName() + " deleteById: borrar direccion con id: {}", id);

                this.direccionRepository.deleteById(id);
        }
}
