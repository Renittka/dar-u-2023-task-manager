package kz.dar.university.task.manager.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientDTO {

    private String clientId;
    private String name;
    private String surname;
    private String email;

}
