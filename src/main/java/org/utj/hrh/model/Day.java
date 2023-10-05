/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utj.hrh.model;

/**
 *
 * @author CBWAHYI
 */
public class Day {
  String date_field,date_name,log_no,emp_no,activity_desc,isWeekday,isHoliday;
  Integer hours_worked, leave_hours, public_holiday, extra;
  public String getActivity_desc() {
    return activity_desc;
  }

  public Integer getLeave_hours() {
    return leave_hours;
  }

  public void setLeave_hours(Integer leave_hours) {
    this.leave_hours = leave_hours;
  }

  public Integer getPublic_holiday() {
    return public_holiday;
  }

  public void setPublic_holiday(Integer public_holiday) {
    this.public_holiday = public_holiday;
  }

  public Integer getExtra() {
    return extra;
  }

  public void setExtra(Integer extra) {
    this.extra = extra;
  }

  public String getIsWeekday() {
    return isWeekday;
  }

  public void setIsWeekday(String isWeekday) {
    this.isWeekday = isWeekday;
  }

  public String getIsHoliday() {
    return isHoliday;
  }

  public void setIsHoliday(String isHoliday) {
    this.isHoliday = isHoliday;
  }

  public void setActivity_desc(String activity_desc) {
    this.activity_desc = activity_desc;
  }

  public String getLog_no() {
    return log_no;
  }

  public void setLog_no(String log_no) {
    this.log_no = log_no;
  }

  public String getEmp_no() {
    return emp_no;
  }

  public void setEmp_no(String emp_no) {
    this.emp_no = emp_no;
  }

  public String getDate_field() {
    return date_field;
  }

  public Integer getHours_worked() {
    return hours_worked;
  }

  public void setHours_worked(Integer hours_worked) {
    this.hours_worked = hours_worked;
  }

  public void setDate_field(String date_field) {
    this.date_field = date_field;
  }

  public String getDate_name() {
    return date_name;
  }

  public void setDate_name(String date_name) {
    this.date_name = date_name;
  }
  
  
}
