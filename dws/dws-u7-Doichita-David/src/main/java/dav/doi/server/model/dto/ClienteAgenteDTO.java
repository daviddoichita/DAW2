package dav.doi.server.model.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteAgenteDTO {

    private Long id;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fechaAsignacion;
    private String tipoRelacion;
    private ClienteDTO clienteDTO;
    private AgenteDTO agenteDTO;

    public static ClienteAgenteDTO convertToDTO(dav.doi.server.repository.entity.ClienteAgente clienteAgente,
            ClienteDTO clienteDTO, AgenteDTO agenteDTO) {
        return ClienteAgenteDTO.builder()
                .id(clienteAgente.getId())
                .fechaAsignacion(clienteAgente.getFechaAsignacion())
                .tipoRelacion(clienteAgente.getTipoRelacion())
                .clienteDTO(clienteDTO)
                .agenteDTO(agenteDTO)
                .build();
    }
}
