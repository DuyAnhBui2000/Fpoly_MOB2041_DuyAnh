package buiduyanh.fpoly.buiduyanh_md18202.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import buiduyanh.fpoly.buiduyanh_md18202.LoginActivity;
import buiduyanh.fpoly.buiduyanh_md18202.R;

public class Chao2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chao2);


        HamChay3giay();

    }

    private void HamChay3giay(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Chao2Activity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}