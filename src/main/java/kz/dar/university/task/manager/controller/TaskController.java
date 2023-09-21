package kz.dar.university.task.manager.controller;

import kz.dar.university.task.manager.domain.dto.TaskDTO;
import kz.dar.university.task.manager.domain.dto.TaskResponse;
import kz.dar.university.task.manager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    // GET localhost:8080/task/user/abc123

    private final TaskService taskService;

    @GetMapping("/check")
    public String check() {
        return "task-manager is working";
    }

    @GetMapping("/all")
    public List<TaskResponse> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{taskId}")
    public TaskResponse getTaskById(@PathVariable String taskId) {
        return taskService.getTaskById(taskId);
    }

    @GetMapping("/user/{userId}")
    public List<TaskResponse> getTasksByUserId(@PathVariable String userId) {
        return taskService.getTasksByUserId(userId);
    }

    @PostMapping
    public void createTask(@RequestBody @Valid TaskDTO taskDTO) {
        taskService.createTask(taskDTO);
    }

    @PutMapping
    public void updateTask(@RequestBody TaskDTO taskDTO) {
        taskService.updateTask(taskDTO);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTaskById(@PathVariable String taskId) {
        taskService.deleteTaskById(taskId);
    }

}
