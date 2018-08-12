package com.sime.sms;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.security.Permission;

public class MainActivity extends AppCompatActivity {

    EditText numero;
    EditText sms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numero = (EditText) findViewById(R.id.numero);
        sms = (EditText) findViewById(R.id.sms);
    }

    public void enviarSms(View view) {
        String n = numero.getText().toString();
        String msg = sms.getText().toString();

        try{
            int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

            if(permissionCheck != PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getApplicationContext(), "No tiene permiso para enviar SMS", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(this, new  String[] {Manifest.permission.SEND_SMS}, 225);
            }

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(n, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "Mensaje enviado", Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Mensaje no enviado, verifique permisos o datos", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
