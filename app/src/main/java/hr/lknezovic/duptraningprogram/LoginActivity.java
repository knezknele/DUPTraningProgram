package hr.lknezovic.duptraningprogram;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private LinearLayout proof;
    private Button signOut, startTraning;
    private SignInButton signIn;
    private TextView name, email;
    private GoogleApiClient clinet;
    private static final int REQ_CODE = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        proof = (LinearLayout) findViewById(R.id.proofSection);
        signOut = (Button) findViewById(R.id.logout);
        signIn = (SignInButton) findViewById(R.id.singIn);
        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        startTraning = (Button) findViewById(R.id.start);


        signIn.setOnClickListener(this);
        signOut.setOnClickListener(this);
        startTraning.setOnClickListener(this);
        proof.setVisibility(View.GONE);

        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        clinet = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.singIn:
                signIn();
                break;
            case R.id.logout:
                signOut();
                break;
            case R.id.start:
                start();
                break;
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void signIn(){
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(clinet);
        startActivityForResult(intent, REQ_CODE);
    }
    public void signOut(){
        Auth.GoogleSignInApi.signOut(clinet).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateUI(false);
            }
        });

    }

    public void start(){
        Intent intent = new Intent(LoginActivity.this, PickSession.class);
        startActivity(intent);
    }

    public void handleResult(GoogleSignInResult result){

        if (result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
            String Name = account.getDisplayName();
            String Email = account.getEmail();

            name.setText(Name);
            email.setText(Email);
            updateUI(true);
        }
        else {
            updateUI(false);
        }
    }

    public void updateUI(boolean isLogin){
        if(isLogin){


            signIn.setVisibility(View.GONE);
            proof.setVisibility(View.VISIBLE);
        }
        else {

            signIn.setVisibility(View.VISIBLE);
            proof.setVisibility(View.GONE);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE){

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }
    }
}
