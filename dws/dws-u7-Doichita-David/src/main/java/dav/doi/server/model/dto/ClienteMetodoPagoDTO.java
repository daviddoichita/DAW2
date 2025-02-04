package dav.doi.server.model.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import dav.doi.server.repository.entity.ClienteMetodoPago;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteMetodoPagoDTO {

    private Long id;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaAlta;
    private Boolean predeterminado;
    private ClienteDTO clienteDTO;
    private MetodoPagoDTO metodoPagoDTO;

    public static ClienteMetodoPagoDTO convertToDTO(ClienteMetodoPago clienteMetodoPago, ClienteDTO clienteDTO,
            MetodoPagoDTO metodoPagoDTO) {
        return ClienteMetodoPagoDTO.builder()
                .id(clienteMetodoPago.getId())
                .fechaAlta(clienteMetodoPago.getFechaAlta())
                .predeterminado(clienteMetodoPago.getPredeterminado())
                .clienteDTO(clienteDTO)
                .metodoPagoDTO(metodoPagoDTO)
                .build();
    }
}
