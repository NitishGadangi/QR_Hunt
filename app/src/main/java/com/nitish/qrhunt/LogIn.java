package com.nitish.qrhunt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class LogIn extends AppCompatActivity {
    String teamName;
    CodeScanner mCodeScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        final Intent intent = getIntent();
        teamName=intent.getStringExtra("TEAMNAME");

        findViewById(R.id.btn_demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),QuestionPaper.class);
                i.putExtra("TEAMKEY","a1a2a3a4a5");
                i.putExtra("TEAMNAME","DEMO");
                startActivity(i);
            }
        });

        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                final String test=codeVerify(result.toString());
                if (!test.equals("FAILED")){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AlertDialog alertDialog1 = new AlertDialog.Builder(LogIn.this)
                                    .setIcon(R.drawable.ic_done_black_24dp)
                                    .setTitle("Welcome "+teamName+" !")
                                    .setMessage("your id is "+test+"\nclick on START HUNT to begin Hunting")
                                    .setPositiveButton("START HUNT", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            Intent intent1=new Intent(getApplicationContext(),QuestionPaper.class);
                                            intent1.putExtra("TEAMKEY",test);
                                            intent1.putExtra("TEAMNAME",teamName);
                                            startActivity(intent1);
                                        }
                                    })
                                    .show();
                        }
                    });
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AlertDialog alertDialog = new AlertDialog.Builder(LogIn.this)
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .setTitle("Invalid LogIn QR !")
                                    .setMessage("Contact Organisers to get a proper working LogIn QR")
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            mCodeScanner.startPreview();
                                        }
                                    })
                                    .show();
                        }
                    });
                }
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
    }

    String codeVerify(String qrResult){
        //test
//        String test1= AES.encrypt("1234");
//        Log.i("ENC_S",test1);
//        Log.i("ENC_D",AES.decrypt(test1)+"------");

        //original
        String idObtained=AES.decrypt(qrResult);
        if (idObtained!=(null)){
            if (idObtained.length()==10){
                return idObtained;
            }
        }
        return "FAILED";
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }
}
