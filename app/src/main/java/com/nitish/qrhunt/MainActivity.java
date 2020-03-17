package com.nitish.qrhunt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.thefinestartist.finestwebview.FinestWebView;

import mehdi.sakout.fancybuttons.FancyButton;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;

public class MainActivity extends AppCompatActivity {
    FancyButton btn_hunt,btn_whatsapp;
    ExtendedEditText tn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tn=findViewById(R.id.extended_edit_text);
        btn_whatsapp=findViewById(R.id.btn_whatsapp);
        btn_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://chat.whatsapp.com/CT65FzTFPZgJlW2rkGpyfc";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        tn.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER){
                    try {
                        InputMethodManager inputmanager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (inputmanager != null) {
                            inputmanager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                        }
                    } catch (Exception var2) {
                        var2.printStackTrace();
                    }
                }
                return true;
            }
        });

        btn_hunt=findViewById(R.id.btn_hunt);
        btn_hunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_DENIED){
                    ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CAMERA}, 401);
                }else {
                    Toast.makeText(MainActivity.this, "All Permissions Granted", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),LogIn.class);
                    intent.putExtra("TEAMNAME",tn.getText().toString());
                    startActivity(intent);
                }
            }
        });

        findViewById(R.id.tv_verify_q1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://forms.gle/51cSNdpXaVCYWEFX9";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        findViewById(R.id.tv_nitish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://nitishgadangi.github.io/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

    }
}
