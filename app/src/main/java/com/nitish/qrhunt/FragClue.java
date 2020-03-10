package com.nitish.qrhunt;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

import mehdi.sakout.fancybuttons.FancyButton;

public class FragClue extends Fragment {
    CodeScanner mCodeScanner;
    TextView tv_status_q1,tv_status_q2,tv_status_q3,tv_status_q4,tv_status_q5;
    FancyButton btn_close;
    FancyButton tv_verify_q1,tv_verify_q2,tv_verify_q3,tv_verify_q4,tv_verify_q5;

    String clueNo="1";
    String keyClue="x1";
    String hashAns="";



    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final Activity activity = getActivity();
        View root = inflater.inflate(R.layout.fragclue, container, false);

        Bundle args=getArguments();
        clueNo=args.getString("CLUE");
        keyClue=args.getString("KEY");

        hashAns=AES.encrypt(keyClue);

        tv_status_q1=activity.findViewById(R.id.tv_status_q1);
        tv_status_q2=activity.findViewById(R.id.tv_status_q2);
        tv_status_q3=activity.findViewById(R.id.tv_status_q3);
        tv_status_q4=activity.findViewById(R.id.tv_status_q4);
        tv_status_q5=activity.findViewById(R.id.tv_status_q5);

        tv_verify_q1=activity.findViewById(R.id.tv_verify_q1);
        tv_verify_q2=activity.findViewById(R.id.tv_verify_q2);
        tv_verify_q3=activity.findViewById(R.id.tv_verify_q3);
        tv_verify_q4=activity.findViewById(R.id.tv_verify_q4);
        tv_verify_q5=activity.findViewById(R.id.tv_verify_q5);

        btn_close=root.findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onBackPressed();
            }
        });

        final CodeScannerView scannerView = root.findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(activity, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (hashAns.equals(result.toString())){
                            AlertDialog alertDialog1 = new AlertDialog.Builder(activity)
                                    .setIcon(R.drawable.ic_done_black_24dp)
                                    .setTitle("Congrats !")
                                    .setMessage("You have solved Clue "+clueNo+"\n Keep Going!")
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            activity.onBackPressed();
                                        }
                                    })
                                    .show();
                            if (clueNo.equals("1")){
                                tv_status_q1.setText("clue solved !");
                                tv_status_q1.setTextColor(Color.parseColor("#7ab800"));
                                tv_verify_q1.setVisibility(View.GONE);

                            }else if (clueNo.equals("2")){
                                tv_status_q2.setText("clue solved !");
                                tv_status_q2.setTextColor(Color.parseColor("#7ab800"));
                                tv_verify_q2.setVisibility(View.GONE);

                            }else if (clueNo.equals("3")){
                                tv_status_q3.setText("clue solved !");
                                tv_status_q3.setTextColor(Color.parseColor("#7ab800"));
                                tv_verify_q3.setVisibility(View.GONE);

                            }else if (clueNo.equals("4")){
                                tv_status_q4.setText("clue solved !");
                                tv_status_q4.setTextColor(Color.parseColor("#7ab800"));
                                tv_verify_q4.setVisibility(View.GONE);

                            }else if (clueNo.equals("5")){
                                tv_status_q5.setText("clue solved !");
                                tv_status_q5.setTextColor(Color.parseColor("#7ab800"));
                                tv_verify_q5.setVisibility(View.GONE);
                            }else {
                                AlertDialog alertDialog = new AlertDialog.Builder(activity)
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .setTitle("You are at Wrong Location !")
                                        .setMessage("Read the CLUE again and try another location")
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                mCodeScanner.startPreview();
                                            }
                                        })
                                        .show();
                            }

                        }else {
                            AlertDialog alertDialog = new AlertDialog.Builder(activity)
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .setTitle("You are at Wrong Location !")
                                    .setMessage("Read the CLUE again and try another location")
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            mCodeScanner.startPreview();
                                        }
                                    })
                                    .show();
                        }
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    public void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }
}
