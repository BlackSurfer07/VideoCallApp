package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton;
import com.zegocloud.uikit.service.defines.ZegoUIKitUser;

import java.util.Collections;

public class CallActivity extends AppCompatActivity {

    EditText userIDEditText;
    TextView helloThereTextView;
    ZegoSendCallInvitationButton voiceCallButton, videoCallButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        userIDEditText=findViewById(R.id.user_id_edit_text);
        helloThereTextView=findViewById(R.id.hello_there_text_view);
        voiceCallButton=findViewById(R.id.voice_call_button);
        videoCallButton=findViewById(R.id.video_call_button);

        String userID=getIntent().getStringExtra("User ID");
        helloThereTextView.setText("Hello there, "+userID);

        userIDEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String targetUserID=userIDEditText.getText().toString().trim();
                setVoiceCall(targetUserID);
                setVideoCall(targetUserID);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    void setVoiceCall(String targetUserID){
        voiceCallButton.setIsVideoCall(false);
        voiceCallButton.setResourceID("zego_uikit_call"); // Please fill in the resource ID name that has been configured in the ZEGOCLOUD's console here.
        voiceCallButton.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUserID)));
    }

    void setVideoCall(String targetUserID){
        videoCallButton.setIsVideoCall(true);
        videoCallButton.setResourceID("zego_uikit_call"); // Please fill in the resource ID name that has been configured in the ZEGOCLOUD's console here.
        videoCallButton.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUserID)));
    }

}