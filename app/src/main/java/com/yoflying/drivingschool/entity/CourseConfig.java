package com.yoflying.drivingschool.entity;

/**单个课程预约配置信息
 * Created by yaojiulong on 2017/2/22.
 */

public class CourseConfig {

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

    private AppointmentDate appointmentDate;

    private int status;

    public static class AppointmentDate{

        /**
         * 人数
         */
        private int size;
        private CourseConfig.AppointmentDate.time time;

        /**
         * 时间
         */
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

        public CourseConfig.AppointmentDate.time getTime() {
            return time;
        }

        public void setTime(CourseConfig.AppointmentDate.time time) {
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

    public AppointmentDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(AppointmentDate appointmentDate) {
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
