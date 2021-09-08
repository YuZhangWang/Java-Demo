package com.wbhz.entity;

import java.util.Date;

public class Admin {
    private int adminId;
    private String adminLoginName;
    private String adminPwd;
    private String adminName;
    private String adminHobby;
    private Date adminCreate;
    private int adminError;
    private int adminState;

    public Admin(String adminName, int adminError) {
        super();
        this.adminName = adminName;
        this.adminError = adminError;
    }

    public Admin(int adminId, String adminLoginName, String adminPwd,
                 String adminName, Date adminCreate, int adminError, int adminState) {
        super();
        this.adminId = adminId;
        this.adminLoginName = adminLoginName;
        this.adminPwd = adminPwd;
        this.adminName = adminName;
        this.adminCreate = adminCreate;
        this.adminError = adminError;
        this.adminState = adminState;
    }

    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getAdminHobby() {
        return adminHobby;
    }

    public void setAdminHobby(String adminHobby) {
        this.adminHobby = adminHobby;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminLoginName() {
        return adminLoginName;
    }

    public void setAdminLoginName(String adminLoginName) {
        this.adminLoginName = adminLoginName;
    }

    public String getAdminPwd() {
        return adminPwd;
    }

    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Date getAdminCreate() {
        return adminCreate;
    }

    public void setAdminCreate(Date adminCreate) {
        this.adminCreate = adminCreate;
    }

    public int getAdminError() {
        return adminError;
    }

    public void setAdminError(int adminError) {
        this.adminError = adminError;
    }

    public int getAdminState() {
        return adminState;
    }

    public void setAdminState(int adminState) {
        this.adminState = adminState;
    }

    @Override
    public String toString() {
        return "Admin [adminCreate=" + adminCreate + ", adminError="
                + adminError + ", adminId=" + adminId + ", adminLoginName="
                + adminLoginName + ", adminName=" + adminName + ", adminPwd="
                + adminPwd + ", adminState=" + adminState + "]";
    }

}
