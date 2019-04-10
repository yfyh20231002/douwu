package com.dazhukeji.douwu.ui.aty.mine;

import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.base.BaseAty;
import com.dazhukeji.douwu.ui.fgt.follow.FollowFragment;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/11/22 14:02
 * 功能描述：
 */
public class MemberFollowAty extends BaseAty {
//    @BindView(R.id.txt_title)
//    TextView txtTitle;
//    @BindView(R.id.followTeacherTv)
//    TextView followTeacherTv;
//    @BindView(R.id.followOrganizationTv)
//    TextView followOrganizationTv;
//    @BindView(R.id.follow_recyclerView)
//    RecyclerView followRecyclerView;

//    private RecyclerViewManager mRecyclerViewManager;
//    private int position = 0;
//    private List<Object> mList = new ArrayList<>();
    private FollowFragment mFollowFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_member_follow;
    }

    @Override
    public void initView() {
//        txtTitle.setText("我的关注");
//        mRecyclerViewManager = new RecyclerViewManager(followRecyclerView);
//        mRecyclerViewManager.setLinearLayoutManager(RecyclerView.VERTICAL);
//        for (int i = 0; i < 5; i++) {
//            mList.add(new Object());
//        }
//        setSelect();
    }

    @Override
    public void initData() {
        mFollowFragment = new FollowFragment();
        mFollowFragment.showBack(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.content,mFollowFragment).commitAllowingStateLoss();

    }

//    @OnClick({R.id.followTeacherTv, R.id.followOrganizationTv})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.followTeacherTv:
//                position=0;
//                break;
//            case R.id.followOrganizationTv:
//                position=1;
//                break;
//        }
//        setSelect();
//    }
//
//    private void setSelect() {
//        followTeacherTv.setBackground(null);
//        followOrganizationTv.setBackground(null);
//        if (0 == position) {
//            followTeacherTv.setBackgroundResource(R.drawable.icon_title_bg);
//            followRecyclerView.setAdapter(new TeacherAdapter(R.layout.teacher_item, mList));
//        } else if (1 == position) {
//            followOrganizationTv.setBackgroundResource(R.drawable.icon_title_bg);
////            followRecyclerView.setAdapter(new DanceOrgAdapter(R.layout.danceorg_item, mList));
//        }
//
//    }
}
