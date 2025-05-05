package com.rodolfocf.taskschedulerbff.business.dto.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CellphoneDTOResponse {


    private Long id;
    private String number;
    private String areaCode;

}
