package kz.dar.university.task.manager.domain.dto;

import kz.dar.university.task.manager.util.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskDTO {

    private String taskId;
    @NotNull
    private String userId;
    private String title;
    private TaskStatus status;

}
