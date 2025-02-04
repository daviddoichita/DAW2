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

import dav.doi.server.model.dto.MetodoPagoDTO;
import dav.doi.server.service.ClienteService;
import dav.doi.server.service.MetodoPagoService;

@Controller
public class MetodoPagoController {

    private static final Logger log = LoggerFactory.getLogger(MetodoPagoController.class);

    @Autowired
    private MetodoPagoService metodoPagoService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes/{idCliente}/metodosPago")
    public ModelAndView findAllByCliente(@PathVariable Long idCliente) {
        log.info(this.getClass().getSimpleName()
                + " findAllByCliente: devolver todos los metodos de pago asociados al cliente con id: {}",
                idCliente);

        return new ModelAndView("metodospago")
                .addObject("listaMetodosPagoDTO", this.metodoPagoService.findAllByCliente(idCliente))
                .addObject("clienteDTO", this.clienteService.findById(idCliente));
    }

    @GetMapping("/clientes/{idCliente}/metodosPago/{id}")
    public ModelAndView findById(@PathVariable Long idCliente, @PathVariable Long id) {
        log.info(this.getClass().getSimpleName()
                + " findById: devolver el metodo de pago con id: {} asociado al cliente con id: {}",
                id, idCliente);

        return new ModelAndView("metodopagoshow")
                .addObject("metodoPagoDTO", this.metodoPagoService.findById(id))
                .addObject("clienteDTO", this.clienteService.findById(idCliente));
    }

    @PostMapping("/clientes/{idCliente}/metodosPago/save")
    public ModelAndView save(@PathVariable Long idCliente,
            @ModelAttribute MetodoPagoDTO metodoPagoDTO) {
        log.info(this.getClass().getSimpleName()
                + " save: guardar metodo de pago con datos: {} para el cliente con id: {}", metodoPagoDTO, idCliente);

        this.metodoPagoService.save(metodoPagoDTO, idCliente);

        return new ModelAndView("redirect:/clientes/" + idCliente + "/metodosPago");
    }

    @GetMapping("/clientes/{idCliente}/metodosPago/add")
    public ModelAndView add(@PathVariable Long idCliente) {
        log.info(this.getClass().getSimpleName()
                + " add: redigir vista nuevo metodo de pago para el cliente con id: {}", idCliente);

        return new ModelAndView("metodopagoform")
                .addObject("metodoPagoDTO", new MetodoPagoDTO())
                .addObject("clienteDTO", this.clienteService.findById(idCliente))
                .addObject("add", true);
    }

    @GetMapping("/clientes/{idCliente}/metodosPago/update/{id}")
    public ModelAndView update(@PathVariable Long idCliente, @PathVariable Long id) {
        log.info(this.getClass().getSimpleName()
                + " add: redigir vista actualizar metodo de pago para el cliente con id: {}", idCliente);

        return new ModelAndView("metodopagoform")
                .addObject("metodoPagoDTO", this.metodoPagoService.findById(id))
                .addObject("clienteDTO", this.clienteService.findById(idCliente))
                .addObject("add", false);
    }

    @GetMapping("/clientes/{idCliente}/metodosPago/delete/{id}")
    public ModelAndView delete(@PathVariable Long idCliente, @PathVariable Long id) {
        log.info(this.getClass().getSimpleName()
                + " delete: eliminar metodo de pago con id: {} para el cliente con id: {}", id, idCliente);

        this.metodoPagoService.deleteById(id);

        return new ModelAndView("redirect:/clientes/" + idCliente + "/metodosPago");
    }
}
