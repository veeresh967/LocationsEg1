package com.veeresh.b37_locationseg1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv1, tv2;
    LocationManager manager;
    LocationListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.textView1);
        tv2 = (TextView) findViewById(R.id.textView2);
        manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //THIS METHOD WILL BE CALLED WHEN USER IS MOVING
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                tv1.setText("LATITUDE : " + latitude);
                tv2.setText("LONGITUDE : " + longitude);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
                if (i == LocationProvider.OUT_OF_SERVICE) {
                    Toast.makeText(MainActivity.this,
                            "SATELLITES OR CELL TOWERS UNAVAILABLE..TRY AFTER SOMETIME",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onProviderEnabled(String s) {
                Toast.makeText(MainActivity.this, "FETCHING..WAIT", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProviderDisabled(String s) {
                Toast.makeText(MainActivity.this, "PLZ ENABLE GPS",
                        Toast.LENGTH_SHORT).show();
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(this, "PERMISSION NOT GRANTED", Toast.LENGTH_SHORT).show();
            return;
        }
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                36000, 100, listener);
    }
}
