package com.rodolfocf.taskschedulerbff.business.dto.out;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.rodolfocf.taskschedulerbff.infrastructure.enums.NotificationStatusEnum;
import lombok.*;


import java.time.LocalDateTime;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTOResponse {


    private String id;
    private String taskName;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime dateOfCreation;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime taskDate;
    private String clientEmail;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime modificationDate;
    private NotificationStatusEnum notificationStatusEnum;

}
