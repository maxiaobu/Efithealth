package com.efithealth.app.maxiaobu.receiver;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import java.io.File;

public class DownloadCompleteReceiver extends BroadcastReceiver {
    /**
     * 安卓系统下载类
     **/
    DownloadManager manager;


    public DownloadCompleteReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(
                DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {

            //获取下载的文件id
            long downId = intent.getLongExtra(
                    DownloadManager.EXTRA_DOWNLOAD_ID, -1);

            manager = (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
            //自动安装apk
            installAPK(context,manager.getUriForDownloadedFile(downId));


        }
    }

    /**
     * 安装apk文件
     */
    private void installAPK(Context context,Uri apk) {
        Intent intents = new Intent();

        intents.setAction("android.intent.action.VIEW");
        intents.addCategory("android.intent.category.DEFAULT");
        intents.setType("application/vnd.android.package-archive");
//        intents.setData(apk);
        intents.setDataAndType(Uri.fromFile(new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)+"/maxiaobu.apk")), "application/vnd.android.package-archive");
        intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intents);

    }
}
