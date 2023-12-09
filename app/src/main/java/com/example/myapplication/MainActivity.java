package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

public class MainActivity extends AppCompatActivity {

    EditText userIDEditText;
    Button startButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userIDEditText=findViewById(R.id.user_id_edit_text);
        startButton=findViewById(R.id.start_button);
        startButton.setOnClickListener((v)->{
            String userID=userIDEditText.getText().toString().trim();
            if(userID.isEmpty()){
                return;
            }
            startCall(userID);
            Intent intent=new Intent(MainActivity.this, CallActivity.class);
            intent.putExtra("User ID",userID);
            startActivity(intent);
        });
    }

    void startCall(String userID){
        Application application = getApplication(); // Android's application context
        long appID = 758915205;   // yourAppID
        String appSign = "346c6e695489e93d2bd3de0b751947310561526c3aecaede6f3049374005850d";  // yourAppSign
        String userName =userID;   // yourUserName

        ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();

        ZegoUIKitPrebuiltCallInvitationService.init(getApplication(), appID, appSign, userID, userName,callInvitationConfig);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        ZegoUIKitPrebuiltCallInvitationService.unInit();
    }
}