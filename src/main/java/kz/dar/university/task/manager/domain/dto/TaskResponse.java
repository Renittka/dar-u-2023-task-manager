package kz.dar.university.task.manager.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {

    private String taskId;
    private ClientDTO client;
    private String title;
    private String status;

}
