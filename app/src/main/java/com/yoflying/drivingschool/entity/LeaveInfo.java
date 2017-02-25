package com.yoflying.drivingschool.entity;

/**请假详细信息
 * Created by yaojiulong on 2017/2/7.
 */

public class LeaveInfo {

    /**
     * id : 9
     * dsId : 1
     * coachId : 0
     * leaveDate2 : 2017-2-8 15:24
     * leaveDate3 : 2017-2-16 15:24
     * coachName : 田志恒
     * status : 1
     * createTime : 1486452306000
     * modifyTime : 1486452306000
     */

    private int id;
    private int dsId;
    private int coachId;
    private String leaveDate2;
    private String leaveDate3;
    private String coachName;
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

    public String getLeaveDate2() {
        return leaveDate2;
    }

    public void setLeaveDate2(String leaveDate2) {
        this.leaveDate2 = leaveDate2;
    }

    public String getLeaveDate3() {
        return leaveDate3;
    }

    public void setLeaveDate3(String leaveDate3) {
        this.leaveDate3 = leaveDate3;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
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
}
