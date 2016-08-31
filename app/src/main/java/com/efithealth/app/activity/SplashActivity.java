package com.efithealth.app.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.VolleyError;
import com.efithealth.R;
import com.efithealth.app.maxiaobu.service.UpdataService;
import com.efithealth.app.maxiaobu.utils.IRequest;
import com.efithealth.app.maxiaobu.utils.RequestListener;
import com.efithealth.app.maxiaobu.utils.storage.SPUtils;

/**
 * 开屏页
 * 设置动画 --guide-登录--主页面--更新--初始化环信
 */
public class SplashActivity extends AppCompatActivity {


    private ImageView iv_start;

    private static final int sleepTime = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if(VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
            //透明状态栏
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		}*/
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        iv_start = (ImageView) findViewById(R.id.iv_start);
        initImage();
    }

    private void initImage() {
        final ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        scaleAnim.setFillAfter(true);
        scaleAnim.setDuration(2000);
        scaleAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                checkGuide();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        iv_start.startAnimation(scaleAnim);

    }

    private void startActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
        finish();
    }

    private void checkGuide() {
        boolean enter_guide = SPUtils.getBoolean(this, "enter_guide", true);
        if (enter_guide) {
            this.finish();
            startActivity(new Intent(this, GuideActivity.class));
        } else {
//            Log.d("SplashActivity", "enter_guide1:" + enter_guide1);

            checkVersion();
            startActivity();
        }

    }

    private void checkVersion() {
        IRequest.get(this, "http://news-at.zhihu.com/api/4/version/android/2.3.0", new RequestListener() {
            @Override
            public void requestSuccess(String json) {
                String lastVersion = "1.0.2";
                String apkUrl = "http:/\\/zhstatic.zhihu.com\\/pkg\\/store\\/daily\\/zhihu-daily-zhihu-2.6.0(744)-release.apk";
                String msg = "【更新】\\r\\n\\r\\n- 极大提升性能及稳定性\\r\\n- 部分用户无法使用新浪微博登录\\r\\n- 部分用户无图模式无法分享至微信及朋友圈";
                String version = "100";
                String versionName = getAppVersionName(SplashActivity.this);
                if (versionName.equals(version)) {
                    startActivity();
                } else {
                    //版本需要更新
                    showUpdateDialog(msg, apkUrl);
                }
            }

            @Override
            public void requestError(VolleyError e) {

            }

            @Override
            public void resultFail(String json) {
                String lastVersion = "1.0.2";
                String apkUrl = "http:/\\/zhstatic.zhihu.com\\/pkg\\/store\\/daily\\/zhihu-daily-zhihu-2.6.0(744)-release.apk";
                String msg = "【更新】\\r\\n\\r\\n- 极大提升性能及稳定性\\r\\n- 部分用户无法使用新浪微博登录\\r\\n- 部分用户无图模式无法分享至微信及朋友圈";
                String version = "100";
                String versionName = getAppVersionName(SplashActivity.this);
//                        this.getAppVersionName(SplashActy.this);
                if (versionName.equals(version)) {
                    startActivity();
                } else {
                    //版本需要更新
                    showUpdateDialog(msg, apkUrl);
                }

            }
        });
    }


    public void showUpdateDialog(String msg, final String apkUrl) {
        new MaterialDialog.Builder(this)
                .title("有新版本")
                .content(msg)
                .positiveColor(getResources().getColor(R.color.colorTextPrimary))
                .positiveText("确认")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        // TODO: 2016/8/30 跟新
                        Intent intent = new Intent(SplashActivity.this, UpdataService.class);
                        intent.putExtra("url", apkUrl);
                        startService(intent);
                        dialog.dismiss();
                        startActivity();

                    }
                })
                .negativeColor(getResources().getColor(R.color.colorTextPrimary))
                .negativeText("取消")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        startActivity();
                    }
                })
                .show();
        /*com.mobile.widget.AlertDialogs alert = new com.mobile.widget.AlertDialogs(this);
        alert.confirm(getString(R.string.alert_new_version),
                new AlertDialogs.AlertClickListener() {
                    public void onClick() {
                        Intent intent = new Intent(SplashActy.this, UpdataService.class);
                        intent.putExtra("url",APK_URL);
                        startService(intent);
                        initData();
                        // 检查网络连接
                        // checkNetWork();
                        // 直接进入登录
                        showLogin();
                    }
                }, new AlertDialogs.AlertClickListener() {
                    public void onClick() {
                        initData();
                        // 检查网络连接
                        // checkNetWork();
                        // 直接进入登录
                        showLogin();
                    }
                });*/


    }


    /*@Override
    protected void onStart() {
        super.onStart();

        new Thread(new Runnable() {
            public void run() {
                if (DemoHXSDKHelper.getInstance().isLogined()) {
                    // ** 免登陆情况 加载所有本地群和会话
                    //不是必须的，不加sdk也会自动异步去加载(不会重复加载)；
                    //加上的话保证进了主页面会话和群组都已经load完毕
                    Message message = new Message();
                    m_handler.sendMessage(message);
                    long start = System.currentTimeMillis();
                    //EMGroupManager.getInstance().loadAllGroups();
                    EMChatManager.getInstance().loadAllConversations();
                    long costTime = System.currentTimeMillis() - start;



                    //等待sleeptime时长
                    if (sleepTime - costTime > 0) {
                        try {
                            Thread.sleep(sleepTime - costTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //进入主页面
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }else {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                    }
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
            }
        }).start();

    }

    public Handler m_handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            updateData();
        }

    };
    public void updateData(){
        App.getInstance().update_loacl_friend();
         App.getInstance().update_local_myinfo();
         App.getInstance().update_loacl_indexdata();
    }*/
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
        } catch (Exception e) {
            Toast.makeText(context, "获取versionName失败", Toast.LENGTH_SHORT).show();
        }
        return versionName;
    }
}
