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

import dav.doi.server.model.dto.ClienteDTO;
import dav.doi.server.service.ClienteService;

@Controller
public class ClienteController {

    private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    public ModelAndView findAll() {
        log.info(this.getClass().getSimpleName() + " findAll: devolver todos los cliente");

        return new ModelAndView("clientes").addObject("listaClientesDTO", this.clienteService.findAll());
    }

    @GetMapping("/clientes/{id}")
    public ModelAndView findById(@PathVariable Long id) {
        log.info(this.getClass().getSimpleName() + " findById: devolver cliente con id: {}", id);

        return new ModelAndView("clienteshow").addObject("clienteDTO", this.clienteService.findById(id));
    }

    @PostMapping("clientes/save")
    public ModelAndView save(@ModelAttribute ClienteDTO clienteDTO) {
        log.info(this.getClass().getSimpleName() + " save: guardar cliente con datos: {}", clienteDTO);

        this.clienteService.save(clienteDTO);

        return new ModelAndView("redirect:/clientes");
    }

    @GetMapping("clientes/add")
    public ModelAndView add() {
        log.info(this.getClass().getSimpleName() + " add: redigir a vista nuevo cliente");

        return new ModelAndView("clienteform").addObject("clienteDTO", new ClienteDTO()).addObject("add", true);
    }

    @GetMapping("clientes/update/{id}")
    public ModelAndView update(@PathVariable Long id) {
        log.info(this.getClass().getSimpleName() + " update: redigir a vista actualizar cliente con id: {}", id);

        return new ModelAndView("clienteform").addObject("clienteDTO", this.clienteService.findById(id))
                .addObject("add", false);
    }

    @GetMapping("clientes/delete/{id}")
    public ModelAndView deleteById(@PathVariable Long id) {
        log.info(this.getClass().getSimpleName() + " deleteById: borrar cliente con id: {}", id);

        this.clienteService.deleteById(id);

        return new ModelAndView("redirect:/clientes");
    }
}
