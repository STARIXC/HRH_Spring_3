package org.utj.hrh.dto;

import lombok.*;
import org.utj.hrh.model.*;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDTO {
    private Employee employee;
    private Address presentAddress;
    private Address homeAddress;
    private User user_login;
    private boolean hasLoginAccess;
    private boolean presentSameAsPermanent;
}
