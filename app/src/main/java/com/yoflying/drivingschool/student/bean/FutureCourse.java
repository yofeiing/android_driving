package com.yoflying.drivingschool.student.bean;

/**未来课程实体类
 * Created by yaojiulong on 2017/3/28.
 */

public class FutureCourse {


    /**
     * appointmentDate : {"size": 10, "time": {"stop": "2017-3-4 11:00", "start": "2017-3-4 09:00"}}
     * coachId : 10
     * createTime : 1488359669000
     * dsId : 1
     * id : 144
     * modifyTime : 1490600380000
     * status : 1
     * studentsIds : [7, 7, 7, 7, 7, 7, 7, 7, 7, 7]
     * testAddress : 民乐
     * testCourse : 2
     */

    private String appointmentDate;
    private int coachId;
    private long createTime;
    private int dsId;
    private int id;
    private long modifyTime;
    private int status;
    private String studentsIds;
    private String testAddress;
    private int testCourse;

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getDsId() {
        return dsId;
    }

    public void setDsId(int dsId) {
        this.dsId = dsId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStudentsIds() {
        return studentsIds;
    }

    public void setStudentsIds(String studentsIds) {
        this.studentsIds = studentsIds;
    }

    public String getTestAddress() {
        return testAddress;
    }

    public void setTestAddress(String testAddress) {
        this.testAddress = testAddress;
    }

    public int getTestCourse() {
        return testCourse;
    }

    public void setTestCourse(int testCourse) {
        this.testCourse = testCourse;
    }
}
