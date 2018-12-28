package id.co.project.android.decoration;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class Profil extends AppCompatActivity {
    ImageButton igg, fbb;
    Toolbar toolbar;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
              igg= (ImageButton) findViewById(R.id.ig);
            igg.setOnClickListener(new View.OnClickListener() {
              @Override
                public void onClick(View view) {
                    Intent toIg = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/basrisab/?hl=en"));
                    startActivity(toIg);
                }
            });
                   fbb = (ImageButton) findViewById(R.id.fb);
            fbb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent toFb = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/basriumar12"));
                    startActivity(toFb);
                }
            });
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
}
