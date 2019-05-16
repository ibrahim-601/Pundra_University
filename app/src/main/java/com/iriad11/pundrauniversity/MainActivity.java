package com.iriad11.pundrauniversity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;


import com.iriad11.pundrauniversity.datafromsheet.DBHelper;
import com.iriad11.pundrauniversity.datafromsheet.DataModel;
import com.iriad11.pundrauniversity.datafromsheet.Internet;
import com.iriad11.pundrauniversity.datafromsheet.Json;
import com.iriad11.pundrauniversity.datafromsheet.Keys;
import com.iriad11.pundrauniversity.fragments.Bus;
import com.iriad11.pundrauniversity.fragments.Cgpa;
import com.iriad11.pundrauniversity.fragments.Contacts;
import com.iriad11.pundrauniversity.fragments.Dev;
import com.iriad11.pundrauniversity.fragments.Home;
import com.iriad11.pundrauniversity.fragments.Routine;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<DataModel> array_list;
    private DBHelper mydb;
    public static int fr_count=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mydb =new DBHelper(this);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        displaySelectedScreen(R.id.nav_home);


        if (Internet.checkConnection(getApplicationContext())) {
            mydb.upgrade();
            new GetDataTask().execute();
        } else {
            Toast.makeText(MainActivity.this, "Internet connection not available routines won't be loaded", Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if( fr_count==1){
            displaySelectedScreen(R.id.nav_home);
        }
        else {
            super.onBackPressed();
        }
    }

    public void  displaySelectedScreen(int id){
        Fragment fragment=null;
        switch (id){

            case R.id.nav_contacts :
                fr_count=1;
                fragment = new Contacts();
                break;
            case R.id.nav_home :
                fr_count=0;
                fragment = new Home();
                break;
            case R.id.nav_bus :
                fr_count=1;
                fragment = new Bus();
                break;
            case R.id.nav_routine :
                fr_count=1;
                fragment = new Routine();
                break;
            case R.id.nav_cgpa:
                fr_count=1;
                fragment = new Cgpa();
                break;
            case R.id.nav_web:
                fr_count=1;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.pundrouniversity.edu.bd"));
                startActivity(browserIntent);
            case R.id.nav_dev:
                fr_count=2;
                fragment=new Dev();
                break;
            case R.id.nav_share:
                try {
                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("text/plain");
                    String shareBody = "https://play.google.com/store/apps/details?id=com.ibrahim.pub";
                    share.putExtra(Intent.EXTRA_SUBJECT, "Pundra University");
                    share.putExtra(Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(share, "Share via"));
                } catch(Exception e) {

                }
        }

        if (fragment!=null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displaySelectedScreen(item.getItemId());
        return true;
    }

    class GetDataTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        int jIndex;
        int x;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /**
             * Progress Dialog for User Interaction
             */
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setTitle("Please Wait...");
            dialog.setMessage("Getting Data from internet");
            dialog.show();
        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {

            /**
             * Getting JSON Object from Web Using okHttp
             */
            JSONObject jsonObject = Json.getDataFromWeb();

            try {
                /**
                 * Check Whether Its NULL???
                 */
                if (jsonObject != null) {
                    /**
                     * Check Length...
                     */
                    if(jsonObject.length() > 0) {
                        /**
                         * Getting Array named "contacts" From MAIN Json Object
                         */
                        JSONArray array = jsonObject.getJSONArray(Keys.KEY_CONTACTS);

                        /**
                         * Check Length of Array...
                         */


                        int lenArray = array.length();
                        if(lenArray > 0) {
                            for( ; jIndex < lenArray; jIndex++) {

                                /**
                                 * Creating Every time New Object
                                 * and
                                 * Adding into List
                                 */

                                /**
                                 * Getting Inner Object from contacts array...
                                 * and
                                 * From that We will get Name of that Contact
                                 *
                                 */
                                JSONObject innerObject = array.getJSONObject(jIndex);
                                String name = innerObject.getString(Keys.KEY_NAME);
                                String desig = innerObject.getString(Keys.KEY_DESIG);
                                String phone = "+" + innerObject.getString(Keys.KEY_PHONE);
                                String dept = innerObject.getString(Keys.KEY_DEPARTMENT);

                                /**
                                 * Getting Object from Object gsheets
                                 */

                                mydb.insertEmployee(name,desig,phone,dept);

                            }
                        }
                    }
                } else {

                }
            } catch (JSONException je) {
                Log.i(Json.TAG, "" + je.getLocalizedMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
        }
    }

}
