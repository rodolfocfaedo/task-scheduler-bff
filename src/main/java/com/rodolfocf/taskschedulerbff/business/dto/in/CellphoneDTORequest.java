package com.rodolfocf.taskschedulerbff.business.dto.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CellphoneDTORequest {


    private String number;
    private String areaCode;

}
