package hr.lknezovic.duptraningprogram;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class Main extends AppCompatActivity {

    private Button button1, button2;
    private TextView result, countDowntimer, nameView;
    private EditText weightText;
    private CountDownTimer timer;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nameView = (TextView) findViewById(R.id.Squat);
        button1 = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        result = (TextView) findViewById(R.id.result);
        weightText = (EditText) findViewById(R.id.weight);
        countDowntimer = (TextView) findViewById(R.id.timer);


        ref = FirebaseDatabase.getInstance().getReference("lifts");


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String b = button1.getText().toString();


                try {
                    String currentWeight = weightText.getText().toString();
                    Float oldWeight = Float.parseFloat(currentWeight);
                    Double newWeight = oldWeight + 2.5;

                    if (b.equals("")) {
                        button1.setText("5");

                        Toast.makeText(getApplicationContext(), "Congrats! Rest 3 minutes",
                                Toast.LENGTH_LONG).show();

                        startTimerSuccess();

                    }
                    if (b.equals("5")) {
                        button1.setText("4");
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b.equals("4")) {
                        button1.setText("3");
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b.equals("3")) {
                        button1.setText("2");
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b.equals("2")) {
                        button1.setText("1");
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b.equals("1")) {
                        button1.setText("0");
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b.equals("0")) {
                        button1.setText("");
                        stopTimer();
                        countDowntimer.setText("");
                    }

                    checkButtonValues(button1, button2);
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Entrer value for weight!",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String b2 = button2.getText().toString();

                try {
                    String currentWeight = weightText.getText().toString();
                    Float oldWeight = Float.parseFloat(currentWeight);
                    Double newWeight = oldWeight + 2.5;

                    if (b2.equals("")) {
                        button2.setText("5");
                        //result.setText("Congrats! Next time pick up" + newWeight + "kg");
                        Toast.makeText(getApplicationContext(), "Congrats! Rest 3 minutes",
                                Toast.LENGTH_LONG).show();
                        startTimerSuccess();
                    }
                    if (b2.equals("5")) {
                        button2.setText("4");
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b2.equals("4")) {
                        button2.setText("3");
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b2.equals("3")) {
                        button2.setText("2");
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b2.equals("2")) {
                        button2.setText("1");
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b2.equals("1")) {
                        button2.setText("0");
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b2.equals("0")) {
                        button2.setText("");
                        stopTimer();
                        countDowntimer.setText("");
                    }

                    checkButtonValues(button1, button2);
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Entrer value for weight!",
                            Toast.LENGTH_LONG).show();
                }


            }
        });


    }

    public void addWeight(Double weight) {
        String name = nameView.getText().toString().trim();
        String id = ref.push().getKey();

        Lift lift = new Lift(id, name, weight);
        ref.child(id).setValue(lift);


    }

    public Boolean checkButtonValues(Button button1, Button button2) {
        String b1 = button1.getText().toString();
        String b2 = button2.getText().toString();
        String currentWeight = weightText.getText().toString();
        Float oldWeight = Float.parseFloat(currentWeight);
        Double newWeight = oldWeight + 2.5;

        try {
            if (b1.equals("5") && b2.equals("5") && !b1.equals("") && !b2.equals("")) {
                result.setText("You strong!" + " Next time pick up " + newWeight + "kg");
                addWeight(newWeight);
                return true;
            } else if (b1.equals("") || b2.equals("")) {
                result.setText("");

            } else
                result.setText("Dude, get stronger. Next time pick up " + currentWeight + "kg");
                Double oldWright = Double.parseDouble(currentWeight);
                addWeight(oldWright);
        } catch (NumberFormatException e) {

        }
        return false;
    }

    public void startTimerSuccess() {
        timer = new CountDownTimer(60 * 3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                countDowntimer.setText("" + "If it was easy, rest for 1 minute and 30 seconds \n" + String.format("%d minutes, %d seconds",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            @Override
            public void onFinish() {
                countDowntimer.setText("On to your next set, lad!");
            }
        }.start();
    }

    public void startTimerFailed() {
        timer = new CountDownTimer(60 * 5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                countDowntimer.setText("" + "Rest for \n" + String.format("%d minutes, %d seconds",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            @Override
            public void onFinish() {
                countDowntimer.setText("On to your next set, lad!");
            }
        }.start();
    }

    public void stopTimer() {

        if (countDowntimer != null) {
            timer.cancel();
        }
    }
}
