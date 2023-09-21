package kz.dar.university.task.manager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import kz.dar.university.task.manager.domain.dto.TaskDTO;
import kz.dar.university.task.manager.domain.dto.TaskResponse;

import java.util.List;

public interface TaskService {

    List<TaskResponse> getAllTasks();

    TaskResponse getTaskById(String taskId);

    List<TaskResponse> getTasksByUserId(String clientId);

    void createTask(TaskDTO taskDTO) throws JsonProcessingException;

    void updateTask(TaskDTO taskDTO);

    void deleteTaskById(String taskId);

}
