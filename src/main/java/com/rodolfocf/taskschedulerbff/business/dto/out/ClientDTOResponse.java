package com.rodolfocf.taskschedulerbff.business.dto.out;

import com.rodolfocf.taskschedulerbff.business.dto.in.CellphoneDTORequest;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDTOResponse {

    private Long id;
    private String name;
    private String email;
    private String password;
    private List<CellphoneDTOResponse> cellphones;
}
