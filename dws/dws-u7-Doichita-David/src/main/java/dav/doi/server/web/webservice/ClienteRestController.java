package dav.doi.server.web.webservice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dav.doi.server.model.dto.ClienteDTO;
import dav.doi.server.service.ClienteService;

@RestController
@RequestMapping(path = "/ws/clientes")
public class ClienteRestController {

    private static final Logger log = LoggerFactory.getLogger(ClienteRestController.class);

    @Autowired
    private ClienteService clienteService;

    @GetMapping(path = "")
    public ResponseEntity<List<ClienteDTO>> findAll(@RequestParam(defaultValue = "") String apellidos) {
        List<ClienteDTO> listaClientesDTO = null;

        if (apellidos.isBlank()) {
            log.info(this.getClass().getSimpleName() + " findAll: devolver todos los clientes");
            listaClientesDTO = this.clienteService.findAll();
        } else {
            log.info(this.getClass().getSimpleName() + " findAll: devolver todos los clientes con apellidos: {}",
                    apellidos);
            listaClientesDTO = this.clienteService.findByApellidos(apellidos);
        }

        return ResponseEntity.ok(listaClientesDTO);
    }

    @GetMapping(path = "/{idCliente}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long idCliente) {
        log.info(this.getClass().getSimpleName() + " findById: devolver cliente con id: {}", idCliente);

        ClienteDTO clienteDTO = this.clienteService.findById(idCliente);
        if (clienteDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
        }
    }

    @PostMapping(path = "")
    public ResponseEntity<ClienteDTO> add(@RequestBody ClienteDTO clienteDTO) {
        log.info(this.getClass().getSimpleName() + " add: Nuevo cliente con datos: {}", clienteDTO);

        clienteDTO = this.clienteService.save(clienteDTO);
        if (clienteDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
        }
    }

    @PutMapping(path = "")
    public ResponseEntity<ClienteDTO> update(@RequestBody ClienteDTO clienteDTO) {
        log.info(this.getClass().getSimpleName() + " update: modificar cliente con datos: {}", clienteDTO);

        ClienteDTO cDTOExiste = this.clienteService.findById(clienteDTO.getId());
        if (cDTOExiste == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            clienteDTO = this.clienteService.save(clienteDTO);
            return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
        }
    }

    @DeleteMapping(path = "/{idCliente}")
    public ResponseEntity<String> delete(@PathVariable Long idCliente) {
        log.info(this.getClass().getSimpleName() + " delete: borrar cliente con id: {}", idCliente);

        this.clienteService.deleteById(idCliente);

        return new ResponseEntity<>("Cliente borrado correctamente", HttpStatus.OK);
    }

}
