package com.rodolfocf.taskschedulerbff.infrastructure.client;

import com.rodolfocf.taskschedulerbff.business.dto.in.TaskDTORequest;
import com.rodolfocf.taskschedulerbff.business.dto.out.TaskDTOResponse;
import com.rodolfocf.taskschedulerbff.infrastructure.enums.NotificationStatusEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "task-scheduler", url = "${task-scheduler.url}")
public interface TaskClient {


    @PostMapping
    TaskDTOResponse addTask(@RequestBody TaskDTORequest taskDTO, @RequestHeader("Authorization") String token);


    @GetMapping("/scheduler_tasks")
    List<TaskDTOResponse> searchScheduledTasksByPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finalDate,
            @RequestHeader("Authorization") String token);


    @GetMapping
    List<TaskDTOResponse> findTasksClientByEmail(@RequestHeader("Authorization") String token);


    @DeleteMapping
    void deleteTaskById(@RequestParam("id") String id, @RequestHeader("Authorization") String token);


    @PatchMapping
    TaskDTOResponse changeNotificationStatus(@RequestParam("status") NotificationStatusEnum statusEnum,
                                             @RequestParam("id") String id,
                                             @RequestHeader("Authorization") String token);


    @PutMapping
    TaskDTOResponse taskUpdate(@RequestBody TaskDTORequest taskDTO,
                               @RequestParam("id") String id,
                               @RequestHeader("Authorization") String token);


}
