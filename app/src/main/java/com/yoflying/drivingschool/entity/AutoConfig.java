package com.yoflying.drivingschool.entity;

import java.util.List;

/**
 * Created by yaojiulong on 2017/2/24.
 */

public class AutoConfig {

    /**
     * dsAppointment2 : {'size': 2, 'time': [{'stop': '09:30:00', 'start': '08:30:00'}, {'stop': '15:00:00', 'start': '14:00:00'}]}
     * dsAppointment3 : {'size': 2, 'time': [{'stop': '09:30:00', 'start': '08:30:00'}, {'stop': '15:00:00', 'start': '14:00:00'}]}
     * outCoachIds : []
     * dsId : 1
     * status : 1
     */

    private String dsAppointment2;
    private String dsAppointment3;
    private int status;
    private List<?> outCoachIds;

    public String getDsAppointment2() {
        return dsAppointment2;
    }

    public void setDsAppointment2(String dsAppointment2) {
        this.dsAppointment2 = dsAppointment2;
    }

    public String getDsAppointment3() {
        return dsAppointment3;
    }

    public void setDsAppointment3(String dsAppointment3) {
        this.dsAppointment3 = dsAppointment3;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<?> getOutCoachIds() {
        return outCoachIds;
    }

    public void setOutCoachIds(List<?> outCoachIds) {
        this.outCoachIds = outCoachIds;
    }
}
