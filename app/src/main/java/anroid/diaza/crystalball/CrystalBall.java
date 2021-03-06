package anroid.diaza.crystalball;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.FloatMath;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class CrystalBall extends Activity {

    private TextView answerText;

    private SensorManager sensorManager;
    private Sensor accelerometer;

    // Variables for the determining of the shake
    private float acceleration;
    private float currentAcceleration;
    private float previousAcceleration;

    // Variables for the determining of the delay
    long currentTime;
    long previousTime;
    long delay;
    long elapsed;


    private final SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            // Sets the delay count
            previousTime = currentTime;
            currentTime = System.currentTimeMillis();
            elapsed = currentTime - previousTime;
            delay = delay + elapsed;


            //checks if the phone if shaking using math n' stuff
            previousAcceleration = currentAcceleration;
            currentAcceleration = FloatMath.sqrt(x * x + y * y + z * z );
            float delta = currentAcceleration - previousAcceleration;
            acceleration = acceleration * 0.9f + delta;

            //sets up the animation variable to be able to use
            final ImageView img = (ImageView)findViewById(R.id.animation);
            img.setBackgroundResource(R.drawable.animation);

            final AnimationDrawable animation = (AnimationDrawable)img.getBackground();

            // Gets the prediction
            answerText = (TextView) findViewById(R.id.answerText);

            if(delay > 15000){

                answerText.setText("Don't Forget To Shake");
                answerText.startAnimation(AnimationUtils.loadAnimation(CrystalBall.this, android.R.anim.fade_out));

            }

            // Checks if the Time Has Passed and if the Device has shaken
            if(acceleration > 10 && delay >= 4300) {

                answerText.setText("");

                // Plays the Mario Jump tune after shake
                final MediaPlayer jump = MediaPlayer.create(getApplicationContext(), R.raw.jumping);
                jump.start();

                //Checks if the animation is running, stops it and starts it again
                if (animation.isRunning()) {

                    animation.stop();
                    animation.start();
                }

                //If previous if statement is false it starts up the animation
                else {
                    animation.start();

                }

                //Time it takes(in milliseconds) for the The block sound to play
                new CountDownTimer(400, 1000) {

                    public void onTick(long millisUntilFinished) {
                    }

                    //If the timer is done counting down it does the following
                    public void onFinish() {

                        // Plays the bump sound after 0.4 sec
                        final MediaPlayer bump = MediaPlayer.create(getApplicationContext(), R.raw.bump);
                        bump.start();

                    }
                    //Starts the Countdown timer of the text being set to blank
                }.start();

                //Time it takes(in milliseconds) for the Text to appear
                new CountDownTimer(1600, 1000) {

                    public void onTick(long millisUntilFinished) {
                    }

                    //If the timer is done counting down it does the following
                    public void onFinish() {

                        // Plays the Opening tune during text appears
                        final MediaPlayer open = MediaPlayer.create(getApplicationContext(), R.raw.open);
                        open.start();

                        // Gets the prediction
                        answerText.setText(Predictions.get().getPredictions());

                        // Plays the fade in animation of the prediction
                        answerText.startAnimation(AnimationUtils.loadAnimation(CrystalBall.this, android.R.anim.fade_in));

                        //Time it takes(in milliseconds) for the Text to disappear
                        new CountDownTimer(2500, 1000) {

                            public void onTick(long millisUntilFinished) {
                            }

                            //If the timer is done counting down it does the following
                            public void onFinish() {

                                // Plays the fade out animation of the prediction
                                answerText.startAnimation(AnimationUtils.loadAnimation(CrystalBall.this, android.R.anim.fade_out));

                                //Time it takes(in milliseconds) for the Text to become blank(almost instant)
                                new CountDownTimer(350, 1000) {

                                    public void onTick(long millisUntilFinished) {
                                    }

                                    //If the timer is done counting down it does the following
                                    public void onFinish() {

                                        // Sets the text to Blank
                                        answerText.setText("");

                                    }
                                    //Starts the Countdown timer of the text being set to blank
                                }.start();

                            }
                            //Starts the countdown timer for the text to fade away
                        }.start();

                    }
                    //Starts the countdown time for the text to appear
                }.start();

                // Resets the delay to 0
                delay = 0;

            }

        }



        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    //This is where the app first goes when it first boots up   -------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crystal_ball);

        // This sets up for the use of delay
        delay = 4500;
        previousTime = System.currentTimeMillis();
        currentTime = System.currentTimeMillis();

        answerText = (TextView) findViewById(R.id.answerText);
        answerText.setText("(Shake For Prediction)");

        // This is what listens for and allows us to know when the device is shaken
        sensorManager = (SensorManager)getSystemService (Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        acceleration = 0.0f;
        currentAcceleration = SensorManager.GRAVITY_EARTH;
        previousAcceleration = SensorManager.GRAVITY_EARTH;

    }

    // Comes here right after the onCreate Function   -----------------------------------------------------------------------------------

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    // Goes here if the Application is left   -------------------------------------------------------------------------------------------

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorListener);
    }
}
