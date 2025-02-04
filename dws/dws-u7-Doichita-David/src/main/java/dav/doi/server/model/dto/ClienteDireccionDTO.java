package dav.doi.server.model.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import dav.doi.server.repository.entity.ClienteDireccion;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteDireccionDTO {

    private Long id;
    private ClienteDTO clienteDTO;
    private DireccionDTO direccionDTO;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaAlta;

    public static ClienteDireccionDTO convertToDTO(ClienteDireccion clienteDireccion, ClienteDTO clienteDTO,
            DireccionDTO direccionDTO) {
        return ClienteDireccionDTO.builder()
                .id(clienteDireccion.getId())
                .clienteDTO(clienteDTO)
                .direccionDTO(direccionDTO)
                .fechaAlta(clienteDireccion.getFechaAlta())
                .build();
    }

}