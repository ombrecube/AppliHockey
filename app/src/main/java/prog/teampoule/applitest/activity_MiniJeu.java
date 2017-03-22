package prog.teampoule.applitest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by Julien on 21/03/2017.
 */

public class activity_MiniJeu extends Menu implements SensorEventListener {

    double x=0, y=0, z=0;
    TextView txt_x;
    TextView txt_y;
    TextView txt_z;

    // Celui qui connait l'orientation de l'appareil
    private Display mDisplay;
    // Le sensor manager
    SensorManager sensorManager;
    // L'accéléromètre
    Sensor accelerometer;
    // La gravité
    Sensor gravity;
    // L'accéléromètre linéaire
    Sensor linearAcc;
    // Le capteur sélectionné
    private int sensorType;
    // L'accéléromètre
    private static final int ACCELE = 0;
    // La Gravité
    private static final int Gravity = 1;
    // L'accéléromètre linéaire
    private static final int LINEAR_ACCELE = 2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        stub.setLayoutResource(R.layout.activity_mini_jeu);
        View inflated = stub.inflate();

        /*
         *          Initialise les txtview
         */
        txt_x = (TextView) this.findViewById(R.id.valeur_x);
        txt_y = (TextView) this.findViewById(R.id.valeur_y);
        txt_z = (TextView) this.findViewById(R.id.valeur_z);
        /*
        *           Initialise les capteurs
        */
        // Instancier le SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // Instancier l'accéléromètre
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        // Instancier la gravité
        gravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        // Instancier l'accélération linéaire
        linearAcc = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        // Et enfin instancier le display qui connaît l'orientation de l'appareil
        mDisplay = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
    }

    @Override
    protected  void onPause(){
        sensorManager.unregisterListener(this, accelerometer);
        sensorManager.unregisterListener(this, gravity);
        sensorManager.unregisterListener(this, linearAcc);
        super.onPause();
    }

    @Override
    protected void onResume() {
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, gravity, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, linearAcc, SensorManager.SENSOR_DELAY_UI);
        super.onResume();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
// update only when your are in the right case:
        if (((event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) && (sensorType == ACCELE))
                || ((event.sensor.getType() == Sensor.TYPE_GRAVITY) && (sensorType == Gravity))
                || ((event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) && (sensorType == LINEAR_ACCELE))) {
            // Corriger les valeurs x et y en fonction de l'orientation de l'appareil
            switch (mDisplay.getRotation()) {
                case Surface.ROTATION_0:
                    x = event.values[0];
                    y = event.values[1];
                    break;
                case Surface.ROTATION_90:
                    x = -event.values[1];
                    y = event.values[0];
                    break;
                case Surface.ROTATION_180:
                    x = -event.values[0];
                    y = -event.values[1];
                    break;
                case Surface.ROTATION_270:
                    x = event.values[1];
                    y = -event.values[0];
                    break;
            }
            // la valeur z
            z = event.values[2];
            // faire quelque chose
            String xt = String.valueOf(x);
            String yt = String.valueOf(y);
            String zt = String.valueOf(z);
            if (xt != "" && xt != null)
                txt_x.setText(xt);
            if (yt != "" && yt != null)
                txt_y.setText(yt);
            if (zt != "" && zt != null)
                txt_z.setText(zt);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
