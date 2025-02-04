package dav.doi.server.model.dto;

import java.util.Set;

import dav.doi.server.repository.entity.ClienteMetodoPago;
import dav.doi.server.repository.entity.MetodoPago;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetodoPagoDTO {

    private Long id;
    private String descripcion;

    public static MetodoPagoDTO convertToDTO(MetodoPago metodoPago) {
        return new MetodoPagoDTO(metodoPago.getId(), metodoPago.getDescripcion());
    }

    public static MetodoPago convertToEntity(MetodoPagoDTO metodoPagoDTO,
            Set<ClienteMetodoPago> listaClienteMetodosPago) {
        return new MetodoPago(metodoPagoDTO.getId(), metodoPagoDTO.getDescripcion(), listaClienteMetodosPago);
    }
}
