package kz.dar.university.task.manager.service;

import kz.dar.university.task.manager.domain.dto.ClientDTO;
import kz.dar.university.task.manager.domain.dto.EmailDTO;
import kz.dar.university.task.manager.domain.model.Task;
import kz.dar.university.task.manager.domain.dto.TaskDTO;
import kz.dar.university.task.manager.domain.dto.TaskResponse;
import kz.dar.university.task.manager.feign.ClientClient;
import kz.dar.university.task.manager.repository.TaskRepository;
import kz.dar.university.task.manager.util.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final ClientClient clientClient;
    private final MessageSender messageSender;

    private final ModelMapper mapper = new ModelMapper();

    {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public List<TaskResponse> getAllTasks() {

        List<Task> taskList = taskRepository.getTasksBy();

//        for (Task task : taskList) {
//            mapper.map(task, TaskResponse.class);
//        }

        // 1 2 3 4 5 6 7 8 9 10

        // convert List<Task> to List<TaskResponse>
        /*
        List<TaskResponse> taskResponseList = new ArrayList<>();
        taskList.forEach(task -> {
                            // Task -> TaskResponse
                            TaskResponse response = mapper.map(task, TaskResponse.class);

                            // userId -> client-core-api -> Info ClientDTO
                            ClientDTO clientDTO = clientClient.getClientById(task.getUserId());

                            // set Client
                            response.setClient(clientDTO);
                            taskResponseList.add(response);
                        }
                );
        return taskResponseList;
         */

        return taskList.stream()
                .map(task -> {
                            // Task -> TaskResponse
                            TaskResponse response = mapper.map(task, TaskResponse.class);

                            // userId -> client-core-api -> Info ClientDTO
                            ClientDTO clientDTO = clientClient.getClientById(task.getUserId());

                            // set Client
                            response.setClient(clientDTO);

                            return response;
                        }
                )
                .toList();
    }

    @Override
    public TaskResponse getTaskById(String taskId) {
        Task task = taskRepository.getTaskByTaskId(taskId);

        // Task -> TaskResponse
        TaskResponse response = mapper.map(task, TaskResponse.class);

        // userId -> client-core-api -> Info ClientDTO
        ClientDTO clientDTO = clientClient.getClientById(task.getUserId());

        // set Client
        response.setClient(clientDTO);

        return response;
    }

    @Override
    public List<TaskResponse> getTasksByUserId(String clientId) {

        List<Task> taskList = taskRepository.getTasksByUserId(clientId);

        return taskList.stream()
                .map(task -> {
                            // Task -> TaskResponse
                            TaskResponse response = mapper.map(task, TaskResponse.class);

                            // userId -> client-core-api -> Info ClientDTO
                            ClientDTO clientDTO = clientClient.getClientById(task.getUserId());

                            // set Client
                            response.setClient(clientDTO);

                            return response;
                        }
                )
                .toList();
    }

    @Override
    public void createTask(TaskDTO taskDTO) {
        taskDTO.setTaskId(UUID.randomUUID().toString());
        taskDTO.setStatus(TaskStatus.TO_DO);

        Task task = mapper.map(taskDTO, Task.class);
        task.setStatus(taskDTO.getStatus().getTitle());

        taskRepository.save(task);

        EmailDTO email = new EmailDTO(
                "email",
                task.getTitle(),
                String.format("New task: %s, status: %s", task.getTitle(), task.getStatus())
        );
        messageSender.sendMessage(email);
    }

    @Override
    public void updateTask(TaskDTO taskDTO) {
        Task task = mapper.map(taskDTO, Task.class);
        task.setStatus(taskDTO.getStatus().getTitle());

        if (taskRepository.existsById(task.getTaskId())) {
            taskRepository.save(task);
        }

    }

    @Override
    public void deleteTaskById(String taskId) {
        taskRepository.deleteTaskByTaskId(taskId);
    }
}
