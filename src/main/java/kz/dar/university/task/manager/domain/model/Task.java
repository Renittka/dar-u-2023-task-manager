package kz.dar.university.task.manager.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "test-tasks-dar-u")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @Field(type = FieldType.Keyword)
    private String taskId;
    @Field(type = FieldType.Keyword)
    private String userId;
    @Field(type = FieldType.Text)
    private String title;
    @Field(type = FieldType.Text)
    private String status;

}
