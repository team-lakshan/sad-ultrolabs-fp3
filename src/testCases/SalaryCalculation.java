package testCases;

import java.sql.Timestamp;

public class SalaryCalculation {

    public double calculateDailySalary(Timestamp clockInTime, Timestamp clockOutTime, double payRate) {
        if (clockInTime == null || clockOutTime == null) {
            throw new IllegalArgumentException("Clock-in time or clock-out time cannot be null");
        }

        // Calculate hours worked
        long millisecondsWorked = clockOutTime.getTime() - clockInTime.getTime();
        double hoursWorked = millisecondsWorked / (1000.0 * 60 * 60);

        // Calculate and return the daily salary
        return payRate * hoursWorked;
    }

}
