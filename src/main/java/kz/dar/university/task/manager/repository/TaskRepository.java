package kz.dar.university.task.manager.repository;

import kz.dar.university.task.manager.domain.model.Task;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends ElasticsearchRepository<Task, String> {

    List<Task> getTasksByUserId(String userId);

    List<Task> getTasksBy();

    Task getTaskByTaskId(String taskId);

    void deleteTaskByTaskId(String taskId);

}
