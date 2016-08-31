package com.efithealth.app.maxiaobu.service;

import android.app.DownloadManager;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;

import com.efithealth.app.maxiaobu.receiver.DownloadCompleteReceiver;

/**
 * Created by 马小布 on 2016/8/30.
 */
public class UpdataService extends Service {
    /**
     * 安卓系统下载类
     **/
    DownloadManager manager;

    /**
     * 接收下载完的广播
     **/
    DownloadCompleteReceiver receiver;

    public UpdataService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 调用下载
        initDownManager(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        if (receiver != null)
            unregisterReceiver(receiver);
        super.onDestroy();
    }

    /**
     * 初始化下载器
     **/
    private void initDownManager(Intent intent) {
        manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        receiver = new DownloadCompleteReceiver();

        //设置下载地址
        DownloadManager.Request down = new DownloadManager.Request(
                Uri.parse(intent.getStringExtra("url")));
        down.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE
                | DownloadManager.Request.NETWORK_WIFI);
        // 下载时，通知栏显示途中
        down.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        down.setVisibleInDownloadsUi(true);
        down.setDestinationInExternalFilesDir(this,
                Environment.DIRECTORY_DOWNLOADS, "android_verync_shop.apk");
        // 将下载请求放入队列
        manager.enqueue(down);
        //注册下载广播
        registerReceiver(receiver, new IntentFilter(
                DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        //停止服务并关闭广播
        UpdataService.this.stopSelf();
    }
}
