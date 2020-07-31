package com.example.introschat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResponserActivity extends AppCompatActivity {


    public static final String RESPONSE = "REPLY";

    private TextView mMessageReplied;
    private EditText mMessageReply;
    private Button mButtonReply;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responser);
        mMessageReplied = findViewById(R.id.messages);
        mMessageReply = findViewById(R.id.messagetoreply);
        mButtonReply = findViewById(R.id.buttonReply);

        Intent intentReceiver = getIntent();
        String newMessage = intentReceiver.getStringExtra(MainActivity.SEND_MESSAGE);
        String newMessageReceive = mMessageReplied.getText().toString() + "\n" + "\n" + "Recebida:" + newMessage;
        mMessageReplied.setText(newMessageReceive);
        Toast.makeText(this,"Mesagem Recebida", Toast.LENGTH_SHORT).show();

        mButtonReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentResponder = new Intent();
                String newResponse = mMessageReply.getText().toString();
                String newMessageSend = mMessageReplied + "\n" + "\n" + "Respondida:" + newResponse;
                mMessageReplied.setText(newMessageSend);
                mMessageReply.setText("");
                intentResponder.putExtra(RESPONSE, newResponse);
                setResult(MainActivity.RESULT_OK, intentResponder);
                finish();

            }
        });
    }
}