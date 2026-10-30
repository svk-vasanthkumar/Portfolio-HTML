
package com.example.emailapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText recipient, subject, message;
    Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipient = findViewById(R.id.editText);
        subject = findViewById(R.id.editText2);
        message = findViewById(R.id.editText3);
        sendBtn = findViewById(R.id.sendbttn);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }

    protected void sendEmail() {

        String[] TO = {
                recipient.getText().toString()
        };

        String emailSubject = subject.getText().toString();
        String emailMessage = message.getText().toString();

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailMessage);

        try {
            startActivity(Intent.createChooser(
                    emailIntent, "Send mail..."
            ));
        } catch (Exception ex) {
            Toast.makeText(
                    MainActivity.this,
                    "No email application found",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}
