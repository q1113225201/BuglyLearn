package com.sjl.buglylearn;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;
import com.tencent.bugly.crashreport.CrashReport;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    TextView tv;
    EditText etNum;

    private void initView() {
        tv = findViewById(R.id.tv);
        etNum = findViewById(R.id.et_num);
        findViewById(R.id.btn_bug).setOnClickListener(v -> {
//            bugMethod();
            trueMethod();
        });
        findViewById(R.id.btn_login).setOnClickListener(v -> {
            CrashReport.setUserId(etNum.getText().toString());
        });
        findViewById(R.id.btn_hotfix).setOnClickListener(v -> {
            Beta.downloadPatch();
        });
        findViewById(R.id.btn_apply_hotfix).setOnClickListener(v -> {
            Beta.applyDownloadedPatch();
        });
        findViewById(R.id.btn_check).setOnClickListener(v -> {
            UpgradeInfo upgradeInfo = Beta.getUpgradeInfo();
            if (upgradeInfo != null) {
                new AlertDialog.Builder(this)
                        .setTitle(upgradeInfo.title)
                        .setMessage(upgradeInfo.newFeature)
                        .setNegativeButton("下载", (dialog, which) -> {
                            dialog.dismiss();
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(upgradeInfo.apkUrl));
                            startActivity(intent);
                        })
                        .setPositiveButton("取消", (dialog, which) -> dialog.dismiss())
                        .create().show();
            } else {
                Beta.checkUpgrade(false, true);
            }
        });

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("修复后：").append("\n");
        stringBuilder.append("versionCode:").append(BuildConfig.VERSION_CODE);
        stringBuilder.append("\n");
        stringBuilder.append("versionName:").append(BuildConfig.VERSION_NAME);
        tv.setText(stringBuilder.toString());
        //升级
        //参数1：isManual 用户手动点击检查，非用户点击操作请传false
        //参数2：isSilence 是否显示弹窗等交互，[true:没有弹窗和toast] [false:有弹窗或toast]
        Beta.checkUpgrade(false, false);
    }

    private void bugMethod() {
        int i = 0;
        i = 1 / i;
    }

    private void trueMethod() {
        int i = 1;
        i = 1 / i;
        Toast.makeText(this, "true method", Toast.LENGTH_SHORT).show();
    }
}
