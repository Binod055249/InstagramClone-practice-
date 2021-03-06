package com.example.instagramclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {

    EditText edtUserNameSignUp,edtUserNameLogin,edtPasswordSignup,edtPasswordLogin;
    Button btnSignUp,btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);

        edtUserNameSignUp=findViewById(R.id.edtUserNameSignup);
        edtPasswordSignup=findViewById(R.id.edtPasswordSignUp);
        edtUserNameLogin=findViewById(R.id.edtuserNameLogin);
        edtPasswordLogin=findViewById(R.id.edtPasswordLogin);

        btnSignUp=findViewById(R.id.btnSignUp);
        btnLogin=findViewById(R.id.btnLogin);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ParseUser appUser =new ParseUser();
                appUser.setUsername(edtUserNameSignUp.getText().toString());
                appUser.setPassword(edtPasswordSignup.getText().toString());

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                         if(e==null){
                             FancyToast.makeText(SignUpLoginActivity.this,appUser.get("username")+" is Signed Up Successfully",
                                     FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();

                         }else{
                             FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(),
                                     FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();

                         }

                    }
                });

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  ParseUser.logInInBackground(edtUserNameLogin.getText().toString(),
                          edtPasswordLogin.getText().toString(),
                          new LogInCallback() {
                              @Override
                              public void done(ParseUser user, ParseException e) {
                                  if(user!=null && e==null){
                                      FancyToast.makeText(SignUpLoginActivity.this, user.get("username")+" is login Sucessfully",
                                              Toast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                                  }else{
                                      FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(),
                                              Toast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                                  }

                              }
                          });

            }
        });



    }
}
