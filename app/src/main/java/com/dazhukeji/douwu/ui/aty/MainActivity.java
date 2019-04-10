package com.dazhukeji.douwu.ui.aty;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;

import com.dazhukeji.douwu.R;
import com.dazhukeji.douwu.api.Config;
import com.dazhukeji.douwu.base.BaseAty;
import com.dazhukeji.douwu.ui.fgt.follow.FollowFragment;
import com.dazhukeji.douwu.ui.fgt.forum.ForumFragment;
import com.dazhukeji.douwu.ui.fgt.home.HomeFragment;
import com.dazhukeji.douwu.ui.fgt.mine.MyFragment;
import com.zhangyunfei.mylibrary.common.ActivityStack;
import com.zhangyunfei.mylibrary.utils.NotificationsUtils;
import com.zhangyunfei.mylibrary.utils.PermissionHelper;


public class MainActivity extends BaseAty {
    private long firstTime = 0;

    private HomeFragment mHomeFragment;
    private ForumFragment mForumFragment;
    private FollowFragment mFollowFragment;
    private MyFragment mMyFragment;

    // 四个子Fragment的Tag
    public static final String TAG_HOME = "Home";
    public static final String TAG_FORUM = "Forum";
    public static final String TAG_FOLLOW = "Follow";
    public static final String TAG_MINE = "Mine";
    private String hideTag;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        //申请权限
        requestSomePermission();
        checkNotification();
        mHomeFragment = new HomeFragment();
        switchFragment(mHomeFragment, TAG_HOME);
    }

    private void requestSomePermission() {
        //Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.READ_PHONE_STATE,
        if (!PermissionHelper.hasSelfPermissions(this, Manifest.permission.CALL_PHONE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.READ_PHONE_STATE)) {
            PermissionHelper.requestPermissions(this, 100, new String[]{Manifest.permission.CALL_PHONE,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.READ_PHONE_STATE}, new PermissionHelper.OnPermissionListener() {
                @Override
                public void onPermissionGranted() {

                }

                @Override
                public void onPermissionDenied() {
                    PermissionHelper.showTipsDialog(MainActivity.this);
                }
            });
        }
    }

    private void checkNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            if (!NotificationsUtils.isNotificationEnabled(this)) {
                notificationDialog();
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (!NotificationsUtils.isEnableV26(this, getPackageName(), android.os.Process.myUid())) {
                notificationDialog();
            }

        }

    }

    private void notificationDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setTitle("该应用需要打开通知权限");

        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, " 去设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Intent localIntent = new Intent();
                localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if (Build.VERSION.SDK_INT >= 9) {
                    localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    localIntent.setData(Uri.fromParts("package", MainActivity.this.getPackageName(), null));
                } else if (Build.VERSION.SDK_INT <= 8) {
                    localIntent.setAction(Intent.ACTION_VIEW);

                    localIntent.setClassName("com.android.settings",
                            "com.android.settings.InstalledAppDetails");

                    localIntent.putExtra("com.android.settings.ApplicationPkgName",
                            MainActivity.this.getPackageName());
                }
                startActivity(localIntent);
            }
        });
        dialog.show();
    }

    @Override
    public void initData() {
    }

    /**
     * 选择不同的fragment
     */
    private void switchFragment(Fragment fragment, String tag) {
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

        Fragment tagFragment = mFragmentManager.findFragmentByTag(tag);
        if (tagFragment == null) {
            mFragmentTransaction.add(R.id.fragment_container, fragment, tag);
        } else {
            mFragmentTransaction.show(tagFragment);
        }

        tagFragment = mFragmentManager.findFragmentByTag(hideTag);

        if (tagFragment != null && !tagFragment.equals(fragment)) {
            mFragmentTransaction.hide(tagFragment);
        }
        hideTag = tag;
        mFragmentTransaction.commit();
    }

    public void onTabClicked(View view) {
        int viewId = view.getId();
        switch (viewId) {
            case R.id.re_home:
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                }
                switchFragment(mHomeFragment, TAG_HOME);
                break;
            case R.id.re_forum:
                if (mForumFragment == null) {
                    mForumFragment = new ForumFragment();
                }
                switchFragment(mForumFragment, TAG_FORUM);
                break;
            case R.id.re_follow:
                if (Config.isLogin()) {
                    if (mFollowFragment == null) {
                        mFollowFragment = new FollowFragment();
                    }
                    switchFragment(mFollowFragment, TAG_FOLLOW);
                } else {
                    startActivity(LoginAty.class);
                }

                break;
            case R.id.re_mine:
                if (Config.isLogin()) {
                    if (mMyFragment == null) {
                        mMyFragment = new MyFragment();
                    }
                    switchFragment(mMyFragment, TAG_MINE);
                } else {
                    startActivity(LoginAty.class);
                }
                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (System.currentTimeMillis() - firstTime < 1500) {
            ActivityStack.getInstance().appExit();
        } else {
            firstTime = System.currentTimeMillis();
            showShortToast("再按一次返回桌面", Gravity.CENTER);
        }
    }


    public void toHomeFragment() {
        if (mHomeFragment == null) {
            mHomeFragment = new HomeFragment();
        }
        switchFragment(mHomeFragment, TAG_HOME);
    }

}
