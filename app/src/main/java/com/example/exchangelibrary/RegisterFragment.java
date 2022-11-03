package com.example.exchangelibrary;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.Executor;

public class RegisterFragment extends Fragment {

    public FirebaseAuth mAuth;
//    public int duration = Toast.LENGTH_SHORT;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.register_activity,null);
        EditText email = (EditText) root.findViewById(R.id.et_email);
        EditText password = (EditText) root.findViewById(R.id.et_password);
        Button registerBtn = (Button) root.findViewById(R.id.btn_register);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailid = email.getText().toString();
                String passd = password.getText().toString();

                if (TextUtils.isEmpty(emailid) && TextUtils.isEmpty(passd)) {
                    Log.v("error", "Please enter user name and password");
                }
                else{
                    createAccount(emailid,passd);

                }
            }
        });
        return root;
//        return inflater.inflate(R.layout.register_activity, container, false);
    }


    private void createAccount(String email, String password) {
        // [START create_user_with_email]
        Log.d("Email","This is:"+email);
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("Success", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        }
//                        else {
//                            Log.d("Error","Failure");
//                            Log.w("Error", "createUserWithEmail:failure", task.getException());
//                            Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show();
//                        }
                    }
                });
        // [END create_user_with_email]
    }

}