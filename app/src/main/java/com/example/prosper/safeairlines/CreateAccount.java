package com.example.prosper.safeairlines;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import android.os.AsyncTask;
import android.content.Intent;

import Connection.Base;


public class CreateAccount extends AppCompatActivity {
    TextView viewtext;
    TextView textview;
  static  EditText nom, prenom, email, password, repeatpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nom = (EditText) findViewById(R.id.editTextnomaccount);
        prenom = (EditText) findViewById(R.id.editTextaccountprenom);
        email = (EditText) findViewById(R.id.editTextaccountemail);
        password = (EditText) findViewById(R.id.editTextaccountpass);
        repeatpassword = (EditText) findViewById(R.id.editText5);
        viewtext = (TextView) findViewById(R.id.textViewaccount);


    }
    public static boolean isEmailValid(String email) {
        return !(email == null || TextUtils.isEmpty(email)) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void onSendData(View view) {
        String pass = password.getText().toString();
        boolean email_res=true;
        boolean pass_not=true;
        if(TextUtils.isEmpty(pass) || pass.length() < 6)
        {
            password.setError("Vouz devez avoir au moins six caracteres");
            pass_not=false;
            return;
        }
        if(!isEmailValid(email.getText().toString().trim()))
            email.setError("Votre email n'est pas valid");


      if(valiDate() && isEmailValid(email.getText().toString().trim())&& pass_not) {
          HttpRequestTask t = new HttpRequestTask();
          t.setName(nom.getText().toString());
          t.setEm(email.getText().toString());
          t.setPa(password.getText().toString());
          t.setPren(prenom.getText().toString());

          t.execute();

      }
    }

    public boolean valiDate() {
        String no = nom.getText().toString().trim();
        String pre = prenom.getText().toString().trim();
        String em = email.getText().toString().trim();
        String pass = password.getText().toString().toString().trim();



        if (validatePass() && !no.isEmpty() && !pre.isEmpty() && !em.isEmpty() && !pass.isEmpty()) {

            return true;

        } else {


            Toast.makeText(getApplication(), "please fill in all the fields", Toast.LENGTH_LONG).show();


            return false;
        }


    }


    public boolean validatePass() {

        if (repeatpassword.getText().toString().trim().equals(password.getText().toString().trim())) {

            return true;
        } else {
          repeatpassword.setError("Vos mots de passe ne sont pas egaux");
            password.setError("Vos mots de passe ne sont pas egaux");
            return false;
        }


    }


    private class HttpRequestTask extends AsyncTask<Void, Void, Boolean> {
        private String name, pren, em, pa;


        @Override
        protected Boolean doInBackground(Void... params) {


          return  Base.insert(name, pren, em, pa);


        }
        public void onSubmitClicked(View v)
        {
            String pass = password.getText().toString();
            if(TextUtils.isEmpty(pass) || pass.length() < 6)
            {
                password.setError("Vouz devez avoir au moins six caracteres");
                return;
            }

            //continue processing

        }

        @Override
        protected void onPostExecute(Boolean result) {


            if(result){
                Toast.makeText(getApplicationContext(),"Sign in with your email",Toast.LENGTH_LONG).show();
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);

            }else{
                     email.setError("Votre email existe deja");


            }


        }

        public String getEm() {
            return em;
        }

        public String getName() {
            return name;
        }

        public String getPa() {
            return pa;
        }

        public String getPren() {
            return pren;
        }

        public void setEm(String em) {
            this.em = em;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPren(String pren) {
            this.pren = pren;
        }

        public void setPa(String pa) {
            this.pa = pa;
        }

    }

}



