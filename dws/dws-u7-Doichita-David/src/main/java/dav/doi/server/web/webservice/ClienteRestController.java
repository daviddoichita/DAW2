package dav.doi.server.web.webservice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public List<ClienteDTO> findAll() {
        log.info(this.getClass().getSimpleName() + " findAll: devolver todos los clientes");

        return this.clienteService.findAll();
    }

    @GetMapping(path = "/{idCliente}")
    public ClienteDTO findById(@PathVariable Long idCliente) {
        log.info(this.getClass().getSimpleName() + " findById: devolver cliente con id: {}", idCliente);

        return this.clienteService.findById(idCliente);
    }
}
