package dav.doi.server.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import dav.doi.server.model.dto.AgenteDTO;
import dav.doi.server.service.AgenteService;
import dav.doi.server.service.ClienteService;

@Controller
public class AgenteController {

    private static final Logger log = LoggerFactory.getLogger(AgenteController.class);

    @Autowired
    private AgenteService agenteService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes/{idCliente}/agentes")
    public ModelAndView findAllByCliente(@PathVariable Long idCliente) {
        log.info(this.getClass().getSimpleName()
                + " findAllByCliente: devolver todos los agentes asociados al cliente con id: {}",
                idCliente);

        return new ModelAndView("agentes")
                .addObject("listaClienteAgenteDTO", this.agenteService.findAllByCliente(idCliente))
                .addObject("clienteDTO", this.clienteService.findById(idCliente));
    }

    @GetMapping("/clientes/{idCliente}/agentes/{id}")
    public ModelAndView findById(@PathVariable Long idCliente, @PathVariable Long id) {
        log.info(this.getClass().getSimpleName()
                + " findById: devolver el agente con id: {} asociado al cliente con id: {}",
                id, idCliente);

        return new ModelAndView("agenteshow")
                .addObject("agenteDTO", this.agenteService.findById(id))
                .addObject("clienteDTO", this.clienteService.findById(idCliente));
    }

    @PostMapping("/clientes/{idCliente}/agentes/save")
    public ModelAndView save(@PathVariable Long idCliente,
            @ModelAttribute AgenteDTO agenteDTO) {
        log.info(this.getClass().getSimpleName()
                + " save: guardar agente con datos: {} para el cliente con id: {}", agenteDTO, idCliente);

        this.agenteService.save(agenteDTO, idCliente);

        return new ModelAndView("redirect:/clientes/" + idCliente + "/agentes");
    }

    @GetMapping("/clientes/{idCliente}/agentes/add")
    public ModelAndView add(@PathVariable Long idCliente) {
        log.info(this.getClass().getSimpleName()
                + " add: redigir vista nuevo agente para el cliente con id: {}", idCliente);

        return new ModelAndView("agenteform")
                .addObject("agenteDTO", new AgenteDTO())
                .addObject("clienteDTO", this.clienteService.findById(idCliente))
                .addObject("add", true);
    }

    @GetMapping("/clientes/{idCliente}/agentes/update/{id}")
    public ModelAndView update(@PathVariable Long idCliente, @PathVariable Long id) {
        log.info(this.getClass().getSimpleName()
                + " add: redigir vista actualizar agente para el cliente con id: {}", idCliente);

        return new ModelAndView("agenteform")
                .addObject("agenteDTO", this.agenteService.findById(id))
                .addObject("clienteDTO", this.clienteService.findById(idCliente))
                .addObject("add", false);
    }

    @GetMapping("/clientes/{idCliente}/agentes/delete/{id}")
    public ModelAndView delete(@PathVariable Long idCliente, @PathVariable Long id) {
        log.info(this.getClass().getSimpleName()
                + " delete: eliminar agente con id: {} para el cliente con id: {}", id, idCliente);

        this.agenteService.deleteById(id);

        return new ModelAndView("redirect:/clientes/" + idCliente + "/agentes");
    }
}
