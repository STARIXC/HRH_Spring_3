package org.utj.hrh.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CalendarEvent {

    private String title;
    private LocalDateTime start;
    private LocalDateTime end;
    private boolean allDay;
    private String className;


}
