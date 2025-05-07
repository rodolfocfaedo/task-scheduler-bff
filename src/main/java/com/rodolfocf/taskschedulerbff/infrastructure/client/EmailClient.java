package com.rodolfocf.taskschedulerbff.infrastructure.client;


import com.rodolfocf.taskschedulerbff.business.dto.out.TaskDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification", url = "${notification.url}")
public interface EmailClient {

    @PostMapping
    void sendEmail(@RequestBody TaskDTOResponse taskDTOResponse);
}
