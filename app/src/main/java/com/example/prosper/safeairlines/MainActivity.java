package com.example.prosper.safeairlines;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;

import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;
import android.graphics.Color;

import Connection.Base;
import Connection.Constants;

import android.util.Log;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //add iconn
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_home_white_24dp);





        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.createaccount) {
            // Handle the camera action

            Intent create_account = new Intent(getApplicationContext(), CreateAccount.class);
            startActivity(create_account);
        } else if (id == R.id.search) {
            Intent create_account = new Intent(getApplicationContext(), Rechercher.class);
            startActivity(create_account);
        } else if (id == R.id.signin) {

        } else if (id == R.id.about) {
            new Aboutdialog().execute();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onSendUser(View view) {
        EditText email = (EditText) findViewById(R.id.editTextsignEmail);
        EditText password = (EditText) findViewById(R.id.editTextsignPassword);
        if (!email.getText().toString().trim().isEmpty() && !password.getText().toString().trim().isEmpty()) {
            verifyUserDetails veryuser = new verifyUserDetails();
            veryuser.setEmail(email.getText().toString().trim());
            veryuser.setPassword(password.getText().toString().trim());
            veryuser.execute();

        }


    }



private class Aboutdialog extends AsyncTask<Void, Void, Boolean> {


    @Override
    protected Boolean doInBackground(Void... params) {
        return true;
    }

    @Override
    protected void onPostExecute(Boolean b) {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.MyAlertDialogStyle);
        builder.setTitle("About the our application");
        builder.setMessage("its an application which allows you to make flight reservations and to manage your Safe airlines Account");


        builder.setPositiveButton("dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}

private class verifyUserDetails extends AsyncTask<Void, Void, Client> {
    public String email, password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    protected Client doInBackground(Void... params) {
        return Base.clientDetails(email, password);

    }


    @Override
    protected void onPostExecute(Client client) {

        if (client.getId() > 0) {
            SharedPreferences share = getSharedPreferences("userinformation", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = share.edit();
            edit.putInt("id", client.getId());
            edit.putString("email", client.getEmail());
            edit.putString("nom", client.getNom());
            edit.putString("prenom", client.getPrenom());
            edit.putInt("points", client.getPoints());
            String clientsolde=Double.toString(client.getSolde());
            edit.putString("solde",clientsolde);
            edit.commit();
            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), Operations.class);
            startActivity(i);

        } else {

            Toast.makeText(getApplicationContext(), "please check your email and password", Toast.LENGTH_LONG).show();
        }


    }
}
}
























