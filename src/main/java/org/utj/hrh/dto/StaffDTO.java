package org.utj.hrh.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StaffDTO {
    private String empNo;
    private String firstName;
    private String surname;
    private String otherName;
}
