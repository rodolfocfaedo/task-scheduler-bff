package com.rodolfocf.taskschedulerbff.business;


import com.rodolfocf.taskschedulerbff.business.dto.in.LoginDTORequest;
import com.rodolfocf.taskschedulerbff.business.dto.out.TaskDTOResponse;
import com.rodolfocf.taskschedulerbff.infrastructure.enums.NotificationStatusEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TaskService taskService;
    private final EmailService emailService;
    private final ClientService clientService;

    @Value("${client.email}")
    private String email;

    @Value("${client.password}")
    private String password;


    @Scheduled(cron = "${cron.hour}")
    public void searchTasksForNextHour() {
        String token = login(toRequestDTOConverter());
        log.info("Started task scheduled search");
        LocalDateTime nextHour = LocalDateTime.now().plusHours(1);
        LocalDateTime nextHourPlusFiveMin = LocalDateTime.now().plusHours(1).plusMinutes(3);
        List<TaskDTOResponse> taskList = taskService.searchScheduledTasksByPeriod(nextHour, nextHourPlusFiveMin, token);
        log.info("Tasks found{} ", taskList.toString());

        taskList.forEach(task -> {
            emailService.sendEmail(task);
            log.info("Email sent{} ", task.getClientEmail());
            taskService.changeStatus(NotificationStatusEnum.NOTIFIED, task.getId(), token);

        });
        log.info("Search finished");
    }

    public String login(LoginDTORequest loginDTORequest) {
        return clientService.login(loginDTORequest);
    }

    public LoginDTORequest toRequestDTOConverter() {
        return LoginDTORequest.builder()
                .email(email)
                .password(password)
                .build();
    }


}
