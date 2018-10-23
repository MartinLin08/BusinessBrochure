package com.example.a10609516.businessbrochure;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class HomepageActivity extends AppCompatActivity {

    private LinearLayout under_llt;
    private ImageView bwt, aqu, bin;
    private ImageView bk;
    private TextView version;

    //配合多解析度畫面，會調整Menu的高度
    private int WIDTH, HEIGHT;
    //讀取手機裝置資訊元件
    private DisplayMetrics mPhone;

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        //取消NotificationBar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //取消ActionBar
        getSupportActionBar().hide();
        //動態取得 View 物件
        InitFunction();
        //獲取手機裝置資訊
        getWindowSize();
        //確認是否有最新版本，進行更新
        CheckFirebaseVersion();
        //請求開啟權限
        UsesPermission();
    }

    /**
     * 動態取得 View 物件
     */
    private void InitFunction() {
        under_llt = (LinearLayout) findViewById(R.id.under_llt);
        bk = (ImageView) findViewById(R.id.bk);
        bwt = (ImageView) findViewById(R.id.bwt);
        aqu = (ImageView) findViewById(R.id.aqu);
        bin = (ImageView) findViewById(R.id.bin);
        version = (TextView) findViewById(R.id.version);

        bwt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bwt = new Intent(HomepageActivity.this, CutscenesActivity.class);
                startActivity(bwt);
            }
        });

        under_llt.bringToFront();
    }

    /**
     * 獲取手機裝置資訊
     */
    private void getWindowSize() {
        mPhone = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mPhone);
        //圖片的寬度
        WIDTH = (int) ((float) mPhone.widthPixels);
        //圖片的高度
        HEIGHT = (int) ((float) mPhone.heightPixels);
    }

    /**
     * 確認是否有最新版本，進行更新
     */
    private void CheckFirebaseVersion() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("WQP");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = dataSnapshot.getValue(String.class);
                //Log.d("現在在根結點上的資料是:", "Value is: " + value);
                Map<String, String> map = (Map) dataSnapshot.getValue();
                String data = map.toString().substring(9, 12);
                Log.e("HomepageActivity", "已讀取到值:" + data);
                if (version.getText().toString().equals(data)) {
                } else {
                    new AlertDialog.Builder(HomepageActivity.this)
                            .setTitle("更新通知")
                            .setMessage("檢測到軟體重大更新\n請更新最新版本")
                            .setIcon(R.drawable.bwt_icon)
                            .setNegativeButton("確定",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog,
                                                            int which) {
                                            new Thread() {
                                                @Override
                                                public void run() {
                                                    super.run();
                                                    HomepageActivity.this.Update();
                                                }
                                            }.start();
                                        }
                                    }).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("InventoryActivity", "Failed to read value.", error.toException());
            }
        });
    }

    /**
     * 下載新版本APK
     */
    public void Update() {
        try {
            URL url = new URL("http://192.168.0.201/wqp_1.9.apk");
            //URL url = new URL("http://m.wqp-water.com.tw/wqp_1.9.apk");
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            //c.setRequestMethod("GET");
            //c.setDoOutput(true);
            c.connect();

            String PATH = Environment.getExternalStorageDirectory() + "/Download/";
            //String PATH2 = Environment.getExternalStorageDirectory().getPath() + "/Download/";
            //String PATH = System.getenv("SECONDARY_STORAGE") + "/Download/";
            File file = new File(PATH);
            file.mkdirs();
            File outputFile = new File(file, "wqp_1.9.apk");
            FileOutputStream fos = new FileOutputStream(outputFile);

            InputStream is = c.getInputStream();

            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len1);
            }
            fos.close();
            is.close();//till here, it works fine - .apk is download to my sdcard in download file

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/Download/" + "wqp_1.9.apk")), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

            HomepageActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "開始安裝新版本", Toast.LENGTH_LONG).show();
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("下載錯誤!", e.toString());
            HomepageActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "更新失敗!", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    /**
     * 請求開啟儲存、相機權限
     */
    private void UsesPermission() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(HomepageActivity.this,
                android.Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(HomepageActivity.this,
                    android.Manifest.permission.CAMERA)) {
                new AlertDialog.Builder(HomepageActivity.this)
                        .setMessage("我真的沒有要做壞事, 給我權限吧?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(HomepageActivity.this,
                                        new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.READ_EXTERNAL_STORAGE
                                                , android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .show();
            } else {
                ActivityCompat.requestPermissions(HomepageActivity.this,
                        new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.READ_EXTERNAL_STORAGE
                                , android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        }
    }
}
