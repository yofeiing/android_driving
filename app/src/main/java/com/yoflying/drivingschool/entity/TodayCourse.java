package com.yoflying.drivingschool.entity;

import java.io.Serializable;

/**
 * Created by yaojiulong on 2017/1/18.
 */

public class TodayCourse  implements Serializable{


    /**
     * id : 127
     * dsId : 1
     * coachId : 1
     * testCourse : 3
     * testAddress : 南山
     * studentsIds : null
     * appointmentDate : {"size": 2, "time": {"stop": "2017-01-07 15:00:00", "start": "2017-01-18 14:00:00"}}
     * status : 1
     * createTime : 1483775504000
     * modifyTime : 1483775504000
     */

    private int id;
    private int dsId;
    private int coachId;
    private int testCourse;
    private String testAddress;
    private Object studentsIds;
    private String appointmentDate;
    private int status;
    private long createTime;
    private long modifyTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDsId() {
        return dsId;
    }

    public void setDsId(int dsId) {
        this.dsId = dsId;
    }

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

    public Object getStudentsIds() {
        return studentsIds;
    }

    public void setStudentsIds(Object studentsIds) {
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

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public static class AppointmentDate{
        private int size;
        private time time;
        public static class time{
            private String stop;
            private String start;

            public String getStop() {
                return stop;
            }

            public void setStop(String stop) {
                this.stop = stop;
            }

            public String getStart() {
                return start;
            }

            public void setStart(String start) {
                this.start = start;
            }

            @Override
            public String toString() {
                return "time{" +
                        "stop='" + stop + '\'' +
                        ", start='" + start + '\'' +
                        '}';
            }
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public TodayCourse.AppointmentDate.time getTime() {
            return time;
        }

        public void setTime(TodayCourse.AppointmentDate.time time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "AppointmentDate{" +
                    "size=" + size +
                    ", time=" + time +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "TodayCourse{" +
                "id=" + id +
                ", dsId=" + dsId +
                ", coachId=" + coachId +
                ", testCourse=" + testCourse +
                ", testAddress='" + testAddress + '\'' +
                ", studentsIds=" + studentsIds +
                ", appointmentDate='" + appointmentDate + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
