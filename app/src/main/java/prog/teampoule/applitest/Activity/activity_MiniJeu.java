package prog.teampoule.applitest.Activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import prog.teampoule.applitest.Utilities.Menu;
import prog.teampoule.applitest.R;

/**
 * Created by Julien on 21/03/2017.
 */

public class activity_MiniJeu extends Menu implements SensorEventListener {

    private float xPos, xAccel, xVel = 0.0f;
    private float yPos, yAccel, yVel = 0.0f;
    private float xMax, yMax;
    private Bitmap ball , coeur;
    private Joueur joueur;
    private List<Ennemie> obstacle = new ArrayList<Ennemie>();
    Boolean bool = false;
    // Le sensor manager
    SensorManager sensorManager;
    // L'accéléromètre
    Sensor accelerometer;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        BallView ballView = new BallView(this);
        setContentView(ballView);
        ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
        //stub.setLayoutResource(R.layout.activity_mini_jeu);
        //View inflated = stub.inflate();
        joueur = new Joueur("Poule");

        Point size = new Point();
        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(size);
        xMax = (float) size.x - 100;
        yMax = (float) size.y - 200;
        xPos = (float) size.x/2;
        yPos = yMax;

        // Instancier le SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // Instancier l'accéléromètre
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected  void onPause(){
        sensorManager.unregisterListener(this, accelerometer);
        super.onPause();
    }

    @Override
    protected void onResume() {
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            xAccel = event.values[0];
            yAccel = -event.values[1];
            updateBall();
        }
    }

    private void updateBall() {
        //float frameTime = 0.666f;
        //xVel += (xAccel * frameTime);
        //yVel += (yAccel * frameTime);

        //float xS = xVel;
        //iofloat yS = (yVel / 2) * frameTime;
        if(xAccel>0)
            xPos -= 20;
        else
            xPos +=20;

        if (xPos > xMax) {
            xPos = xMax;
        } else if (xPos < 0) {
            xPos = 0;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private class BallView extends View {

        public BallView(Context context) {
            super(context);
            Bitmap ballSrc = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
            Bitmap CoeurSrc = BitmapFactory.decodeResource(getResources(), R.drawable.coeur);
            final int dstWidth = 100;
            final int dstHeight = 100;
            ball = Bitmap.createScaledBitmap(ballSrc, dstWidth, dstHeight, true);
            coeur = Bitmap.createScaledBitmap(CoeurSrc, 50, 50, true);
            new Thread(new Runnable() {
                Bitmap ballSrc = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
                public void run() {
                    while(true){
                        Bitmap obj = Bitmap.createScaledBitmap(ballSrc, dstWidth, dstHeight, true);
                        Ennemie en = new Ennemie(obj,xPos,100.0f);
                        obstacle.add(en);
                        SystemClock.sleep(4000);
                        if (joueur.getVie() <=0){
                            obstacle = new ArrayList<Ennemie>();
                            break;
                        }
                    }
                }
            }).start();

        }

        @Override
        protected void onDraw(Canvas canvas) {
            if (bool == false){
                for (int i=0;i<joueur.getVie();i++){
                    canvas.drawBitmap(coeur, 50+i*50,50,null);
                }
                canvas.drawBitmap(ball, xPos, yPos, null);
                //Log.d("onDraw",String.valueOf(obstacle.size()));
                for(int i = 0; i< obstacle.size();i++){
                    Ennemie en = obstacle.get(i);
                    en.Y +=12;
                    canvas.drawBitmap(en.image,en.X,en.Y,null);
                    if (en.Y > yMax+100){
                        obstacle.get(i).Y = 0;
                    }
                    if(yPos <= en.Y + 100 && en.X +100 > xPos && en.X <xPos +100){
                        joueur.setVie(joueur.getVie()-1);
                        Log.d("onDraw",String.valueOf(joueur.getVie()));
                        obstacle.get(i).Y = 0;
                    }
                }
            }else{
                canvas.drawText();
                // canvas.drawText("Jouez",xMax/2-20,yMax/2,new Paint(1));
            }
            invalidate();
        }

    }

    private class Ennemie{
        Bitmap image;
        float X,Y;
        public Ennemie(Bitmap bit,float posX, float posY){
            image = bit;
            X=posX;
            Y=posY;
        }
    }

    private class Joueur{
        int vie = 3;
        String nomJoueur;
        float record;
        public Joueur(String nom){
            nomJoueur = nom;
        }

        public int getVie() {
            return vie;
        }

        public void setVie(int vie) {
            this.vie = vie;
        }

        public String getNomJoueur() {
            return nomJoueur;
        }

        public void setNomJoueur(String nomJoueur) {
            this.nomJoueur = nomJoueur;
        }

        public float getRecord() {
            return record;
        }

        public void setRecord(float record) {
            this.record = record;
        }
    }
}
