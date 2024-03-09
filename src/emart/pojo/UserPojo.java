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
public class UserPojo {

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public UserPojo(String userid, String empid, String password, String usertype, String username) {
        this.userid = userid;
        this.empid = empid;
        this.password = password;
        this.usertype = usertype;
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserPojo{" + "userid=" + userid + ", empid=" + empid + ", password=" + password + ", usertype=" + usertype + ", username=" + username + '}';
    }

    public UserPojo() {
    }

   

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

   

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    private String userid;
    private String empid;
    private String password;
    private String usertype;
    private String username;
}
