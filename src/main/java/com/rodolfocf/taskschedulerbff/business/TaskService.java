package com.rodolfocf.taskschedulerbff.business;


import com.rodolfocf.taskschedulerbff.business.dto.in.TaskDTORequest;
import com.rodolfocf.taskschedulerbff.business.dto.out.TaskDTOResponse;
import com.rodolfocf.taskschedulerbff.infrastructure.client.TaskClient;
import com.rodolfocf.taskschedulerbff.infrastructure.enums.NotificationStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskClient taskClient;


    public TaskDTOResponse addTask(String token, TaskDTORequest taskDTO) {
        return taskClient.addTask(taskDTO, token);
    }

    public List<TaskDTOResponse> searchScheduledTasksByPeriod(LocalDateTime startDate, LocalDateTime finalDate, String token) {
        return taskClient.searchScheduledTasksByPeriod(startDate, finalDate, token);
    }

    public List<TaskDTOResponse> findTasksByClientEmail(String token) {
        return taskClient.findTasksClientByEmail(token);
    }

    public void deleteTaskById(String id, String token) {
        taskClient.deleteTaskById(id, token);
    }

    public TaskDTOResponse changeStatus(NotificationStatusEnum statusEnum, String id, String token) {
        return taskClient.changeNotificationStatus(statusEnum, id, token);
    }

    public TaskDTOResponse taskUpdate(TaskDTORequest taskDTO, String id, String token) {
        return taskClient.taskUpdate(taskDTO, id, token);
    }


}
