package unitedwayadk.app211;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.*;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ViewServiceActivity extends AppCompatActivity implements View.OnClickListener {
    TextView phoneNumberView, phone2LabelView, phone2View, addressView;
    LinearLayout phoneNumberLayout, phone2Layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_service);

        String name = getIntent().getStringExtra("name").replaceAll("\"", "");
        String desc = getIntent().getStringExtra("desc").replaceAll("\"", "");
        String phoneNumber = getIntent().getStringExtra("phoneNumber").replaceAll("\"", "");
        String phone2Label = getIntent().getStringExtra("phone2Label").replaceAll("\"", "");
        String phone2 = getIntent().getStringExtra("phone2").replaceAll("\"", "");
        //String website = getIntent().getStringExtra("website").replaceAll("\"", "");
        final String address = getIntent().getStringExtra("address").replaceAll("\"", "");

        phoneNumberView = findViewById(R.id.service_phone_number);
        phoneNumberLayout = findViewById(R.id.service_phone_number_layout);
        phone2LabelView = findViewById(R.id.service_phone2_label);
        phone2View = findViewById(R.id.service_phone2);
        phone2Layout = findViewById(R.id.service_phone2_layout);
        addressView = findViewById(R.id.service_address);

        ((TextView)findViewById(R.id.service_name)).setText(name);
        ((TextView)findViewById(R.id.service_desc)).setText(desc);
        phoneNumberView.setOnClickListener(this);
        phoneNumberView.setMovementMethod(LinkMovementMethod.getInstance());
        phoneNumberView.setText(Html.fromHtml("<a href=''>" + phoneNumber + "</a>"));
        if(!phoneNumber.isEmpty()) {
            phoneNumberView.setOnClickListener(this);
            phoneNumberView.setMovementMethod(LinkMovementMethod.getInstance());
            phoneNumberView.setText(Html.fromHtml("<a href=''>" + phoneNumber + "</a>"));
        } else {
            ((ViewGroup)phoneNumberLayout.getParent()).removeView(phoneNumberLayout);
        }
        if(!phone2.isEmpty()) {
            phone2View.setOnClickListener(this);
            phone2View.setMovementMethod(LinkMovementMethod.getInstance());
            phone2View.setText(Html.fromHtml("<a href=''>" + phone2 + "</a>"));
            if(!phone2Label.isEmpty()) {
                phone2LabelView.setText(phone2Label);
            } else {
                phone2Layout.removeView(phone2LabelView);
            }
        } else {
            ((ViewGroup)phone2Layout.getParent()).removeView(phone2Layout);
        }
//        if(!website.isEmpty())
//            ((TextView)findViewById(R.id.service_website)).setText(Html.fromHtml("<a href='" + website + "'>" + website + "</a>"));
//        else
            ((ViewGroup)findViewById(R.id.service_website).getParent()).removeView(findViewById(R.id.service_website));
        if(!address.isEmpty()) {
            addressView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String uri = "geo:0,0?q=" + address;
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(intent);
                }
            });
            addressView.setMovementMethod(LinkMovementMethod.getInstance());
            addressView.setText(Html.fromHtml("<a href=''>" + address + "</a>"));
        } else {
            ((ViewGroup)addressView.getParent()).removeView(addressView);
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home)
            finish();
        return true;
    }

    @Override
    public void onClick(View view) {
        try {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:"+phoneNumberView.getText().toString()));
            startActivity(callIntent);
        } catch (ActivityNotFoundException activityException) {
            Log.e("Calling a Phone Number", "Call failed", activityException);
        }
    }
}
