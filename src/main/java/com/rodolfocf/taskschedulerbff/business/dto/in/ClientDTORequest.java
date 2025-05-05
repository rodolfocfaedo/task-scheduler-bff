package com.rodolfocf.taskschedulerbff.business.dto.in;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDTORequest {


    private String name;
    private String email;
    private String password;
    private List<CellphoneDTORequest> cellphones;
}
