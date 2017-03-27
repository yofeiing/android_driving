package com.yoflying.drivingschool.student.bean;

/**预约课程时间实体类
 * Created by yaojiulong on 2017/3/23.
 */

public class OrderTime {

    /**
     * size : 2
     * time : {"stop":"2017-01-11 09:30:00","start":"2017-01-11 08:30:00"}
     */

    private int size;
    private TimeBean time;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public TimeBean getTime() {
        return time;
    }

    public void setTime(TimeBean time) {
        this.time = time;
    }

    public static class TimeBean {
        /**
         * stop : 2017-01-11 09:30:00
         * start : 2017-01-11 08:30:00
         */

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
    }
}
