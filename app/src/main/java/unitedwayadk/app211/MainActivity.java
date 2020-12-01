package unitedwayadk.app211;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static County county;
    public static ServiceType serviceType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void selectClinton(View view) {
        Intent intent = new Intent(this, ServiceTypeActivity.class);
        county = County.Clinton;
        startActivity(intent);
    }

    public void selectEssex(View view) {
        Intent intent = new Intent(this, ServiceTypeActivity.class);
        county = County.Essex;
        startActivity(intent);
    }

    public void selectFranklin(View view) {
        Intent intent = new Intent(this, ServiceTypeActivity.class);
        county = County.Franklin;
        startActivity(intent);
    }

    public void selectHamilton(View view) {
        Intent intent = new Intent(this, ServiceTypeActivity.class);
        county = County.Hamilton;
        startActivity(intent);
    }
}