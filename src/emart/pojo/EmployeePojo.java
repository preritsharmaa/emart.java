/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emart.pojo;

/**
 *
 * @author Priyanshu
 */
public class EmployeePojo {

    @Override
    public String toString() {
        return "EmployeesPojo{" + "empId=" + empId + ", empName=" + empName + ", job=" + job + ", salary=" + salary + '}';
    }

    public EmployeePojo() {
    }

    public EmployeePojo(String empId, String empName, String job, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.job = job;
        this.salary = salary;
    }
    private String empId;
    private String empName;
    private String job;
    private double salary;

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    
}
