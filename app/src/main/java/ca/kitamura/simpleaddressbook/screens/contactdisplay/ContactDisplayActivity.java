package ca.kitamura.simpleaddressbook.screens.contactdisplay;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ca.kitamura.simpleaddressbook.R;
import ca.kitamura.simpleaddressbook.models.randomuser.Result;
//This doesn't really need an mvp structure due to the small size and time constraints
public class ContactDisplayActivity extends AppCompatActivity {

    ImageView contactPhoto;
    TextView contactName;
    TextView contactNumber;
    TextView contactLocation;

    CardView cardViewNumber;
    CardView cardViewLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_display);

        //Bindings
        contactPhoto = (ImageView)findViewById(R.id.activity_contact_image);
        contactName = (TextView)findViewById(R.id.activity_contact_name);
        contactNumber = (TextView)findViewById(R.id.activity_contact_phone_number);
        contactLocation = (TextView)findViewById(R.id.activity_contact_location);

        cardViewNumber = (CardView)findViewById(R.id.activity_contact_phone_cardview);
        cardViewLocation = (CardView)findViewById(R.id.activity_contact_location_cardview);

        //Check if there is data or else go back
        if(getIntent().getExtras() != null) {
            final Result contactData = (Result) getIntent().getExtras().getSerializable("contactData");

            ActionBar ab = getSupportActionBar();
            ab.setTitle(String.format("%s %s",contactData.getName().getFirst(), contactData.getName().getLast()));
            ab.setDisplayHomeAsUpEnabled(true);

            Glide.with(this).load(contactData.getPicture().getLarge()).into(contactPhoto);
            contactName.setText(String.format("%s %s",contactData.getName().getFirst(), contactData.getName().getLast()));
            contactNumber.setText(contactData.getPhone());
            contactLocation.setText(String.format("%s, %s, %s",contactData.getLocation().getStreet(), contactData.getLocation().getCity(), contactData.getLocation().getCity()));

            //Dialer Trigger
            cardViewNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse(String.format("tel:%s", contactData.getPhone())));
                    startActivity(intent);
                }
            });

            //Google Maps
            cardViewLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(String.format("geo:0,0?q=%s+%s+%s",contactData.getLocation().getStreet(), contactData.getLocation().getCity(), contactData.getLocation().getCity())));
                    startActivity(intent);
                }
            });


        } else {
            //No data go back
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
                super.onBackPressed();
                return true;
        }
        return false;
    }
}
