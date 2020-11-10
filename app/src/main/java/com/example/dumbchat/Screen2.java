package com.example.dumbchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Screen2 extends AppCompatActivity {

    public final static String TELA2_MSG_SALVA = "TELA2MSG";
    SharedPreferences banco;
    String mensagemTela1;
    TextView tvMessagem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);

        banco = this.getSharedPreferences(Screen1.DUMBCHATCACHE, Context.MODE_PRIVATE);

        Button btEnviar = findViewById(R.id.bt_send);
        tvMessagem = findViewById(R.id.tv_msg_recebida);
        final EditText edMsgSend = findViewById(R.id.ed_msg_send);

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mensagem = edMsgSend.getText().toString();
                SharedPreferences.Editor editory = banco.edit();
                editory.putString(TELA2_MSG_SALVA, mensagem);
                editory.apply();
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mensagemTela1 = banco.getString(Screen1.TELA1_MSG_SALVA, "Ainda não foi enviado nada!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvMessagem.setText("Recebido: "+mensagemTela1);
    }
}