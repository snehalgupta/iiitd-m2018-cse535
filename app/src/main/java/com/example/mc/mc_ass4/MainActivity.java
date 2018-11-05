package com.example.mc.mc_ass4;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensor_manager;
    private Sensor accelerometer;
    private Sensor gyroscope;
    private Sensor proximity;
    private Sensor orientation;
    private LocationManager location_manager;
    private float acelVal;
    private float acelLast;
    private float shake;
    LocationListener locationListener;
    private Database_Helper db_helper;
    private TextView textView;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private Button button;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensor_manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensor_manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscope = sensor_manager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        orientation = sensor_manager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        location_manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        db_helper = new Database_Helper(getApplicationContext());
        textView = (TextView)findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView5 = (TextView) findViewById(R.id.textView5) ;
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensor_manager.registerListener(MainActivity.this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
                sensor_manager.registerListener(gyroscope_listener,gyroscope,SensorManager.SENSOR_DELAY_NORMAL);
                sensor_manager.registerListener(proximity_listener,proximity,SensorManager.SENSOR_DELAY_NORMAL);
                sensor_manager.registerListener(orientation_listener,orientation,SensorManager.SENSOR_DELAY_NORMAL);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sensor_manager.flush(MainActivity.this);
                sensor_manager.unregisterListener(MainActivity.this);
                sensor_manager.unregisterListener(gyroscope_listener);
                sensor_manager.unregisterListener(proximity_listener);
                sensor_manager.unregisterListener(orientation_listener);
                location_manager.removeUpdates(locationListener);
            }
        });

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //Toast.makeText(getApplicationContext(),location.getLatitude()+"",Toast.LENGTH_LONG).show();
                textView4.setText("Location: Latitude-"+location.getLatitude()+"Longitude-"+location.getLongitude());
                //db_helper.add_gps(System.currentTimeMillis()+"",location.getLatitude(),location.getLongitude());
                //Log.d("Current Latitude",location.getLatitude()+"");
                //Log.d("Current Longitude",location.getLongitude()+"");
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}
                        , 10);
            }
            return;
        }
        location_manager.requestLocationUpdates("gps", 0, 0, locationListener);
        proximity = sensor_manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        //sensor_manager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        //sensor_manager.registerListener(gyroscope_listener,gyroscope,SensorManager.SENSOR_DELAY_NORMAL);
        //sensor_manager.registerListener(proximity_listener,proximity,SensorManager.SENSOR_DELAY_NORMAL);
        //sensor_manager.registerListener(orientation_listener,orientation,SensorManager.SENSOR_DELAY_NORMAL);
        acelVal = SensorManager.GRAVITY_EARTH;
        acelLast = SensorManager.GRAVITY_EARTH;
        shake = 0.00f;
    }

    private final SensorEventListener orientation_listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] rotationMatrix = new float[16];
            SensorManager.getRotationMatrixFromVector(rotationMatrix, sensorEvent.values);
            float[] orientations = new float[3];
            SensorManager.getOrientation(rotationMatrix, orientations);
            for(int i = 0; i < 3; i++) {
                orientations[i] = (float)(Math.toDegrees(orientations[i]));
            }
            textView3.setText("Orientation: "+orientations[0]+","+orientations[1]+","+orientations[2]);
            //db_helper.add_ori(sensorEvent.timestamp+"",orientations[0],orientations[1],orientations[2]);
            Log.d("Orientation changed",orientations[0]+" "+orientations[1]+" "+orientations[2]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}
                                , 10);
                    }
                    return;
                }
                break;
            default:
                break;
        }
    }


    private final SensorEventListener gyroscope_listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if(sensorEvent.values[2] > 0.5f) {
                Toast toast = Toast.makeText(getApplicationContext(),"Object rotated anti-clockwise",Toast.LENGTH_SHORT);
                toast.show();
            } else if(sensorEvent.values[2] < -0.5f) { //
                Toast toast = Toast.makeText(getApplicationContext(),"Object rotated clockwise",Toast.LENGTH_SHORT);
                toast.show();
            }
            textView2.setText("Gyroscope: "+sensorEvent.values[0]+","+sensorEvent.values[1]+","+sensorEvent.values[2]);
            //db_helper.add_gyro(sensorEvent.timestamp+"",sensorEvent.values[0],sensorEvent.values[1],sensorEvent.values[2]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    private final SensorEventListener proximity_listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if(sensorEvent.values[0] >= -4 && sensorEvent.values[0] <= 4) {
                Toast toast = Toast.makeText(getApplicationContext(),"Object very close",Toast.LENGTH_SHORT);
                toast.show();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(),"Object far away",Toast.LENGTH_SHORT);
                toast.show();
            }
            textView5.setText("Proximity: "+sensorEvent.values[0]);
            //db_helper.add_pro(sensorEvent.timestamp+"",sensorEvent.values[0]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];
        acelLast = acelVal;
        acelVal = (float)Math.sqrt(x * x + y * y + z * z);
        float delta = acelVal - acelLast;
        shake = shake*0.9f + delta;
        if(shake > 12){
            Toast toast = Toast.makeText(getApplicationContext(),"Dont shake me",Toast.LENGTH_SHORT);
            toast.show();
        }
        textView.setText("Accelerometer: "+x+","+y+","+z);
        //db_helper.add_acel(sensorEvent.timestamp+"",sensorEvent.values[0],sensorEvent.values[1],sensorEvent.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    @Override
    public void onResume()
    {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}
                        , 10);
            }
            location_manager.requestLocationUpdates("gps", 0, 0, locationListener);
            return;
        }
        location_manager.requestLocationUpdates("gps", 0, 0, locationListener);

    }
    @Override
    public void onPause() {
        super.onPause();
        sensor_manager.unregisterListener(this);
        sensor_manager.unregisterListener(gyroscope_listener);
        sensor_manager.unregisterListener(proximity_listener);
        sensor_manager.unregisterListener(orientation_listener);
    }

}