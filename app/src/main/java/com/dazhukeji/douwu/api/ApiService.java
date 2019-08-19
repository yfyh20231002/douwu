package com.dazhukeji.douwu.api;

import com.dazhukeji.douwu.bean.follow.UserAttentionOrganizationBean;
import com.dazhukeji.douwu.bean.follow.UserAttentionTeacherBean;
import com.dazhukeji.douwu.bean.home.HomeIndexPagingBean;
import com.dazhukeji.douwu.bean.home.courses.AllCoursesBean;
import com.dazhukeji.douwu.bean.home.invitation.InvitationFindBean;
import com.dazhukeji.douwu.bean.home.invitation.InvitationListBean;
import com.dazhukeji.douwu.bean.home.organization.OrganizationListBean;
import com.dazhukeji.douwu.bean.home.teacher.TeacherListBean;
import com.dazhukeji.douwu.bean.home.video.PlayVideoBean;
import com.dazhukeji.douwu.bean.home.video.SplendidVideoBean;
import com.dazhukeji.douwu.bean.mine.AffirmUpdateBean;
import com.dazhukeji.douwu.bean.mine.member.UserCollectCurriculumBean;
import com.dazhukeji.douwu.bean.mine.member.UserCollectVideosBean;
import com.dazhukeji.douwu.bean.mine.org.OrgInvitationFindBean;
import com.dazhukeji.douwu.bean.mine.teacher.TeacherCurriculumListBean;
import com.dazhukeji.douwu.bean.mine.teacher.TeacherInfoBean;
import com.dazhukeji.douwu.bean.publicBean.BaseBean;
import com.dazhukeji.douwu.bean.publicBean.DanceTypeBean;
import com.dazhukeji.douwu.bean.publicBean.Response;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/7/19 14:35
 * 功能描述：
 */
public interface ApiService {
    //public static final String BASE_URL = "http://yuewu.dazhu-ltd.cn/index.php/api/";

