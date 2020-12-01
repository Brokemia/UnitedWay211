package unitedwayadk.app211;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ServiceTypeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_type);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home)
            finish();
        return true;
    }

    public void selectFoodBasicNeeds(View view) {
        Intent intent = new Intent(this, BrowseServicesActivity.class);
        MainActivity.serviceType = ServiceType.FoodBasicNeeds;
        startActivity(intent);
    }

    public void selectFamilyNeeds(View view) {
        Intent intent = new Intent(this, BrowseServicesActivity.class);
        MainActivity.serviceType = ServiceType.FamilyNeeds;
        startActivity(intent);
    }

    public void selectPublicHealth(View view) {
        Intent intent = new Intent(this, BrowseServicesActivity.class);
        MainActivity.serviceType = ServiceType.PublicHealth;
        startActivity(intent);
    }

    public void selectMentalHealth(View view) {
        Intent intent = new Intent(this, BrowseServicesActivity.class);
        MainActivity.serviceType = ServiceType.MentalHealth;
        startActivity(intent);
    }
}
