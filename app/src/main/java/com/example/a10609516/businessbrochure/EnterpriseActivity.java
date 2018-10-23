package com.example.a10609516.businessbrochure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class EnterpriseActivity extends AppCompatActivity {

    private FrameLayout enterprise_flt;
    private LinearLayout enterprise_llt;
    private LinearLayout medical_llt, business_llt, family_llt;;
    private ImageView enterprise_imv, medical_imv, business_imv, family_imv;
    private Button last_btn, next_btn;
    private FloatingActionMenu logo_fab;
    private FloatingActionButton home_fab, bwt_fab, aqu_fab, bin_fab;

    //配合多解析度畫面，會調整Menu的高度
    private int WIDTH, HEIGHT;
    //讀取手機裝置資訊元件
    private DisplayMetrics mPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise);
        //取消NotificationBar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //取消ActionBar
        getSupportActionBar().hide();
        //動態取得 View 物件
        InitFunction();
        //獲取手機裝置資訊
        getWindowSize();
        //動態新增ImageView
        addImageView();
        //設置圖片的特效
        ItemAnimation();
    }

    /**
     * 動態取得 View 物件
     */
    private void InitFunction() {
        enterprise_flt = (FrameLayout) findViewById(R.id.enterprise_flt);
        enterprise_llt = (LinearLayout) findViewById(R.id.enterprise_llt);
        medical_llt = (LinearLayout) findViewById(R.id.medical_llt);
        business_llt = (LinearLayout) findViewById(R.id.business_llt);
        family_llt = (LinearLayout) findViewById(R.id.family_llt);
        last_btn = (Button) findViewById(R.id.last_btn);
        next_btn = (Button) findViewById(R.id.next_btn);
        medical_imv = (ImageView) findViewById(R.id.medical_imv);
        business_imv = (ImageView) findViewById(R.id.business_imv);
        family_imv = (ImageView) findViewById(R.id.family_imv);
        logo_fab = (FloatingActionMenu) findViewById(R.id.logo_fab);
        home_fab = (FloatingActionButton) findViewById(R.id.home_fab);
        bwt_fab = (FloatingActionButton) findViewById(R.id.bwt_fab);

        last_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_intent = new Intent(EnterpriseActivity.this, IntroductionActivity.class);
                startActivity(next_intent);
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next_intent = new Intent(EnterpriseActivity.this, PartnerActivity.class);
                startActivity(next_intent);
            }
        });

        home_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bwt_intent = new Intent(EnterpriseActivity.this, HomepageActivity.class);
                startActivity(bwt_intent);
            }
        });

        bwt_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bwt_intent = new Intent(EnterpriseActivity.this, CutscenesActivity.class);
                startActivity(bwt_intent);
            }
        });

        medical_imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterprise_imv = new ImageView(EnterpriseActivity.this);
                enterprise_imv.setBackgroundResource(R.drawable.medical_content);
                LinearLayout.LayoutParams enterprise_pm = new LinearLayout.LayoutParams(WIDTH, HEIGHT);
                enterprise_imv.setLayoutParams(enterprise_pm);
                enterprise_flt.addView(enterprise_imv);
                last_btn.setVisibility(View.GONE);
                next_btn.setVisibility(View.GONE);

                enterprise_imv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        enterprise_flt.removeView(enterprise_imv);
                        last_btn.setVisibility(View.VISIBLE);
                        next_btn.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

        business_imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterprise_imv = new ImageView(EnterpriseActivity.this);
                enterprise_imv.setBackgroundResource(R.drawable.business_content);
                LinearLayout.LayoutParams enterprise_pm = new LinearLayout.LayoutParams(WIDTH, HEIGHT);
                enterprise_imv.setLayoutParams(enterprise_pm);
                enterprise_flt.addView(enterprise_imv);
                last_btn.setVisibility(View.GONE);
                next_btn.setVisibility(View.GONE);

                enterprise_imv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        enterprise_flt.removeView(enterprise_imv);
                        last_btn.setVisibility(View.VISIBLE);
                        next_btn.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

        family_imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterprise_imv = new ImageView(EnterpriseActivity.this);
                enterprise_imv.setBackgroundResource(R.drawable.family_content);
                LinearLayout.LayoutParams enterprise_pm = new LinearLayout.LayoutParams(WIDTH, HEIGHT);
                enterprise_imv.setLayoutParams(enterprise_pm);
                enterprise_flt.addView(enterprise_imv);
                last_btn.setVisibility(View.GONE);
                next_btn.setVisibility(View.GONE);

                enterprise_imv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        enterprise_flt.removeView(enterprise_imv);
                        last_btn.setVisibility(View.VISIBLE);
                        next_btn.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

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
     * 動態新增ImageView
     */
    private void addImageView (){
        /*enterprise_imv = new ImageView(EnterpriseActivity.this);
        enterprise_imv.setBackgroundResource(R.drawable.enterprise);
        LinearLayout.LayoutParams o3_01_pm = new LinearLayout.LayoutParams(WIDTH, HEIGHT);
        enterprise_imv.setLayoutParams(o3_01_pm);

        enterprise_llt.addView(enterprise_imv);*/
        //設置fab menu圖片
        logo_fab.getMenuIconView().setImageResource(R.drawable.wqp_logo);
        //把view設置在最上層
        logo_fab.bringToFront();
    }

    /**
     * 設置圖片的特效
     *
     * @return
     */
    private void ItemAnimation() {

        Animation am_medical1 = new TranslateAnimation(WIDTH, 0, 0, 0);
        am_medical1.setDuration(1500);
        am_medical1.setRepeatCount(0);
        medical_llt.setAnimation(am_medical1);
        am_medical1.startNow();

        Animation am_medical2 = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        medical_imv.startAnimation(am_medical2);

        Animation am_business1 = new TranslateAnimation(0, 0, HEIGHT, 0);
        am_business1.setDuration(1500);
        am_business1.setRepeatCount(0);
        business_llt.setAnimation(am_business1);
        am_business1.startNow();

        Animation am_business2 = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        business_imv.startAnimation(am_business2);

        Animation am_family1 = new TranslateAnimation(-WIDTH, 0, 0, 0);
        am_family1.setDuration(1500);
        am_family1.setRepeatCount(0);
        family_llt.setAnimation(am_family1);
        am_family1.startNow();

        Animation am_family2 = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        family_imv.startAnimation(am_family2);
    }

    protected void onRestart() {
        super.onRestart();
        medical_imv.setVisibility(View.VISIBLE);
        business_imv.setVisibility(View.VISIBLE);
        family_imv.setVisibility(View.VISIBLE);
        //設置圖片的特效
        ItemAnimation();
    }
}
