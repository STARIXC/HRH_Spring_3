package org.utj.hrh.dto;

import lombok.*;

@Data
public class StaffDTO {
    private Long id;
    private String personNumber;
    
    private String firstName;
    private String surname;
    private String otherName;
    
    private String gender;
    
    private String phone;
    private String email;
    
    private String position_title;
}
