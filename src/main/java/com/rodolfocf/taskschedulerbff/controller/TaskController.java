package com.rodolfocf.taskschedulerbff.controller;

import com.rodolfocf.taskschedulerbff.business.TaskService;
import com.rodolfocf.taskschedulerbff.business.dto.in.TaskDTORequest;
import com.rodolfocf.taskschedulerbff.business.dto.out.TaskDTOResponse;
import com.rodolfocf.taskschedulerbff.infrastructure.enums.NotificationStatusEnum;
import com.rodolfocf.taskschedulerbff.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
@Tag(name = "Tasks", description = "Register task client")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    @Operation(summary = "Get client's tasks by their email", description = "Search client's tasks by their email")
    @ApiResponse(responseCode = "200", description = "Tasks found successfully")
    @ApiResponse(responseCode = "400", description = "Client not found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<List<TaskDTOResponse>> findTasksClientByEmail(@RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(taskService.findTasksByClientEmail(token));
    }

    @PostMapping
    @Operation(summary = "Save client's task", description = "Create new task")
    @ApiResponse(responseCode = "200", description = "Task saved successfully")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<TaskDTOResponse> addTask(@RequestBody TaskDTORequest taskDTORequest,
                                                   @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(taskService.addTask(token, taskDTORequest));
    }


    @PutMapping
    @Operation(summary = "Change task data client", description = "Change task data client")
    @ApiResponse(responseCode = "200", description = "Changed successfully")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<TaskDTOResponse> taskUpdate(@RequestBody TaskDTORequest taskDTO,
                                                      @RequestParam("id") String id,
                                                      @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(taskService.taskUpdate(taskDTO, id, token));
    }

    @DeleteMapping
    @Operation(summary = "Delete client's tasks by id", description = "Delete client's tasks by id")
    @ApiResponse(responseCode = "200", description = "Tasks deleted successfully")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<String> deleteTaskById(@RequestParam("id") String id,
                                                 @RequestHeader(name = "Authorization", required = false) String token) {
        taskService.deleteTaskById(id, token);
        return ResponseEntity.ok().body("Task deleted!");
    }

    @PatchMapping
    @Operation(summary = "Change client's notification status", description = "Change client's notification status")
    @ApiResponse(responseCode = "200", description = "Changed successfully")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<TaskDTOResponse> changeNotificationStatus(@RequestParam("status") NotificationStatusEnum statusEnum,
                                                                    @RequestParam("id") String id,
                                                                    @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(taskService.changeStatus(statusEnum, id, token));
    }

    @GetMapping("/scheduled_tasks")
    @Operation(summary = "Get task by period", description = "Search task by period")
    @ApiResponse(responseCode = "200", description = "Task found successfully")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    public ResponseEntity<List<TaskDTOResponse>> searchScheduledTasksByPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finalDate,
            @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(taskService.searchScheduledTasksByPeriod(startDate, finalDate, token));
    }


}
