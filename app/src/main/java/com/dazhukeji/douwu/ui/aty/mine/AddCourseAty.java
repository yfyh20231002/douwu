package com.dazhukeji.douwu.ui.aty.mine;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.api.ApiService;
import com.dazhukeji.douwu.api.Config;
import com.dazhukeji.douwu.base.BaseAty;
import com.dazhukeji.douwu.bean.publicBean.DanceTypeBean;
import com.dazhukeji.douwu.contract.DanceTypeContract;
import com.dazhukeji.douwu.contract.upload.UpLoadContract;
import com.dazhukeji.douwu.presenter.DanceTypePresenter;
import com.dazhukeji.douwu.presenter.UpLoadPresenter;
import com.dazhukeji.douwu.utils.MyUtils;
import com.dazhukeji.douwu.view.MyEditText;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.zhangyunfei.mylibrary.http.ApiConfig;
import com.zhangyunfei.mylibrary.http.RetrofitHelper;
import com.zhangyunfei.mylibrary.utils.GlideApp;
import com.zhangyunfei.mylibrary.utils.StringUtils;
import com.zhangyunfei.mylibrary.utils.ToastUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/22 15:54
 * 功能描述：
 */
public class AddCourseAty extends BaseAty implements DanceTypeContract.View, UpLoadContract.View {
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.picImg)
    ImageView picImg;
    @BindView(R.id.videoImg)
    ImageView videoImg;
    @BindView(R.id.courseNameEdit)
    MyEditText courseNameEdit;
    @BindView(R.id.courseTeacherEdit)
    MyEditText courseTeacherEdit;
    @BindView(R.id.alwaysRB)
    RadioButton alwaysRB;
    @BindView(R.id.customRB)
    RadioButton customRB;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.startYear)
    TextView startYear;
    @BindView(R.id.startMonth)
    TextView startMonth;
    @BindView(R.id.startDay)
    TextView startDay;
    @BindView(R.id.endYear)
    TextView endYear;
    @BindView(R.id.endMonth)
    TextView endMonth;
    @BindView(R.id.endDay)
    TextView endDay;
    @BindView(R.id.courseEdit)
    MyEditText courseEdit;
    @BindView(R.id.titles_recyclerView)
    RecyclerView titlesRecyclerView;
    @BindView(R.id.juniorRB)
    RadioButton juniorRB;
    @BindView(R.id.middleRB)
    RadioButton middleRB;
    @BindView(R.id.seniorRB)
    RadioButton seniorRB;
    @BindView(R.id.radioGroup2)
    RadioGroup radioGroup2;
    @BindView(R.id.coursePriceEdit)
    MyEditText coursePriceEdit;

    private DanceTypePresenter mDanceTypePresenter;
    private TitlesAdapter mTitlesAdapter;
    private UpLoadPresenter mUpLoadPresenter;

    private List<DanceTypeBean.DataBean> mTitleList;

    private String mStartDate = "";
    private String mEndDate = "";

    private String imgPath = "";
    private String videoPath = "";
    /**
     * org 机构添加课程
     * teacher 老师添加课程
     */
    private String mFrom;
    private int dance_type_id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_course;
    }

    @Override
    public void initView() {
        txtTitle.setText("添加课程");
        mFrom = getIntent().getStringExtra("from");
    }

    @Override
    public void initData() {
        mDanceTypePresenter = new DanceTypePresenter();
        mDanceTypePresenter.attachView(this, mContext);
        mDanceTypePresenter.postDanceTypeSelect();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4);
        titlesRecyclerView.setLayoutManager(gridLayoutManager);
        mUpLoadPresenter = new UpLoadPresenter();
        mUpLoadPresenter.attachView(this, mContext);
    }

    /**
     * @param t 1 代表起始时间  2 代表结束时间
     */
    private void setDate(int t) {
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();

        //正确设置方式 原因：注意事项有说明
        //        startDate.set(2019,0,1);
        endDate.set(2026, 11, 31);
        TimePickerView timePickerView = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                String format = df.format(date);
                //                if (status.equals("start")){
                //                    mStartDate = format;
                //                    startDateTv.setText(format);
                //                }else if (status.equals("end")){
                //                    mEndDate = format;
                //                    endDateTv.setText(format);
                //                }
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                if (t == 1) {
                    startYear.setText(String.valueOf(calendar.get(Calendar.YEAR)));
                    startMonth.setText(String.valueOf(calendar.get(Calendar.MONTH) + 1));
                    startDay.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
                    mStartDate = format;
                } else if (t == 2) {
                    endYear.setText(String.valueOf(calendar.get(Calendar.YEAR)));
                    endMonth.setText(String.valueOf(calendar.get(Calendar.MONTH) + 1));
                    endDay.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
                    mEndDate = format;
                }

            }
        })
                .setDate(Calendar.getInstance())
                .setRangDate(startDate, endDate)
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .build();
        timePickerView.show();
    }

    @OnClick({R.id.picImg, R.id.videoImg, R.id.startLayout, R.id.endLayout, R.id.confirmTv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.picImg:
                setImagePicker();
                Intent intent = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent, Config.IMAGE_PICKER);
                break;
            case R.id.videoImg:
                /**
                 * intent.setType(“image/*”);//选择图片
                 * intent.setType(“audio/*”); //选择音频
                 * intent.setType(“video/*”); //选择视频 （mp4 3gp 是android支持的视频格式）
                 * intent.setType(“video/*;image/*”);//同时选择视频和图片
                 */
                Intent intent2 = new Intent(Intent.ACTION_GET_CONTENT);
                intent2.setType("video/*");
                intent2.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent2, "选择文件"), Config.VIDEO_PICKER);
                break;
            case R.id.startLayout:
                setDate(1);
                break;
            case R.id.endLayout:
                setDate(2);
                break;
            case R.id.confirmTv:
                confirm();
                break;
        }
    }

    private void confirm() {
        if (StringUtils.isEmpty(courseTeacherEdit.getContent())||StringUtils.isEmpty(courseNameEdit.getContent())
                ||StringUtils.isEmpty(imgPath)
                ||StringUtils.isEmpty(courseEdit.getContent())
                ||StringUtils.isEmpty(videoPath)
                ||StringUtils.isEmpty(coursePriceEdit.getContent())
        ){
            Toast.makeText(mContext, "请将信息填写完整", Toast.LENGTH_SHORT).show();
            return;
        }
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> map = new HashMap<>();
        map.put("user_token", ApiConfig.getToken());
        map.put("curriculum_admin", courseTeacherEdit.getContent());
        map.put("curriculum_name", courseNameEdit.getContent());
        map.put("curriculum_introduce_picture", imgPath);
        map.put("curriculum_details", courseEdit.getContent());
        map.put("curriculum_video", videoPath);
        map.put("curriculum_actual_price", coursePriceEdit.getContent());
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.juniorRB) {
                    map.put("curriculum_difficulty", "初级进阶");
                } else if (checkedId == R.id.middleRB) {
                    map.put("curriculum_difficulty", "中级进阶");
                } else if (checkedId == R.id.seniorRB) {
                    map.put("curriculum_difficulty", "高级进阶");
                }
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.alwaysRB) {
                    map.put("curriculum_effective", "1");
                } else if (checkedId == R.id.customRB) {
                    map.put("curriculum_effective", "2");
                }
            }
        });
        map.put("curriculum_difficulty", "初级进阶");
        map.put("curriculum_effective", "1");
        map.put("curriculum_start_time", mStartDate);
        map.put("curriculum_over_time", mEndDate);
        if (mTitleList != null) {
            map.put("dance_type_id", String.valueOf(dance_type_id));
        }
        Observable<ResponseBody> observable = null;
        if (mFrom.equals("org")) {
            observable = apiService.postOrganizationCurriculumAdd(map);
        } else if (mFrom.equals("teacher")) {
            observable = apiService.postTeacherCurriculumAdd(map);
        }
        if (observable != null) {
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableObserver<ResponseBody>() {
                        @Override
                        public void onNext(ResponseBody responseBody) {
                            Map<String, String> map = Config.getMap(responseBody);
                            ToastUtils.showToast(map.get("msg"));
                            finish();
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    @Override
    public void danceTypeSuccess(DanceTypeBean danceTypeBean) {
        List<DanceTypeBean.DataBean> dance_type = danceTypeBean.getData();
        if (dance_type != null && dance_type.size() > 0) {
            mTitleList = dance_type;
            dance_type_id = mTitleList.get(0).getDance_type_id();
            if (mTitlesAdapter == null) {
                mTitlesAdapter = new TitlesAdapter(R.layout.title_item, dance_type);
                titlesRecyclerView.setAdapter(mTitlesAdapter);
                mTitlesAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        dance_type_id = mTitleList.get(position).getDance_type_id();
                    }
                });
            } else {
                mTitlesAdapter.notifyDataSetChanged();
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == Config.IMAGE_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images.size() > 0) {
                    String path = images.get(0).path;
                    File file = new File(path);
                    GlideApp.with(mContext).load(file).into(picImg);
                    //mUpLoadPresenter.postPic("headImg", file);
                    mUpLoadPresenter.postFile("headImg",file,"3");
                }
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }
        if (resultCode == RESULT_OK && data != null && requestCode == Config.VIDEO_PICKER) {
            File file = new File(MyUtils.getRealPath(AddCourseAty.this,data));
            //mUpLoadPresenter.postVideo("video", file);
            mUpLoadPresenter.postFile("video",file,"1");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDanceTypePresenter != null){
            mDanceTypePresenter.detachView();
        }
    }

    @Override
    public void uploadSuccess(String channel, String path) {
        if (channel.equals("headImg")) {
            imgPath = path;
        }
        if (channel.equals("video")) {
            videoPath = path;
        }
    }

    public class TitlesAdapter extends BaseQuickAdapter<DanceTypeBean.DataBean, BaseViewHolder> {

        private int selectPosition = 0;

        public TitlesAdapter(int layoutResId, @Nullable List<DanceTypeBean.DataBean> data) {
            super(layoutResId, data);
        }


        public void setSelectPosition(int selectPosition) {
            this.selectPosition = selectPosition;
            notifyDataSetChanged();
        }

        public int getSelectPositon() {
            return selectPosition;
        }

        @Override
        protected void convert(BaseViewHolder helper, DanceTypeBean.DataBean item) {
            CheckBox checkBox = helper.getView(R.id.titleCB);
            checkBox.setText(item.getDance_type_name());
            if (selectPosition == helper.getLayoutPosition()) {
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
        }
    }

}