    @FormUrlEncoded
    @POST("index/index.html")
    Observable<ResponseBody> postHome(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("index/index_paging.html")
    Observable<HomeIndexPagingBean> postIndexPaging(@FieldMap Map<String, Integer> map);

    @POST("index/area.html")
    Observable<ResponseBody> postArea();


    @FormUrlEncoded
    @POST("index/index_area.html")
    Observable<HomeIndexPagingBean> postIndexArea(@FieldMap Map<String, Integer> map);

    @FormUrlEncoded
    @POST("index/index_seek.html")
    Observable<HomeIndexPagingBean> postIndexSeek(@FieldMap Map<String, Integer> map);

    /**
     * 舞种列表
     */
    @POST("Common/dance_type_select.html")
    Observable<DanceTypeBean> postDanceTypeSelect();

    /**
     * 登录注册
     */
    @FormUrlEncoded
    @POST("Login/user_login.html")
    Observable<ResponseBody> postLogin(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("Login/register.html")
    Observable<BaseBean> postRegister(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("Login/send_message.html")
    Observable<BaseBean> postSendMessage(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("Login/forget.html")
    Observable<BaseBean> postForget(@FieldMap Map<String, String> map);

    /**
     * 上传接口
     */

    @Multipart
    @POST("Upload/upload_file.html")
    Observable<Response> postUploadFile(@Part List<MultipartBody.Part> partLis);

    @Multipart
    @POST("Upload/upload_image.html")
    Observable<Response> postUploadImage(@Part List<MultipartBody.Part> partLis);

    @Multipart
    @POST("Upload/upload_video.html")
    Observable<Response> postUploadVideo(@Part List<MultipartBody.Part> partLis);

    @Multipart
    @POST("Upload/upload_music.html")
    Observable<Response> postUploadMusic(@Part List<MultipartBody.Part> partLis);

    /**
     * 首页上传接口
     */
    @FormUrlEncoded
    @POST("Index/upload_image.html")
    Observable<ResponseBody> postIndexUploadImage(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("Index/upload_video.html")
    Observable<ResponseBody> postIndexUploadVideo(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("Index/upload_music.html")
    Observable<ResponseBody> postIndexUploadMusic(@FieldMap Map<String, String> map);

    /**
     * 舞蹈机构
     */

    @FormUrlEncoded
    @POST("Organization/organization_list.html")
    Observable<OrganizationListBean> postOrganizationList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("Organization/organization_find.html")
    Observable<ResponseBody> postOrganizationFind(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("Organization/organization_invitation.html")
    Observable<ResponseBody> postOrganizationInvitation(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("Organization/organization_teacher.html")
    Observable<ResponseBody> postOrganizationTeacher(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("Organization/organization_teacher_details.html")
    Observable<ResponseBody> postOrganizationTeacherDetails(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("Organization/organization_videos.html")
    Observable<ResponseBody> postOrganizationVideos(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("Organization/organization_curriculum.html")
    Observable<ResponseBody> postOrganizationCurriculum(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("Organization/organization_curriculum_details.html")
    Observable<ResponseBody> postOrganizationCurriculumDetails(@FieldMap Map<String, String> map);

    /**
     * 关注机构(待验证)
     */
    @FormUrlEncoded
    @POST("Organization/organization_attention.html")
    Observable<ResponseBody> postOrganizationAttention(@FieldMap Map<String, String> map);

    /**
     * 取消关注机构(待验证)
     */
    @FormUrlEncoded
    @POST("Organization/organization_cancel_attention.html")
    Observable<ResponseBody> postOrganizationCancelAttention(@FieldMap Map<String, String> map);

    /**
     * 收藏/取消收藏课程(待验证)
     */
    @FormUrlEncoded
    @POST("Organization/curriculum_collect.html")
    Observable<ResponseBody> postOrganizationCurriculumCollect(@FieldMap Map<String, String> map);

    /**
     * 授课老师
     */
    @FormUrlEncoded
    @POST("Teacher/teacher_list.html")
    Observable<TeacherListBean> postTeacherList(@FieldMap Map<String, String> map);


    @FormUrlEncoded
    @POST("Teacher/teacher_details.html")
    Observable<ResponseBody> postTeacherDetails(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("Teacher/teacher_videos_list.html")
    Observable<ResponseBody> postTeacherVideosList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("Teacher/play_videos.html")
    Observable<BaseBean> postTeacherPlayVideos(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("Teacher/video_collect.html")
    Observable<BaseBean> postVideoCollect(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("Teacher/video_cancel_collect.html")
    Observable<BaseBean> postTeacherVideoCancelCollect(@FieldMap Map<String, String> map);


    @FormUrlEncoded
    @POST("Teacher/curriculum_more.html")
    Observable<ResponseBody> postCurriculumMore(@FieldMap Map<String, String> map);


    @FormUrlEncoded
    @POST("Teacher/curriculum_details.html")
    Observable<ResponseBody> postTeacherCurriculumDetails(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("Teacher/attention_teacher.html")
    Observable<ResponseBody> postAttentionTeacher(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("Teacher/cancel_attention_teacher_operation.html")
    Observable<ResponseBody> postCancelAttentionTeacherOperation(@FieldMap Map<String, String> map);

    /**
     * 全部课程
     */

    @FormUrlEncoded
    @POST("index/all_courses.html")
    Observable<AllCoursesBean> postAllCourses(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("index/curriculum_details.html")
    Observable<ResponseBody> postCurriculumDetails(@FieldMap Map<String, String> map);

    /**
     * 精彩视频
     */
    @FormUrlEncoded
    @POST("index/splendid_video.html")
    Observable<SplendidVideoBean> postSplendidVideo(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("index/play_video.html")
    Observable<PlayVideoBean> postIndexPlayVideo(@FieldMap Map<String, String> map);

    //    @FormUrlEncoded
    //    @POST("index/video_collect.html")
    //    Observable<BaseBean> postIndexVideoCollect(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("index/video_cancel_collect.html")
    Observable<BaseBean> postIndexVideoCancelCollect(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("user/user_videos_comments.html")
    Observable<ResponseBody> postUser_videos_comments(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("index/report_video.html")
    Observable<ResponseBody> postReportVideo(@FieldMap Map<String, String> map);

    /**
     * 文件播放(待验证)
     */
    @FormUrlEncoded
    @POST("Organization/file_play.html")
    Observable<ResponseBody> postFilePlay(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("Organization/file_play_comments.html")
    Observable<ResponseBody> postFilePlayComments(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("Organization/file_collect.html")
    Observable<ResponseBody> postFileCollect(@FieldMap Map<String, String> map);

    /**
     * 招聘大厅
     */
    @FormUrlEncoded
    @POST("invitation/invitation_list.html")
    Observable<InvitationListBean> postInvitationList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("invitation/invitation_find.html")
    Observable<InvitationFindBean> postInvitationFind(@FieldMap Map<String, String> map);

    /**
     * 合作洽谈
     */

//    @FormUrlEncoded
//    @POST("index/platform.html")
//    Observable<BaseBean> postPlatform(@FieldMap Map<String, String> map);

    @POST("index/platform.html")
    Observable<ResponseBody> postPlatform();

    /**
     * 首页上传
     */

    @FormUrlEncoded
    @POST("Upload/index_upload.html")
    Observable<BaseBean> postIndexUpload(@FieldMap Map<String, String> map);

    /**
     * 关注
     */

    @FormUrlEncoded
    @POST("user/user_attention_teacher.html")
    Observable<UserAttentionTeacherBean> postUserAttentionTeacher(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("user/cancel_attention_teacher.html")
    Observable<BaseBean> postCancelAttentionTeacher(@FieldMap Map<String, String> map);


    @FormUrlEncoded
    @POST("user/user_attention_organization.html")
    Observable<UserAttentionOrganizationBean> postUserAttentionOrganization(@FieldMap Map<String, String> map);


    @FormUrlEncoded
    @POST("user/cancel_attention_organization.html")
    Observable<BaseBean> postCancelAttentionOrganization(@FieldMap Map<String, String> map);


    /**
     * 会员中心
     */
    @FormUrlEncoded
    @POST("user/log_out.html")
    Observable<ResponseBody> postLoginOut(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("user/user_centre.html")
    Observable<ResponseBody> postUserCentre(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("user/user_datum_update.html")
    Observable<BaseBean> postUserDatumUpdate(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("user/affirm_update.html")
    Observable<AffirmUpdateBean> postAffirmUpdate(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("user/user_collect_curriculum.html")
    Observable<UserCollectCurriculumBean> postUserCollectCurriculum(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("user/user_collect_videos.html")
    Observable<UserCollectVideosBean> postUserCollectVideos(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("user/verify.html")
    Observable<ResponseBody> postVerify(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("user/status_upgrade.html")
    Observable<BaseBean> postUserStatusUpgrade(@FieldMap Map<String, Object> map);


    @FormUrlEncoded
    @POST("user/user_videos.html")
    Observable<ResponseBody> postUserVideos(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("user/user_videos_delete.html")
    Observable<ResponseBody> postUserVideosDelete(@FieldMap Map<String, String> map);


    /**
     * 老师中心和机构中心我的粉丝
     */
    @FormUrlEncoded
    @POST("Common/fans.html")
    Observable<ResponseBody> postCommonFans(@FieldMap Map<String, String> map);

    /**
     * 会员中心 ---老师中心
     */
    /*@FormUrlEncoded
    @POST("user/user_collect_videos.html")
    Observable<UserCollectVideosBean> postUserCollectVideos(@FieldMap Map<String,String> map);*/
    @FormUrlEncoded
    @POST("teacher/user_teacher_videos_list.html")
    Observable<ResponseBody> postUserTeacherVideosList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("teacher/teacher_curriculum_sell_list.html")
    Observable<ResponseBody> postTeacherCurriculumSellList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("teacher/teacher_curriculum_stop_selling_list.html")
    Observable<ResponseBody> postTeacherCurriculumStopSellingList(@FieldMap Map<String, String> map);


    @FormUrlEncoded
    @POST("user/user_teacher_send_video.html")
    Observable<ResponseBody> postUserTeacherSendVideo(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("teacher/teacher_curriculum_add.html")
    Observable<ResponseBody> postTeacherCurriculumAdd(@FieldMap Map<String, String> map);


    @FormUrlEncoded
    @POST("teacher/teacher_curriculum_list.html")
    Observable<TeacherCurriculumListBean> postTeacherCurriculumList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("teacher/curriculum_delete.html")
    Observable<BaseBean> postTeacherCurriculumDelete(@FieldMap Map<String, String> map);



    @FormUrlEncoded
    @POST("Teacher/teacher_information.html")
    Observable<TeacherInfoBean> postTeacherInformation(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("user/user_teacher_affirm_edit.html")
    Observable<BaseBean> postUserTeacherAffirmEdit(@FieldMap Map<String, String> map);

    /**
     * 会员中心 ---机构中心
     */
    @FormUrlEncoded
    @POST("organization/organization_invitation_find.html")
    Observable<Response<OrgInvitationFindBean>> postOrganizationInvitationFind(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("organization/organization_invitation_edit.html")
    Observable<Response> postOrganizationInvitationEdit(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("organization/organization_invitation_add.html")
    Observable<Response> postOrganizationInvitationAdd(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("organization/organization_teacher_find.html")
    Observable<ResponseBody> postOrganizationTeacherFind(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("organization/organization_teacher_manage.html")
    Observable<ResponseBody> postOrganizationTeacherManage(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("organization/organization_teacher_delete.html")
    Observable<ResponseBody> postOrganizationTeacherDelete(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("organization/organization_teacher_recommend.html")
    Observable<ResponseBody> postOrganizationTeacherRecommend(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("organization/organization_teacher_cancel_recommend.html")
    Observable<ResponseBody> postOrganizationTeacherCancelRecommend(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("organization/organization_teacher_add.html")
    Observable<ResponseBody> postOrganizationTeacherAdd(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("organization/organization_information_edit.html")
    Observable<ResponseBody> postOrganizationInformationEdit(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("organization/organization_affirm_edit.html")
    Observable<ResponseBody> postOrganizationAffirmEdit(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("user/organization_send_videos.html")
    Observable<ResponseBody> postOrganizationSendVideos(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("Organization/organization_delete_work.html")
    Observable<ResponseBody> postOrganizationDeleteWork(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("organization/organization_file.html")
    Observable<ResponseBody> postOrganizationFile(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("organization/organization_curriculum_manage.html")
    Observable<ResponseBody> postOrganizationCurriculumManage(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("organization/organization_cease_curriculum.html")
    Observable<ResponseBody> postOrganizationCeaseCurriculum(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("organization/organization_curriculum_add.html")
    Observable<ResponseBody> postOrganizationCurriculumAdd(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("organization/organization_curriculum_state_update.html")
    Observable<ResponseBody> postOrganizationCurriculumStateUpdate(@FieldMap Map<String, String> map);


    @FormUrlEncoded
    @POST("Common/curriculum_operation.html")
    Observable<ResponseBody> postTeacherCurriculumStateUpdate(@FieldMap Map<String, String> map);
}
