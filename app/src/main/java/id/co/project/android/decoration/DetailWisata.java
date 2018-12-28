package id.co.project.android.decoration;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;


import id.co.project.android.decoration.apiservices.ApiService;
import id.co.project.android.decoration.clients.ApiClient;
import id.co.project.android.decoration.models.InsertFoodResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class DetailWisata extends AppCompatActivity {

    private Button btnInsert;
    private ProgressDialog progressDialog;

    TextView judul, dsk;
    ImageView gbr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        btnInsert = (Button) findViewById(R.id.button_insert_food);

        progressDialog = new ProgressDialog(this);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupInsertFood();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        judul =(TextView)findViewById(R.id.jdl);
        gbr =(ImageView)findViewById(R.id.img);
        dsk = (TextView)findViewById(R.id.desk);
        Intent intent= getIntent();
        String judul1 = intent.getStringExtra("judul");
        String gbr1 = intent.getStringExtra("gambar");
       String desk = intent.getStringExtra("desk");

        try {

            InputStream streamImg = getAssets().open(gbr1);
            Bitmap bmp = BitmapFactory.decodeStream(streamImg);
            gbr.setImageBitmap(bmp);

        } catch (IOException e) {
            e.printStackTrace();
        }




        judul.setText(judul1);
        dsk.setText(desk);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void popupInsertFood(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.popup_insert_food,null);
        builder.setView(view);

        final EditText etFoodcustomername = (EditText) view.findViewById(R.id.edit_text_food_customername);
        final EditText etFoodtelp = (EditText) view.findViewById(R.id.edit_text_food_telp);
        final EditText etFoodemail = (EditText) view.findViewById(R.id.edit_text_food_email);
        final EditText etFoodktp = (EditText) view.findViewById(R.id.edit_text_food_ktp);
        final EditText etFoodalamat = (EditText) view.findViewById(R.id.edit_text_food_alamat);
        final EditText etFoodpaket = (EditText) view.findViewById(R.id.edit_text_food_paket);

        progressDialog.setTitle("Inserting");
        progressDialog.setMessage("Please wait ....");
        progressDialog.show();

        builder.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                String customername = etFoodcustomername.getText().toString();
                String telp = etFoodtelp.getText().toString();
                String email = etFoodemail.getText().toString();
                String ktp = etFoodktp.getText().toString();
                String alamat = etFoodalamat.getText().toString();
                String paket = etFoodpaket.getText().toString();

                if(TextUtils.isEmpty(customername)){
                    Toast.makeText(DetailWisata.this, "Food Name is required", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(telp)){
                    Toast.makeText(DetailWisata.this, "Food Quantity is required", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(email)){
                    Toast.makeText(DetailWisata.this, "Food Quantity is required", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(ktp)){
                    Toast.makeText(DetailWisata.this, "Food Quantity is required", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(alamat)){
                    Toast.makeText(DetailWisata.this, "Food Quantity is required", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(paket)){
                    Toast.makeText(DetailWisata.this, "Food Quantity is required", Toast.LENGTH_SHORT).show();
                }else{
                    insertData(customername,telp,email,ktp,alamat,paket);
                }
            }
        });

        builder.show();
    }

    /**
     * this method used to send data to server or our local server
     * @param customername
     * @param telp
     * @param email
     * @param ktp
     * @param alamat
     * @param paket
     */
    private void insertData(String customername, String telp, String email, String ktp, String alamat, String paket){
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<InsertFoodResponseModel> call = apiService.insertFood(customername, telp,email,ktp,alamat,paket);
        call.enqueue(new Callback<InsertFoodResponseModel>() {
            @Override
            public void onResponse(Call<InsertFoodResponseModel> call, Response<InsertFoodResponseModel> response) {

                InsertFoodResponseModel insertFoodResponseModel = response.body();

                //check the status code
                if(insertFoodResponseModel.getStatus()==1){
                    Toast.makeText(DetailWisata.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }else{
                    Toast.makeText(DetailWisata.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<InsertFoodResponseModel> call, Throwable t) {
                Toast.makeText(DetailWisata.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

}
