package com.yoflying.drivingschool.entity;

import java.io.Serializable;

/**单个课程预约配置信息
 * Created by yaojiulong on 2017/2/22.
 */

public class CourseConfig implements Serializable{

    /**
     * 教练id
     */
    private int coachId;

    /**
     * 科目
     */
    private int testCourse;

    /**
     * 地址
     */
    private String testAddress;

    private int studentsIds;

    private String appointmentDate;

    private int status;





    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    public int getTestCourse() {
        return testCourse;
    }

    public void setTestCourse(int testCourse) {
        this.testCourse = testCourse;
    }

    public String getTestAddress() {
        return testAddress;
    }

    public void setTestAddress(String testAddress) {
        this.testAddress = testAddress;
    }

    public int getStudentsIds() {
        return studentsIds;
    }

    public void setStudentsIds(int studentsIds) {
        this.studentsIds = studentsIds;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CourseConfig{" +
                "coachId=" + coachId +
                ", testCourse=" + testCourse +
                ", testAddress='" + testAddress + '\'' +
                ", studentsIds=" + studentsIds +
                ", appointmentDate=" + appointmentDate +
                '}';
    }
}
