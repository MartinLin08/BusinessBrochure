package com.example.a10609516.businessbrochure;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

public class PartnerActivity extends AppCompatActivity {

    private ViewPager partner_vp;
    private View drinks_view, hotel_view, medicine_view;
    private List<View> viewList;//view陣列

    private List<TextView> txtPoints;
    private LinearLayout partner_points;

    private ImageView p01, p02, p03, p04, p05, p06;
    private ImageView p07, p08, p09, p10, p11, p12;
    private ImageView p13, p14, p15, p16, p17, p18;
    private ImageView p19, p20, p21, p22, p23, p24;
    private ImageView p25, p26, p27, p28, p29, p30;
    private ImageView partner_imv, partner_top;

    private Button last_btn, next_btn;

    private FloatingActionMenu logo_fab;
    private FloatingActionButton home_fab, bwt_fab, aqu_fab, bin_fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner);
        //取消NotificationBar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //取消ActionBar
        getSupportActionBar().hide();
        //動態取得 View 物件
        InitFunction();
        //設置ViewPager物件
        GetViewPager();
        //ViewPager的各項設置
        ViewPagerAdapter();
        //動態新增ImageView
        addImageView();
        //設置圖片來源
        setAnimatorToLarge();
        //設置圖片的特效
        ItemAnimation();
        //設置圓點
        initCircle();
    }

    /**
     * 動態取得 View 物件
     */
    private void InitFunction() {
        partner_top = (ImageView) findViewById(R.id.partner_top);
        partner_vp = (ViewPager) findViewById(R.id.partner_vp);
        partner_imv = (ImageView) findViewById(R.id.partner_imv);
        last_btn = (Button) findViewById(R.id.last_btn);
        next_btn = (Button) findViewById(R.id.next_btn);
        logo_fab = (FloatingActionMenu) findViewById(R.id.logo_fab);
        home_fab = (FloatingActionButton) findViewById(R.id.home_fab);
        bwt_fab = (FloatingActionButton) findViewById(R.id.bwt_fab);
        partner_points = (LinearLayout) findViewById(R.id.partner_points);

        last_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_newt = new Intent(PartnerActivity.this, EnterpriseActivity.class);
                startActivity(intent_newt);
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_newt = new Intent(PartnerActivity.this, GlobalActivity.class);
                startActivity(intent_newt);
            }
        });

        home_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bwt_intent = new Intent(PartnerActivity.this, HomepageActivity.class);
                startActivity(bwt_intent);
            }
        });

        bwt_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bwt_intent = new Intent(PartnerActivity.this, CutscenesActivity.class);
                startActivity(bwt_intent);
            }
        });
    }

    /**
     * 設置圓點
     */
    private void initCircle() {
        txtPoints = new ArrayList<>();
        int d = 13;
        int m = 2;
        for (int i = 0; i < viewList.size(); i++) {
            TextView circle_txt = new TextView(this);
            if (i == 0) {
                circle_txt.setBackgroundResource(R.drawable.point_blue);
            } else {
                circle_txt.setBackgroundResource(R.drawable.point_white);
            }
            LinearLayout.LayoutParams circle_params = new LinearLayout.LayoutParams(d, d);
            circle_params.setMargins(m, m, m, m);
            circle_txt.setLayoutParams(circle_params);
            txtPoints.add(circle_txt);
            partner_points.addView(circle_txt);
        }
    }

    /**
     *圓點的位置
     * @param pos
     */
    public void changePoints(int pos) {
        if (txtPoints != null) {
            for (int i = 0; i < txtPoints.size(); i++) {
                if (pos == i) {
                    txtPoints.get(i).setBackgroundResource(R.drawable.point_blue);
                } else {
                    txtPoints.get(i).setBackgroundResource(R.drawable.point_white);
                }
            }
        }
    }

    /**
     * 取得ViewPager物件
     */
    private void GetViewPager() {
        LayoutInflater inflater = getLayoutInflater();
        drinks_view = inflater.inflate(R.layout.partner_drinks, null);
        hotel_view = inflater.inflate(R.layout.partner_hotel, null);
        medicine_view = inflater.inflate(R.layout.partner_medicine, null);

        viewList = new ArrayList<>();// 將要分頁顯示的View装入陣列中
        viewList.add(drinks_view);
        viewList.add(hotel_view);
        viewList.add(medicine_view);
    }

    /**
     * 動態取得 ViewPager 物件
     */
    private void InViewGroupFunction() {
        p01 = (ImageView) findViewById(R.id.drinks01);
        p02 = (ImageView) findViewById(R.id.drinks02);
        p03 = (ImageView) findViewById(R.id.drinks03);
        p04 = (ImageView) findViewById(R.id.drinks04);
        p05 = (ImageView) findViewById(R.id.drinks05);
        p06 = (ImageView) findViewById(R.id.drinks06);
        p07 = (ImageView) findViewById(R.id.drinks07);
        p08 = (ImageView) findViewById(R.id.drinks08);
        p09 = (ImageView) findViewById(R.id.drinks09);
        p10 = (ImageView) findViewById(R.id.drinks10);
        p11 = (ImageView) findViewById(R.id.hotel01);
        p12 = (ImageView) findViewById(R.id.hotel02);
        p13 = (ImageView) findViewById(R.id.hotel03);
        p14 = (ImageView) findViewById(R.id.hotel04);
        p15 = (ImageView) findViewById(R.id.hotel05);
        p16 = (ImageView) findViewById(R.id.hotel06);
        p17 = (ImageView) findViewById(R.id.hotel07);
        p18 = (ImageView) findViewById(R.id.hotel08);
        p19 = (ImageView) findViewById(R.id.hotel09);
        p20 = (ImageView) findViewById(R.id.hotel10);
        p21 = (ImageView) findViewById(R.id.medicine01);
        p22 = (ImageView) findViewById(R.id.medicine02);
        p23 = (ImageView) findViewById(R.id.medicine03);
        p24 = (ImageView) findViewById(R.id.medicine04);
        p25 = (ImageView) findViewById(R.id.medicine05);
        p26 = (ImageView) findViewById(R.id.medicine06);
        p27 = (ImageView) findViewById(R.id.medicine07);
        p28 = (ImageView) findViewById(R.id.medicine08);
        p29 = (ImageView) findViewById(R.id.medicine09);
        p30 = (ImageView) findViewById(R.id.medicine10);
    }

    /**
     * ViewPager的各項設置
     */
    private void ViewPagerAdapter() {
        partner_vp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return viewList.size();
                //return 10000;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                // TODO Auto-generated method stub
                //根據傳來的Key，找到view,判斷與傳來的參數View arg0是不是同一個layout
                return view == viewList.get(Integer.parseInt(object.toString()));
                //return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                // TODO Auto-generated method stub
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                //container.addView(viewList.get(position));
                container.addView(viewList.get(position % viewList.size()));
                //動態取得 View 物件
                InViewGroupFunction();
                switch (position) {
                    case 0:
                        p01.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                partner_imv.setImageDrawable(null);
                                partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.b01));
                                partner_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                Animation am_trans = AnimationUtils.loadAnimation(PartnerActivity.this, R.anim.translate);
                                partner_imv.startAnimation(am_trans);
                            }
                        });
                        p02.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                partner_imv.setImageDrawable(null);
                                partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.b02));
                                partner_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                Animation am_trans = AnimationUtils.loadAnimation(PartnerActivity.this, R.anim.translate);
                                partner_imv.startAnimation(am_trans);
                            }
                        });
                        p03.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                partner_imv.setImageDrawable(null);
                                partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.b03));
                                partner_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                Animation am_trans = AnimationUtils.loadAnimation(PartnerActivity.this, R.anim.translate);
                                partner_imv.startAnimation(am_trans);
                            }
                        });
                        p04.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                partner_imv.setImageDrawable(null);
                                partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.b04));
                                partner_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                Animation am_trans = AnimationUtils.loadAnimation(PartnerActivity.this, R.anim.translate);
                                partner_imv.startAnimation(am_trans);
                            }
                        });
                        p05.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                partner_imv.setImageDrawable(null);
                                partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.b05));
                                partner_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                Animation am_trans = AnimationUtils.loadAnimation(PartnerActivity.this, R.anim.translate);
                                partner_imv.startAnimation(am_trans);
                            }
                        });
                        p06.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                partner_imv.setImageDrawable(null);
                                partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.b06));
                                partner_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                Animation am_trans = AnimationUtils.loadAnimation(PartnerActivity.this, R.anim.translate);
                                partner_imv.startAnimation(am_trans);
                            }
                        });
                        p07.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                partner_imv.setImageDrawable(null);
                                partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.b07));
                                partner_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                Animation am_trans = AnimationUtils.loadAnimation(PartnerActivity.this, R.anim.translate);
                                partner_imv.startAnimation(am_trans);
                            }
                        });
                        p08.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                partner_imv.setImageDrawable(null);
                                partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.b08));
                                partner_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                Animation am_trans = AnimationUtils.loadAnimation(PartnerActivity.this, R.anim.translate);
                                partner_imv.startAnimation(am_trans);
                            }
                        });
                        p09.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                partner_imv.setImageDrawable(null);
                                partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.b09));
                                partner_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                Animation am_trans = AnimationUtils.loadAnimation(PartnerActivity.this, R.anim.translate);
                                partner_imv.startAnimation(am_trans);
                            }
                        });
                        p10.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                partner_imv.setImageDrawable(null);
                                partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.b10));
                                partner_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                Animation am_trans = AnimationUtils.loadAnimation(PartnerActivity.this, R.anim.translate);
                                partner_imv.startAnimation(am_trans);
                            }
                        });
                        break;
                    case 1:
                        p11.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                partner_imv.setImageDrawable(null);
                                partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.b11));
                                partner_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                Animation am_trans = AnimationUtils.loadAnimation(PartnerActivity.this, R.anim.translate);
                                partner_imv.startAnimation(am_trans);
                            }
                        });
                        p12.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                partner_imv.setImageDrawable(null);
                                partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.b12));
                                partner_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                Animation am_trans = AnimationUtils.loadAnimation(PartnerActivity.this, R.anim.translate);
                                partner_imv.startAnimation(am_trans);
                            }
                        });
                        p13.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                partner_imv.setImageDrawable(null);
                                partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.b13));
                                partner_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                Animation am_trans = AnimationUtils.loadAnimation(PartnerActivity.this, R.anim.translate);
                                partner_imv.startAnimation(am_trans);
                            }
                        });
                        p14.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                partner_imv.setImageDrawable(null);
                                partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.b14));
                                Animation am_trans = AnimationUtils.loadAnimation(PartnerActivity.this, R.anim.translate);
                                partner_imv.startAnimation(am_trans);
                            }
                        });
                        p15.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                partner_imv.setImageDrawable(null);
                                partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.b15));
                                partner_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                Animation am_trans = AnimationUtils.loadAnimation(PartnerActivity.this, R.anim.translate);
                                partner_imv.startAnimation(am_trans);
                            }
                        });
                        p16.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                partner_imv.setImageDrawable(null);
                                partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.b16));
                                partner_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                Animation am_trans = AnimationUtils.loadAnimation(PartnerActivity.this, R.anim.translate);
                                partner_imv.startAnimation(am_trans);
                            }
                        });
                        p17.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                partner_imv.setImageDrawable(null);
                                partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.b17));
                                partner_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                Animation am_trans = AnimationUtils.loadAnimation(PartnerActivity.this, R.anim.translate);
                                partner_imv.startAnimation(am_trans);
                            }
                        });
                        p18.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                partner_imv.setImageDrawable(null);
                                partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.b18));
                                partner_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                Animation am_trans = AnimationUtils.loadAnimation(PartnerActivity.this, R.anim.translate);
                                partner_imv.startAnimation(am_trans);
                            }
                        });
                        p19.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                partner_imv.setImageDrawable(null);
                                partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.b19));
                                partner_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                Animation am_trans = AnimationUtils.loadAnimation(PartnerActivity.this, R.anim.translate);
                                partner_imv.startAnimation(am_trans);
                            }
                        });
                        p20.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                partner_imv.setImageDrawable(null);
                                partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.b20));
                                partner_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                Animation am_trans = AnimationUtils.loadAnimation(PartnerActivity.this, R.anim.translate);
                                partner_imv.startAnimation(am_trans);
                            }
                        });
                        break;
                    case 2:
                        p21.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                partner_imv.setImageDrawable(null);
                                partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.b21));
                                partner_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                Animation am_trans = AnimationUtils.loadAnimation(PartnerActivity.this, R.anim.translate);
                                partner_imv.startAnimation(am_trans);
                            }
                        });
                        p22.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                partner_imv.setImageDrawable(null);
                                partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.b22));
                                partner_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                Animation am_trans = AnimationUtils.loadAnimation(PartnerActivity.this, R.anim.translate);
                                partner_imv.startAnimation(am_trans);
                            }
                        });
                        p23.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                partner_imv.setImageDrawable(null);
                                partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.b23));
                                partner_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                Animation am_trans = AnimationUtils.loadAnimation(PartnerActivity.this, R.anim.translate);
                                partner_imv.startAnimation(am_trans);
                            }
                        });
                        p24.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                partner_imv.setImageDrawable(null);
                                partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.b24));
                                partner_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                Animation am_trans = AnimationUtils.loadAnimation(PartnerActivity.this, R.anim.translate);
                                partner_imv.startAnimation(am_trans);
                            }
                        });
                        p25.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                partner_imv.setImageDrawable(null);
                                partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.b25));
                                partner_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                Animation am_trans = AnimationUtils.loadAnimation(PartnerActivity.this, R.anim.translate);
                                partner_imv.startAnimation(am_trans);
                            }
                        });
                        p26.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                partner_imv.setImageDrawable(null);
                                partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.b26));
                                partner_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                Animation am_trans = AnimationUtils.loadAnimation(PartnerActivity.this, R.anim.translate);
                                partner_imv.startAnimation(am_trans);
                            }
                        });
                        p27.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                partner_imv.setImageDrawable(null);
                                partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.b27));
                                partner_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                Animation am_trans = AnimationUtils.loadAnimation(PartnerActivity.this, R.anim.translate);
                                partner_imv.startAnimation(am_trans);
                            }
                        });
                        p28.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                partner_imv.setImageDrawable(null);
                                partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.b28));
                                partner_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                Animation am_trans = AnimationUtils.loadAnimation(PartnerActivity.this, R.anim.translate);
                                partner_imv.startAnimation(am_trans);
                            }
                        });
                        p29.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                partner_imv.setImageDrawable(null);
                                partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.b29));
                                partner_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                Animation am_trans = AnimationUtils.loadAnimation(PartnerActivity.this, R.anim.translate);
                                partner_imv.startAnimation(am_trans);
                            }
                        });
                        p30.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                partner_imv.setImageDrawable(null);
                                partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.b30));
                                partner_imv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                                Animation am_trans = AnimationUtils.loadAnimation(PartnerActivity.this, R.anim.translate);
                                partner_imv.startAnimation(am_trans);
                            }
                        });
                        break;
                    default:
                        break;
                }
                //把當前新增layout的位置（position）做為Key傳過去
                return position;
                //return viewList.get(position % viewList.size());
            }
        });

        partner_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changePoints((position)%viewList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 動態新增ImageView
     */
    private void addImageView() {
        //設置fab menu圖片
        logo_fab.getMenuIconView().setImageResource(R.drawable.wqp_logo);
        //把view設置在最上層
        logo_fab.bringToFront();
    }

    /**
     * 設置圖片來源
     */
    private void setAnimatorToLarge() {
        partner_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                partner_imv.setImageDrawable(null);
                partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.bwt_ball));
                partner_imv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Animation am_trans = AnimationUtils.loadAnimation(PartnerActivity.this, R.anim.anim_alpha);
                partner_imv.startAnimation(am_trans);
            }
        });
    }

    /**
     * 設置圖片的特效
     *
     * @return
     */
    private void ItemAnimation() {
        Animation am_partner = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        partner_imv.startAnimation(am_partner);
    }

    protected void onRestart() {
        super.onRestart();
        Log.e("partnerActivity","onRestart");
        partner_imv.setImageDrawable(null);
        partner_imv.setImageDrawable(getResources().getDrawable(R.drawable.bwt_ball));
        partner_imv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //設置圖片的特效
        ItemAnimation();
    }
}