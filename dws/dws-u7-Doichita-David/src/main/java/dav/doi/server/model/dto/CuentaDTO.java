package dav.doi.server.model.dto;

import java.util.Set;

import dav.doi.server.repository.entity.Cliente;
import dav.doi.server.repository.entity.Cuenta;
import dav.doi.server.repository.entity.Movimiento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaDTO {

    private Long id;

    private String banco;

    private String sucursal;

    private String dc;

    private String numeroCuenta;

    private float saldoActual;

    public static CuentaDTO convertToDTO(Cuenta cuenta) {
        return new CuentaDTO(cuenta.getId(), cuenta.getBanco(), cuenta.getSucursal(), cuenta.getDc(),
                cuenta.getNumeroCuenta(), cuenta.getSaldoActual());
    }

    public static Cuenta convertToEntity(CuentaDTO cuentaDTO, Cliente cliente, Set<Movimiento> movimientosOrigen,
            Set<Movimiento> movimientosDestino) {
        return new Cuenta(cuentaDTO.getId(), cuentaDTO.getBanco(), cuentaDTO.getSucursal(), cuentaDTO.getDc(),
                cuentaDTO.getNumeroCuenta(), cuentaDTO.getSaldoActual(), cliente, movimientosDestino,
                movimientosOrigen);
    }
}
