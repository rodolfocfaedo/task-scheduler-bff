package com.rodolfocf.taskschedulerbff.business;


import com.rodolfocf.taskschedulerbff.business.dto.out.TaskDTOResponse;
import com.rodolfocf.taskschedulerbff.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;


    public void sendEmail(TaskDTOResponse taskDTOResponse) {
        emailClient.sendEmail(taskDTOResponse);
    }


}
