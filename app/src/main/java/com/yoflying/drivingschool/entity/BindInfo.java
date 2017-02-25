package com.yoflying.drivingschool.entity;

/**绑定教练信息
 * Created by yaojiulong on 2017/1/18.
 */

public class BindInfo {
    /**
     * 学员id
     */
    private int studentsId;
    /**
     * 教练id
     */
    private int coachId;
    /**
     * 科目
     */
    private int course;

    public BindInfo(int studentsId, int coachId, int course) {
        this.studentsId = studentsId;
        this.coachId = coachId;
        this.course = course;
    }
}
