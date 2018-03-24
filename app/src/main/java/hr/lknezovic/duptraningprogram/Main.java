package hr.lknezovic.duptraningprogram;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends AppCompatActivity {

    private Button button1, button2;
    private TextView result, countDowntimer;
    private EditText weight;
    private CountDownTimer timer;
    private int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final long bootTime = SystemClock.elapsedRealtime();


        button1 = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        result = (TextView) findViewById(R.id.result);
        weight = (EditText) findViewById(R.id.weight);
        countDowntimer = (TextView) findViewById(R.id.timer);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String b = button1.getText().toString();



                try {
                    String currentWeight = weight.getText().toString();
                    Float oldWeight = Float.parseFloat(currentWeight);
                    Double newWeight = oldWeight + 2.5;

                    if (b.equals("")) {
                        button1.setText("5");
                        //result.setText("Congrats! Next time pick up" + newWeight + "kg");
                        Toast.makeText(getApplicationContext(), "Congrats! Rest 3 minutes",
                                Toast.LENGTH_LONG).show();
                        startTimer();
                    }
                    if (b.equals("5")) {
                        button1.setText("4");
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                    }
                    if (b.equals("4")) {
                        button1.setText("3");
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                    }
                    if (b.equals("3")) {
                        button1.setText("2");
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                    }
                    if (b.equals("2")) {
                        button1.setText("1");
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                    }
                    if (b.equals("1")) {
                        button1.setText("0");
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                    }
                    if (b.equals("0")) {
                        button1.setText("");
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
                    String currentWeight = weight.getText().toString();
                    Float oldWeight = Float.parseFloat(currentWeight);
                    Double newWeight = oldWeight + 2.5;

                    if (b2.equals("")) {
                        button2.setText("5");
                        //result.setText("Congrats! Next time pick up" + newWeight + "kg");
                        Toast.makeText(getApplicationContext(), "Congrats! Rest 3 minutes",
                                Toast.LENGTH_LONG).show();
                    }
                    if (b2.equals("5")) {
                        button2.setText("4");
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                    }
                    if (b2.equals("4")) {
                        button2.setText("3");
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                    }
                    if (b2.equals("3")) {
                        button2.setText("2");
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                    }
                    if (b2.equals("2")) {
                        button2.setText("1");
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                    }
                    if (b2.equals("1")) {
                        button2.setText("0");
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                    }
                    if (b2.equals("0")) {
                        button2.setText("");
                    }

                    checkButtonValues(button1, button2);
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Entrer value for weight!",
                            Toast.LENGTH_LONG).show();
                }


            }
        });



    }
    public Boolean checkButtonValues(Button button1, Button button2) {
        String b1 = button1.getText().toString();
        String b2 = button2.getText().toString();
        String currentWeight = weight.getText().toString();
        Float oldWeight = Float.parseFloat(currentWeight);
        Double newWeight = oldWeight + 2.5;

        try {
            if (b1.equals("5") && b2.equals("5") && !b1.equals("") && !b2.equals("")) {
                result.setText("You strong!" + " Next time pick up " + newWeight + "kg");
                return true;
            } else if (b1.equals("") || b2.equals("")){
                result.setText("Do your next set");

            } else
            result.setText("Dude, get stronger. Next time pick up " + currentWeight + "kg");
        } catch (NumberFormatException e) {

        }
        return false;
    }

    public void startTimer(){
        timer = new CountDownTimer(60*1000, 1000) {
            @Override
            public void onTick(long milliSecondsUntillFinished) {
                countDowntimer.setText(String.valueOf(counter));
                counter++;
            }

            @Override
            public void onFinish() {

            }
        }.start();


    }
}
