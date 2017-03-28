package com.yoflying.drivingschool.student.bean;

/**历史约车记录
 * Created by yaojiulong on 2017/3/28.
 */

public class HistoryRecord {

    /**
     * appointmentDate : 1490593663000
     * coachId : 10
     * createTime : 1490593672000
     * dsId : 1
     * id : 1
     * status : 1
     * studentId : 7
     * testAddress : 田老师训练场
     * testCourse : 2
     */

    private long appointmentDate;
    private int coachId;
    private long createTime;
    private int dsId;
    private int id;
    private int status;
    private int studentId;
    private String testAddress;
    private int testCourse;

    public long getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(long appointmentDate) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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
