package org.utj.hrh.model;

/**
 *
 * @author UTJ
 */
public class LeaveBalance {

    private int balance_id;
    private int leave_id;
    private int days_accrued, days_used;
    private String employee_id,year;

    public LeaveBalance(int leave_id, int days_accrued, int days_used, String employee_id, String year) {
        this.leave_id = leave_id;
        this.days_accrued = days_accrued;
        this.days_used = days_used;
        this.employee_id = employee_id;
        this.year = year;
    }

   
  
    public int getBalance_id() {
        return balance_id;
    }

    public void setBalance_id(int balance_id) {
        this.balance_id = balance_id;
    }

    public int getLeave_id() {
        return leave_id;
    }

    public void setLeave_id(int leave_id) {
        this.leave_id = leave_id;
    }

    public int getDays_accrued() {
        return days_accrued;
    }

    public void setDays_accrued(int days_accrued) {
        this.days_accrued = days_accrued;
    }

    public int getDays_used() {
        return days_used;
    }

    public void setDays_used(int days_used) {
        this.days_used = days_used;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }



    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    
    
    public LeaveBalance() {
    }

  

}
