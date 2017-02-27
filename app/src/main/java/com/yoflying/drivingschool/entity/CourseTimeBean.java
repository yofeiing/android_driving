package com.yoflying.drivingschool.entity;

/**单个预约课程的时间实体类
 * Created by yaojiulong on 2017/2/26.
 */

public class CourseTimeBean {

    /**
     * size : 2
     * time : {"stop":"2017-01-13 09:30:00","start":"2017-01-13 08:30:00"}
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
         * stop : 2017-01-13 09:30:00
         * start : 2017-01-13 08:30:00
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

        @Override
        public String toString() {
            return "TimeBean{" +
                    "start='" + start + '\'' +
                    ", stop='" + stop + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CourseTimeBean{" +
                "size=" + size +
                ", time=" + time +
                '}';
    }
}
