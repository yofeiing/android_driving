package com.yoflying.drivingschool.retrofit;

import com.yoflying.drivingschool.entity.Admin;
import com.yoflying.drivingschool.entity.HttpsResult;
import com.yoflying.drivingschool.entity.LeaveInfo;
import com.yoflying.drivingschool.entity.Person;
import com.yoflying.drivingschool.entity.TodayCourse;
import com.yoflying.drivingschool.student.bean.FutureCourse;
import com.yoflying.drivingschool.student.bean.HistoryRecord;
import com.yoflying.drivingschool.student.bean.OrderInfo;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by yaojiulong on 2016/12/21.
 */

public interface ApiStore {
//       String BASE_URL="http://waddwaw.vicp.cc:14858/";
    // String BASE_URL="http://192.168.0.103:8080/";
    // String BASE_URL="http://120.27.33.15:8080/";
     String BASE_URL="http://172.16.45.196:8080/";

    @POST("restful/loginPost")
    Observable<HttpsResult<Person>> login(@Body RequestBody body);

    /**
     * 创建教练和学员
     * @param body
     * @param map
     * @return
     */
    @POST("manage/createCoachSt")
    Observable<HttpsResult> create(@Body RequestBody body, @HeaderMap Map<String,String> map);

    /**
     * 获取教练和学员的信息
     * @return
     */
    @GET("coachstudent/coachstudentInfo")
    Observable<HttpsResult<Person>> getUserInfo();

    /**
     * 获取管理员信息
     * @return
     */
    @GET("manage/manageInfo")
    Observable<HttpsResult<Admin>> getAdminInfo();

    /**
     * 获取可以预约课程的信息
     * @return
     */
    @GET("coachstudent/student/getAppointment")
    Observable<OrderInfo> getOrderInfo();

    /**
     * 获取学员列表
     * @param map
     * @return
     */
    @GET("manage/findStudentList")
    Observable<HttpsResult<List<Person>>> getStudentList(@QueryMap Map<String,String> map);

    /**
     * 获取教练列表
     * @param map
     * @return
     */
    @GET("manage/findCoachList")
    Observable<HttpsResult<List<Person>>> getTeacherList(@QueryMap  Map<String,String> map);

    /**
     * 搜索学员或者教练
     * @param map
     * @return
     */
    @GET("manage/searchCoachStList")
    Observable<HttpsResult<List<Person>>> searchPerson(@QueryMap Map<String,String> map);

    /**
     * 学员绑定教练
     * @param body
     * @return
     */
    @POST("manage/bindCSCUpdate")
    Observable<HttpsResult> bindTeacher(@Body RequestBody body);

    /**
     * 获取今日课程
     * @return
     */
    @GET("manage/dsAppointrentSt ")
    Observable<HttpsResult<List<TodayCourse>>> getTodayCourse();

    /**
     * 添加教练请假
     * @param body
     * @return
     */
    @POST("manage/createLeave")
    Observable<HttpsResult> addLeave(@Body RequestBody body);

    /**
     * 获取历史请假列表
     * @param map
     * @return
     */
    @GET("manage/coachLeaveList")
    Observable<HttpsResult<List<LeaveInfo>>> getLeaveList(@QueryMap  Map<String,String> map);

    /**
     * 手动预约配置
     * @param body
     * @return
     */
    @POST("manage/postAppointment")
    Observable<HttpsResult>  submitCourseConfig(@Body RequestBody body);

    /**
     * 学员预约课程
     * @param body
     * @return
     */
    @POST("coachstudent/student/postAppointment")
    Observable<HttpsResult> orderCourse(@Body RequestBody body);

    /**
     * 学员获取未来学车情况
     * @return
     */
    @GET("coachstudent/student/futureAppointment")
    Observable<HttpsResult<List<FutureCourse>>> getFutureCourse();

    /**
     * 获取历史约车记录
     * @return
     */
    @GET("coachstudent/student/historyAppointment")
    Observable<HttpsResult<List<HistoryRecord>>> getHistoryRecords(@QueryMap  Map<String,String> map);

}
