package com.example.introschat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final static String SEND_MESSAGE = "SEND";
    public final static int RESPONDER_CODE =  123;
    public static final int RESULT_OK = 1;


    private TextView mMessageStatus;
    private TextView mMessageReceived;
    private EditText mMessageSend;
    private Button mButtonSend;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMessageReceived = findViewById(R.id.messages);
        mMessageSend = findViewById(R.id.messagetosend);
        mButtonSend =  findViewById(R.id.buttonSend);


        mButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSender = new Intent(MainActivity.this, ResponserActivity.class);
                String newMessage = mMessageSend.getText().toString();
                String newMessageSend = mMessageReceived.getText().toString() + "\n" + "\n" + "Enviada:" + newMessage ;
                mMessageReceived.setText(newMessageSend);
                mMessageSend.setText("");
                intentSender.putExtra(SEND_MESSAGE, newMessage);
                startActivityForResult(intentSender, RESPONDER_CODE);
            }
        });
    }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent intentResponse ) {
            super.onActivityResult(requestCode, resultCode, intentResponse);

            if (resultCode == RESULT_OK && requestCode == RESPONDER_CODE) {
                String newResponse =intentResponse.getStringExtra(ResponserActivity.RESPONSE);
                String newMessageReply = mMessageReceived.getText().toString() + "\n" + "\n" + "Recebida:" + newResponse;
                mMessageReceived.setText(newMessageReply);
                Toast.makeText(this,"Resposta Recebida", Toast.LENGTH_SHORT).show();
            }

        }

  
}