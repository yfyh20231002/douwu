package com.dazhukeji.douwu.ui.aty.mine;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.zhangyunfei.mylibrary.utils.ToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
 * 创建时间：2018/11/15 11:29
 * 功能描述：发布视频
 */
public class PublishVideoAty extends BaseAty implements DanceTypeContract.View, UpLoadContract.View {
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.typeTv)
    TextView typeTv;
    @BindView(R.id.typeTv2)
    TextView typeTv2;
    @BindView(R.id.fileLayout)
    LinearLayout fileLayout;
    @BindView(R.id.titleEdit)
    MyEditText titleEdit;
    @BindView(R.id.videoImg)
    ImageView videoImg;
    @BindView(R.id.picImg)
    ImageView picImg;
    @BindView(R.id.titles_recyclerView)
    RecyclerView titlesRecyclerView;


    private DanceTypePresenter mDanceTypePresenter;
    private TitlesAdapter mTitlesAdapter;
    private UpLoadPresenter mUpLoadPresenter;

    private List<DanceTypeBean.DataBean> mTitleList;
    private String imgPath = "";
    private String videoPath = "";
    private String mType;
    private String musicPath = "";
    /**
     * home 首页
     * teacher  老师中心
     * org  机构中心
     */
    private String mFrom;
    private String mDistrictId;

    @Override
    public int getLayoutId() {
        return R.layout.layout_publish_video;
    }

    @Override
    public void initView() {
        mType = getIntent().getStringExtra("type");
        mFrom = getIntent().getStringExtra("from");
        mDistrictId = getIntent().getStringExtra("district_id");
        if ("video".equals(mType)) {
            txtTitle.setText("发布视频");
            typeTv.setText("上传视频");
            titleEdit.setHint("请输入视频标题");
        } else if ("music".equals(mType)) {
            txtTitle.setText("发布音频");
            typeTv.setText("上传音频");
            titleEdit.setHint("请输入音频标题");
        } else {
            txtTitle.setText("发布图片");
            typeTv2.setText("上传图片");
            fileLayout.setVisibility(View.GONE);
            titleEdit.setHint("请输入图片标题");
        }

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

    @Override
    public void danceTypeSuccess(DanceTypeBean danceTypeBean) {
        List<DanceTypeBean.DataBean> dance_type = danceTypeBean.getData();
        if (dance_type != null && dance_type.size() > 0) {
            mTitleList = dance_type;
            if (mTitlesAdapter == null) {
                mTitlesAdapter = new TitlesAdapter(R.layout.title_item, dance_type);
                titlesRecyclerView.setAdapter(mTitlesAdapter);
                mTitlesAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        mTitlesAdapter.setSelectPosition(position);
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
            File file = new File(MyUtils.getRealPath(PublishVideoAty.this,data));
            mUpLoadPresenter.postFile("video",file,"1");
        }

        if (resultCode == RESULT_OK && data != null && requestCode == Config.MUSIC_PICKER) {
            File file = new File(MyUtils.getRealPath(PublishVideoAty.this,data));
            mUpLoadPresenter.postFile("music",file,"2");
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
        if (channel.equals("music")) {
            musicPath = path;
        }
    }

    @OnClick({R.id.videoImg, R.id.picImg, R.id.confirmTv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.videoImg:
                if ("video".equals(mType)) {
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
                } else if ("music".equals(mType)) {
                    /**
                     * intent.setType(“image/*”);//选择图片
                     * intent.setType(“audio/*”); //选择音频
                     * intent.setType(“video/*”); //选择视频 （mp4 3gp 是android支持的视频格式）
                     * intent.setType(“video/*;image/*”);//同时选择视频和图片
                     */
                    Intent intent2 = new Intent(Intent.ACTION_GET_CONTENT);
                    intent2.setType("audio/*");
                    intent2.addCategory(Intent.CATEGORY_OPENABLE);
                    startActivityForResult(Intent.createChooser(intent2, "选择文件"), Config.MUSIC_PICKER);
                }
                break;
            case R.id.picImg:
                setImagePicker();
                Intent intent = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent, Config.IMAGE_PICKER);
                break;
            case R.id.confirmTv:
                confirm();
                break;
        }
    }

    private void confirm() {
        ApiService apiService = RetrofitHelper.getInstance().create(ApiService.class);
        Map<String, String> map = new HashMap<>();
        map.put("user_token", ApiConfig.getToken());
        map.put("district_id", mDistrictId);
        map.put("file_cover", imgPath);
        map.put("file_name", titleEdit.getContent());
        //file_type
        //文件类型1:视频2:音乐3:图片
        if ("video".equals(mType)) {
            map.put("file_content", videoPath);
            map.put("file_type", "1");
        } else if ("music".equals(mType)) {
            map.put("file_content", musicPath);
            map.put("file_type", "2");
        } else {
            map.put("file_content", imgPath);
            map.put("file_type", "3");
        }
        if (mTitleList != null) {
            map.put("dance_type_id", String.valueOf(mTitleList.get(mTitlesAdapter.getSelectPositon()).getDance_type_id()));
        }
        Observable<ResponseBody> observable = null;
        if (mFrom.equals("teacher")) {
            observable = apiService.postUserTeacherSendVideo(map);
        } else if (mFrom.equals("org")) {
            observable = apiService.postOrganizationSendVideos(map);
        } else if (mFrom.equals("home")) {
            if ("video".equals(mType)) {
                observable = apiService.postIndexUploadVideo(map);
            } else if ("music".equals(mType)) {
                observable = apiService.postIndexUploadMusic(map);
            } else {
                observable = apiService.postIndexUploadImage(map);
            }

        }
        if (observable != null) {
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableObserver<ResponseBody>() {
                        @Override
                        public void onNext(ResponseBody responseBody) {
                            Map<String, String> map = Config.getMap(responseBody);
                            ToastUtils.showToast(map.get("msg"));
                            if (map.get("code").equals("1")) {
                                finish();
                            }
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
