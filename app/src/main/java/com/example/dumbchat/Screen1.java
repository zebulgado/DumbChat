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
import android.widget.Toast;

import java.util.EventListener;

public class Screen1 extends AppCompatActivity {

    public final static String TELA1_MSG_SALVA = "TELA1MSG";
    public static final String DUMBCHATCACHE = "cachesalvo";
    TextView tvMensagemRecebida;
    TextView tvMensagemEnviada;
    SharedPreferences banco;
    String mensagemVeioDeLa;
    String mensagemQueVeioDaqui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        banco = this.getSharedPreferences(DUMBCHATCACHE, Context.MODE_PRIVATE);


        final EditText edMsg = findViewById(R.id.ed_msg_send);
        Button btEnviar = findViewById(R.id.bt_send);
        tvMensagemRecebida = findViewById(R.id.tv_msg_recebida);
        tvMensagemEnviada = findViewById(R.id.tv_msg_enviada);

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Screen1.this, Screen2.class);
                String mensagem = edMsg.getText().toString();
                tvMensagemEnviada.setText("Enviado: "+mensagem);
                SharedPreferences.Editor editorx = banco.edit();
                editorx.putString(TELA1_MSG_SALVA, mensagem);
                editorx.apply();
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mensagemVeioDeLa = banco.getString(Screen2.TELA2_MSG_SALVA, "Ainda não foi enviado nada!");
        mensagemQueVeioDaqui = banco.getString(TELA1_MSG_SALVA, "Ainda não foi enviado nada!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvMensagemRecebida.setText(mensagemVeioDeLa);
        tvMensagemEnviada.setText(mensagemQueVeioDaqui);
    }
}