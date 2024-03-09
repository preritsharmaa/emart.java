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
public class ReceptionistPojo {
    private String receptionistId;
    private String receptionistName;
    private String userId;
    private double salary;

    public String getReceptionistId() {
        return receptionistId;
    }

    public void setReceptionistId(String receptionistId) {
        this.receptionistId = receptionistId;
    }

    public String getReceptionistName() {
        return receptionistName;
    }

    public void setReceptionistName(String receptionistName) {
        this.receptionistName = receptionistName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public ReceptionistPojo() {
    }

    public ReceptionistPojo(String receptionistId, String receptionistName, String userId, double salary) {
        this.receptionistId = receptionistId;
        this.receptionistName = receptionistName;
        this.userId = userId;
        this.salary = salary;
    }
}
