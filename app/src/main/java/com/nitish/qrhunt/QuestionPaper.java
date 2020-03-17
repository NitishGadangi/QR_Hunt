package com.nitish.qrhunt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import mehdi.sakout.fancybuttons.FancyButton;

public class QuestionPaper extends AppCompatActivity {
    TextView tv_teamName;
    TextView tv_ques_q1,tv_ques_q2,tv_ques_q3,tv_ques_q4,tv_ques_q5;
    FancyButton tv_verify_q1,tv_verify_q2,tv_verify_q3,tv_verify_q4,tv_verify_q5;
    String baseKey="hunt_";
    String[] keyList={"x1","x2","x3","x4","x5"};
    String teamName;
    String srcKey="x1x2x3x4x5";


    @Override
    public void onBackPressed() {
        FragClue test=(FragClue) getSupportFragmentManager().findFragmentByTag("CLUE_SCANNER");
        if (test != null && test.isVisible()) {
            //DO STUFF
            super.onBackPressed();
        }
        else {
            //Whatever
            AlertDialog alertDialog = new AlertDialog.Builder(QuestionPaper.this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Do not Go Back !")
                    .setMessage("You should not go back. Closing the app will ruin all your Game progress. Thus you end up loosing the Treasure.")
                    .setPositiveButton("Ok", null)
                    .show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_question_paper);

        tv_teamName=findViewById(R.id.tv_teamName);
        tv_ques_q1=findViewById(R.id.tv_ques_q1);
        tv_ques_q2=findViewById(R.id.tv_ques_q2);
        tv_ques_q3=findViewById(R.id.tv_ques_q3);
        tv_ques_q4=findViewById(R.id.tv_ques_q4);
        tv_ques_q5=findViewById(R.id.tv_ques_q5);
        tv_verify_q1=findViewById(R.id.tv_verify_q1);
        tv_verify_q2=findViewById(R.id.tv_verify_q2);
        tv_verify_q3=findViewById(R.id.tv_verify_q3);
        tv_verify_q4=findViewById(R.id.tv_verify_q4);
        tv_verify_q5=findViewById(R.id.tv_verify_q5);

        Intent intent = getIntent();
        teamName=intent.getStringExtra("TEAMNAME");
        srcKey=intent.getStringExtra("TEAMKEY");

        setKeyList(srcKey);

        tv_teamName.setText(teamName+" : "+srcKey);

        setQuestions(keyList,baseKey);

        tv_verify_q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("CLUE","1");
                bundle.putString("KEY",keyList[0]);

                Fragment mFragment = null;
                mFragment = new FragClue();
                mFragment.setArguments(bundle);
                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction().addToBackStack(null)
                        .replace(R.id.frameLayout, mFragment,"CLUE_SCANNER").commit();
            }
        });
        tv_verify_q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("CLUE","2");
                bundle.putString("KEY",keyList[1]);

                Fragment mFragment = null;
                mFragment = new FragClue();
                mFragment.setArguments(bundle);
                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction().addToBackStack(null)
                        .replace(R.id.frameLayout, mFragment,"CLUE_SCANNER").commit();
            }
        });
        tv_verify_q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("CLUE","3");
                bundle.putString("KEY",keyList[2]);

                Fragment mFragment = null;
                mFragment = new FragClue();
                mFragment.setArguments(bundle);
                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction().addToBackStack(null)
                        .replace(R.id.frameLayout, mFragment,"CLUE_SCANNER").commit();
            }
        });
        tv_verify_q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("CLUE","4");
                bundle.putString("KEY",keyList[3]);

                Fragment mFragment = null;
                mFragment = new FragClue();
                mFragment.setArguments(bundle);
                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction().addToBackStack(null)
                        .replace(R.id.frameLayout, mFragment,"CLUE_SCANNER").commit();
            }
        });
        tv_verify_q5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("CLUE","5");
                bundle.putString("KEY",keyList[4]);

                Fragment mFragment = null;
                mFragment = new FragClue();
                mFragment.setArguments(bundle);
                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction().addToBackStack(null)
                        .replace(R.id.frameLayout, mFragment,"CLUE_SCANNER").commit();
            }
        });



    }

    void setKeyList(String srcKey){
        keyList[0]=srcKey.substring(0,2);
        keyList[1]=srcKey.substring(2,4);
        keyList[2]=srcKey.substring(4,6);
        keyList[3]=srcKey.substring(6,8);
        keyList[4]=srcKey.substring(8,10);
    }

    void setQuestions(String[] keyList,String baseKey){
        tv_ques_q1.setText(getStringByIdName(getApplicationContext(),baseKey+keyList[0]));
        tv_ques_q2.setText(getStringByIdName(getApplicationContext(),baseKey+keyList[1]));
        tv_ques_q3.setText(getStringByIdName(getApplicationContext(),baseKey+keyList[2]));
        tv_ques_q4.setText(getStringByIdName(getApplicationContext(),baseKey+keyList[3]));
        tv_ques_q5.setText(getStringByIdName(getApplicationContext(),baseKey+keyList[4]));
    }

    public static String getStringByIdName(Context context, String idName) {
        Resources res = context.getResources();
        return res.getString(res.getIdentifier(idName, "string", context.getPackageName()));
    }

}
