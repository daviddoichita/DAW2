package dav.doi.server.model.dto;

import java.util.Set;

import dav.doi.server.repository.entity.ClienteAgente;
import dav.doi.server.repository.entity.Agente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgenteDTO {

    private Long id;
    private String nombre;
    private String apellido;

    public static AgenteDTO convertToDTO(Agente agente) {
        return new AgenteDTO(agente.getId(), agente.getNombre(), agente.getApellido());
    }

    public static Agente convertToEntity(AgenteDTO agenteDTO,
            Set<ClienteAgente> listaClienteAgente) {
        return new Agente(agenteDTO.getId(), agenteDTO.getNombre(),
                agenteDTO.getApellido(), listaClienteAgente);
    }
}
