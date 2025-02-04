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

import dav.doi.server.model.dto.DireccionDTO;
import dav.doi.server.service.ClienteService;
import dav.doi.server.service.DireccionService;

@Controller
public class DireccionController {

        private static final Logger log = LoggerFactory.getLogger(DireccionController.class);

        @Autowired
        private DireccionService direccionService;

        @Autowired
        private ClienteService clienteService;

        @GetMapping("/clientes/{idCliente}/direcciones")
        public ModelAndView findAllByCliente(@PathVariable Long idCliente) {
                log.info(this.getClass().getSimpleName()
                                + " findAllByCliente: devolver todas las direcciones asociadas al cliente con id: {}",
                                idCliente);

                return new ModelAndView("direcciones")
                                .addObject("listaDireccionesDTO", this.direccionService.findAllByCliente(idCliente))
                                .addObject("clienteDTO", this.clienteService.findById(idCliente));
        }

        @GetMapping("/clientes/{idCliente}/direcciones/all")
        public ModelAndView findAll(@PathVariable Long idCliente) {
                log.info(this.getClass().getSimpleName()
                                + " findAll: devolver todas las direcciones para la vista nueva direccion desde existente");

                return new ModelAndView("direccionexistente")
                                .addObject("listaDireccionesDTO", this.direccionService.findAll())
                                .addObject("clienteDTO", this.clienteService.findById(idCliente))
                                .addObject("direccionDTO", new DireccionDTO());
        }

        @GetMapping("/clientes/{idCliente}/direcciones/update")
        public ModelAndView addDireccionExistente(@PathVariable Long idCliente,
                        @ModelAttribute DireccionDTO direccionDTO) {
                log.info(this.getClass().getSimpleName()
                                + " addDireccionExistente: guardar direccion existente: {} para el cliente con id: {}",
                                direccionDTO, idCliente);

                direccionDTO = this.direccionService.findById(direccionDTO.getId());

                this.direccionService.addExistente(direccionDTO, idCliente);

                return new ModelAndView("redirect:/clientes/" + idCliente + "/direcciones");
        }

        @GetMapping("/clientes/{idCliente}/direcciones/{id}")
        public ModelAndView findById(@PathVariable Long idCliente, @PathVariable Long id) {
                log.info(this.getClass().getSimpleName() + " findById: devolver direccion con id: {}", id);

                return new ModelAndView("direccionshow")
                                .addObject("direccionDTO", this.direccionService.findById(id))
                                .addObject("clienteDTO", this.clienteService.findById(idCliente));
        }

        @PostMapping("/clientes/{idCliente}/direcciones/save")
        public ModelAndView save(@PathVariable Long idCliente, @ModelAttribute DireccionDTO direccionDTO) {
                log.info(this.getClass().getSimpleName() + " save: guardar direccion con datos: {}", direccionDTO);

                this.direccionService.save(direccionDTO, idCliente);

                return new ModelAndView("redirect:/clientes/" + idCliente + "/direcciones");
        }

        @GetMapping("/clientes/{idCliente}/direcciones/add")
        public ModelAndView add(@PathVariable Long idCliente) {
                log.info(this.getClass().getSimpleName() + " add: redigir a vista nueva direccion");

                return new ModelAndView("direccionform")
                                .addObject("direccionDTO", new DireccionDTO())
                                .addObject("clienteDTO", this.clienteService.findById(idCliente))
                                .addObject("add", true);
        }

        @GetMapping("/clientes/{idCliente}/direcciones/update/{id}")
        public ModelAndView update(@PathVariable Long idCliente, @PathVariable Long id) {
                log.info(this.getClass().getSimpleName() + " update: redigir a vista actualizar direccion con id: {}",
                                id);

                return new ModelAndView("direccionform")
                                .addObject("direccionDTO", this.direccionService.findById(id))
                                .addObject("clienteDTO", this.clienteService.findById(idCliente))
                                .addObject("add", false);
        }
}
