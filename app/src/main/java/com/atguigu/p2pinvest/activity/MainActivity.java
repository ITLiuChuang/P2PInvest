package com.atguigu.p2pinvest.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.atguigu.p2pinvest.R;
import com.atguigu.p2pinvest.fragment.HomeFragment;
import com.atguigu.p2pinvest.fragment.InvestFragment;
import com.atguigu.p2pinvest.fragment.MoreFragment;
import com.atguigu.p2pinvest.fragment.PropertyFragment;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.main_rg)
    RadioGroup mainRg;
    private HomeFragment homeFragment;
    private FragmentTransaction ft;
    private InvestFragment investFragment;
    private PropertyFragment propertyFragment;
    private MoreFragment moreFragment;
    private boolean isExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initdata();
        initListener();
    }

    private void initListener() {
        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switchFragment(checkedId);
            }
        });
    }

    private void switchFragment(int checkedId) {
        ft = getSupportFragmentManager().beginTransaction();
        hiddenFragment(ft);
        switch (checkedId) {
            case R.id.rb_main:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.main_fl, homeFragment);
                }
                ft.show(homeFragment);
                break;
            case R.id.rb_invest:
                if (investFragment == null) {
                    investFragment = new InvestFragment();
                    ft.add(R.id.main_fl, investFragment);
                }
                ft.show(investFragment);
                break;
            case R.id.rb_propert:
                if (propertyFragment == null) {
                    propertyFragment = new PropertyFragment();
                    ft.add(R.id.main_fl, propertyFragment);
                }
                ft.show(propertyFragment);
                break;
            case R.id.rb_more:
                if (moreFragment == null) {
                    moreFragment = new MoreFragment();
                    ft.add(R.id.main_fl, moreFragment);
                }
                ft.show(moreFragment);
                break;
        }
        ft.commit();
    }

    private void hiddenFragment(FragmentTransaction ft) {
        if (homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (moreFragment != null) {
            ft.hide(moreFragment);
        }
        if (investFragment != null) {
            ft.hide(investFragment);
        }
        if (propertyFragment != null) {
            ft.hide(propertyFragment);
        }
    }

    private void initdata() {
        switchFragment(R.id.rb_main);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (isExit) {
                finish();
            }
            Toast.makeText(this, "在次点击退出", Toast.LENGTH_SHORT).show();
            isExit = true;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);

        }
        return true;

    }
}
