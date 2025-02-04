package dav.doi.server.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dav.doi.server.model.dto.MovimientoDTO;
import dav.doi.server.service.ClienteService;
import dav.doi.server.service.CuentaService;
import dav.doi.server.service.MovimientoService;

@Controller
public class MovimientoController {

    private static final Logger log = LoggerFactory.getLogger(MovimientoController.class);

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private MovimientoService movimientoService;

    @GetMapping("/clientes/{idCliente}/cuentas/{idCuenta}/movimientos")
    public ModelAndView findAll(@PathVariable Long idCliente, @PathVariable Long idCuenta) {
        log.info(this.getClass().getSimpleName() + " findAll: devolver todos los movimientos");

        return new ModelAndView("movimientos")
                .addObject("movimientosOrigen", this.movimientoService.findAllByCuentaOrigen(idCuenta))
                .addObject("movimientosDestino", this.movimientoService.findAllByCuentaDestino(idCuenta))
                .addObject("cuentaDTO", this.cuentaService.findById(idCuenta))
                .addObject("clienteDTO", this.clienteService.findById(idCliente));
    }

    @GetMapping("/clientes/{idCliente}/cuentas/{idCuenta}/movimientos/{idMovimiento}")
    public ModelAndView findById(@PathVariable Long idCliente, @PathVariable Long idCuenta,
            @PathVariable Long idMovimiento) {
        log.info(this.getClass().getSimpleName() + " findById: devolver movimiento con id: {}", idMovimiento);

        return new ModelAndView("movimientoshow")
                .addObject("movimientoDTO", this.movimientoService.findById(idMovimiento))
                .addObject("cuentaDTO", this.cuentaService.findById(idCuenta))
                .addObject("clienteDTO", this.clienteService.findById(idCliente));
    }

    @PostMapping("/clientes/{idCliente}/cuentas/{idCuenta}/movimientos/save")
    public ModelAndView save(@PathVariable Long idCliente, @PathVariable Long idCuenta,
            @ModelAttribute MovimientoDTO movimientoDTO, @RequestParam Long idCuentaOrigen,
            @RequestParam Long idCuentaDestino) {
        log.info(this.getClass().getSimpleName() + " save: guardar movimiento con datos: {}", movimientoDTO);

        this.movimientoService.save(movimientoDTO, idCuentaOrigen, idCuentaDestino);

        return new ModelAndView("redirect:/clientes/" + idCliente + "/cuentas/" + idCuenta + "/movimientos");
    }

    @GetMapping("/clientes/{idCliente}/cuentas/{idCuenta}/movimientos/add")
    public ModelAndView update(@PathVariable Long idCliente, @PathVariable Long idCuenta) {
        log.info(this.getClass().getSimpleName() + " add: redirigir a nuevo movimiento para cuenta con id: {}",
                idCuenta);

        return new ModelAndView("movimientoform").addObject("movimientoDTO", new MovimientoDTO())
                .addObject("cuentaDTO", this.cuentaService.findById(idCuenta))
                .addObject("clienteDTO", this.clienteService.findById(idCliente))
                .addObject("cuentas", this.cuentaService.findAllByCliente(idCliente))
                .addObject("cuentasReverse", this.cuentaService.findAllByCliente(idCliente).reversed())
                .addObject("add", true);
    }

    @GetMapping("/clientes/{idCliente}/cuentas/{idCuenta}/movimientos/update/{idMovimiento}")
    public ModelAndView update(@PathVariable Long idCliente, @PathVariable Long idCuenta,
            @PathVariable Long idMovimiento) {
        log.info(this.getClass().getSimpleName() + " update: redirigir a actualizar movimiento con id: {}",
                idMovimiento);

        return new ModelAndView("movimientoform")
                .addObject("movimientoDTO", this.movimientoService.findById(idMovimiento))
                .addObject("cuentaDTO", this.cuentaService.findById(idCuenta))
                .addObject("clienteDTO", this.clienteService.findById(idCliente))
                .addObject("cuentas", this.cuentaService.findAllByCliente(idCliente))
                .addObject("cuentasReverse", this.cuentaService.findAllByCliente(idCliente).reversed())
                .addObject("add", false);
    }

    @GetMapping("/clientes/{idCliente}/cuentas/{idCuenta}/movimientos/delete/{idMovimiento}")
    public ModelAndView deleteById(@PathVariable Long idCliente, @PathVariable Long idCuenta,
            @PathVariable Long idMovimiento) {
        log.info(this.getClass().getSimpleName() + " deleteById: borrar movimiento con id: {}", idMovimiento);

        this.movimientoService.deleteById(idMovimiento);

        return new ModelAndView("redirect:/clientes/" + idCliente + "/cuentas/" + idCuenta + "/movimientos");
    }
}
