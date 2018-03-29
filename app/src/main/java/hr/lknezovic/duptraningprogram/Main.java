package hr.lknezovic.duptraningprogram;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Main extends AppCompatActivity {

    private Button button1, button2, button3, button4, button5, bb1, bb2, bb3, bb4;
    private TextView result, countDowntimer, nameView, benchNameView, DLNameView;
    private EditText weightText, weightB, weightD;
    private CountDownTimer timer;
    DatabaseReference ref, refBench,refDL;
    private boolean squatClicked;

    List<Lift> weightPerLift;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nameView = (TextView) findViewById(R.id.Squat);
        button1 = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        weightText = (EditText) findViewById(R.id.weight);

        result = (TextView) findViewById(R.id.result);
        countDowntimer = (TextView) findViewById(R.id.timer);

        benchNameView = (TextView) findViewById(R.id.bench);
        weightB = (EditText) findViewById(R.id.weightBench);
        bb1 = (Button) findViewById(R.id.buttonBench1);
        bb2 = (Button) findViewById(R.id.buttonBench2);
        bb3 = (Button) findViewById(R.id.buttonBench3);
        bb4 = (Button) findViewById(R.id.buttonBench4);

        weightD = (EditText) findViewById(R.id.weightDeadlift);
        DLNameView = (TextView) findViewById(R.id.deadlift);


        weightPerLift = new ArrayList<>();

        ref = FirebaseDatabase.getInstance().getReference("lifts");

        refBench = FirebaseDatabase.getInstance().getReference("bench");

        refDL = FirebaseDatabase.getInstance().getReference("deadlift");

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
                        button1.setBackgroundResource(R.drawable.redroundbutton);
                        button1.setTextColor(getResources().getColor(R.color.textColor));
                        squatClicked = true;

                        Toast.makeText(getApplicationContext(), "Congrats! Rest 3 minutes",
                                Toast.LENGTH_LONG).show();

                        startTimerSuccess();

                    }
                    if (b.equals("5")) {
                        button1.setText("4");
                        button1.setBackgroundResource(R.drawable.redroundbutton);
                        button1.setTextColor(getResources().getColor(R.color.textColor));
                        squatClicked = true;

                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b.equals("4")) {
                        button1.setText("3");
                        button1.setBackgroundResource(R.drawable.redroundbutton);
                        button1.setTextColor(getResources().getColor(R.color.textColor));
                        squatClicked = true;

                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b.equals("3")) {
                        button1.setText("2");
                        button1.setBackgroundResource(R.drawable.redroundbutton);
                        button1.setTextColor(getResources().getColor(R.color.textColor));
                        squatClicked = true;

                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b.equals("2")) {
                        button1.setText("1");
                        button1.setBackgroundResource(R.drawable.redroundbutton);
                        button1.setTextColor(getResources().getColor(R.color.textColor));
                        squatClicked = true;

                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b.equals("1")) {
                        button1.setText("0");
                        button1.setBackgroundResource(R.drawable.redroundbutton);
                        button1.setTextColor(getResources().getColor(R.color.textColor));
                        squatClicked = true;

                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b.equals("0")) {
                        button1.setText("");
                        button1.setBackgroundResource(R.drawable.roundedbutton);
                        squatClicked = true;

                        stopTimer();
                        countDowntimer.setText("");
                    }

                    checkButtonValues(button1, button2, button3, button4, button5);
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Enter value for weight!",
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
                        button2.setBackgroundResource(R.drawable.redroundbutton);
                        button2.setTextColor(getResources().getColor(R.color.textColor));
                        //result.setText("Congrats! Next time pick up" + newWeight + "kg");
                        Toast.makeText(getApplicationContext(), "Congrats! Rest 3 minutes",
                                Toast.LENGTH_LONG).show();
                        startTimerSuccess();
                    }
                    if (b2.equals("5")) {
                        button2.setText("4");
                        button2.setBackgroundResource(R.drawable.redroundbutton);
                        button2.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b2.equals("4")) {
                        button2.setText("3");
                        button2.setBackgroundResource(R.drawable.redroundbutton);
                        button2.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b2.equals("3")) {
                        button2.setText("2");
                        button2.setBackgroundResource(R.drawable.redroundbutton);
                        button2.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b2.equals("2")) {
                        button2.setText("1");
                        button2.setBackgroundResource(R.drawable.redroundbutton);
                        button2.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b2.equals("1")) {
                        button2.setText("0");
                        button2.setBackgroundResource(R.drawable.redroundbutton);
                        button2.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b2.equals("0")) {
                        button2.setText("");
                        button2.setBackgroundResource(R.drawable.roundedbutton);
                        stopTimer();
                        countDowntimer.setText("");
                    }

                    checkButtonValues(button1, button2, button3, button4, button5);
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Enter value for weight!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String b3 = button3.getText().toString();


                try {
                    String currentWeight = weightText.getText().toString();
                    Float oldWeight = Float.parseFloat(currentWeight);
                    Double newWeight = oldWeight + 2.5;

                    if (b3.equals("")) {
                        button3.setText("5");
                        button3.setBackgroundResource(R.drawable.redroundbutton);
                        button3.setTextColor(getResources().getColor(R.color.textColor));

                        Toast.makeText(getApplicationContext(), "Congrats! Rest 3 minutes",
                                Toast.LENGTH_LONG).show();

                        startTimerSuccess();

                    }
                    if (b3.equals("5")) {
                        button3.setText("4");
                        button3.setBackgroundResource(R.drawable.redroundbutton);
                        button3.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b3.equals("4")) {
                        button3.setText("3");
                        button3.setBackgroundResource(R.drawable.redroundbutton);
                        button3.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b3.equals("3")) {
                        button3.setText("2");
                        button3.setBackgroundResource(R.drawable.redroundbutton);
                        button3.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b3.equals("2")) {
                        button3.setText("1");
                        button3.setBackgroundResource(R.drawable.redroundbutton);
                        button3.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b3.equals("1")) {
                        button3.setText("0");
                        button3.setBackgroundResource(R.drawable.redroundbutton);
                        button3.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b3.equals("0")) {
                        button3.setText("");
                        button3.setBackgroundResource(R.drawable.roundedbutton);
                        stopTimer();
                        countDowntimer.setText("");
                    }

                    checkButtonValues(button1, button2, button3, button4, button5);
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Enter value for weight!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String b4 = button4.getText().toString();

                try {
                    String currentWeight = weightText.getText().toString();
                    Float oldWeight = Float.parseFloat(currentWeight);
                    Double newWeight = oldWeight + 2.5;

                    if (b4.equals("")) {
                        button4.setText("5");
                        button4.setBackgroundResource(R.drawable.redroundbutton);
                        button4.setTextColor(getResources().getColor(R.color.textColor));

                        Toast.makeText(getApplicationContext(), "Congrats! Rest 3 minutes",
                                Toast.LENGTH_LONG).show();

                        startTimerSuccess();

                    }
                    if (b4.equals("5")) {
                        button4.setText("4");
                        button4.setBackgroundResource(R.drawable.redroundbutton);
                        button4.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b4.equals("4")) {
                        button4.setText("3");
                        button4.setBackgroundResource(R.drawable.redroundbutton);
                        button4.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b4.equals("3")) {
                        button4.setText("2");
                        button4.setBackgroundResource(R.drawable.redroundbutton);
                        button4.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b4.equals("2")) {
                        button4.setText("1");
                        button4.setBackgroundResource(R.drawable.redroundbutton);
                        button4.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b4.equals("1")) {
                        button4.setText("0");
                        button4.setBackgroundResource(R.drawable.redroundbutton);
                        button4.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b4.equals("0")) {
                        button4.setText("");
                        button4.setBackgroundResource(R.drawable.roundedbutton);
                        stopTimer();
                        countDowntimer.setText("");
                    }

                    checkButtonValues(button1, button2, button3, button4, button5);
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Enter value for weight!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String b5 = button5.getText().toString();


                try {
                    String currentWeight = weightText.getText().toString();
                    Float oldWeight = Float.parseFloat(currentWeight);
                    Double newWeight = oldWeight + 2.5;

                    if (b5.equals("")) {
                        button5.setText("5");
                        button5.setBackgroundResource(R.drawable.redroundbutton);
                        button5.setTextColor(getResources().getColor(R.color.textColor));

                        Toast.makeText(getApplicationContext(), "Congrats! Rest 3 minutes",
                                Toast.LENGTH_LONG).show();

                        startTimerSuccess();

                    }
                    if (b5.equals("5")) {
                        button5.setText("4");
                        button5.setBackgroundResource(R.drawable.redroundbutton);
                        button5.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b5.equals("4")) {
                        button5.setText("3");
                        button5.setBackgroundResource(R.drawable.redroundbutton);
                        button5.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b5.equals("3")) {
                        button5.setText("2");
                        button5.setBackgroundResource(R.drawable.redroundbutton);
                        button5.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b5.equals("2")) {
                        button5.setText("1");
                        button5.setBackgroundResource(R.drawable.redroundbutton);
                        button5.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b5.equals("1")) {
                        button5.setText("0");
                        button5.setBackgroundResource(R.drawable.redroundbutton);
                        button5.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (b5.equals("0")) {
                        button5.setText("");
                        button5.setBackgroundResource(R.drawable.roundedbutton);
                        stopTimer();
                        countDowntimer.setText("");
                    }

                    checkButtonValues(button1, button2, button3, button4, button5);
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Enter value for weight!",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        bb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bBench1 = bb1.getText().toString();


                try {
                    String currentWeight = weightB.getText().toString();
                    Float oldWeight = Float.parseFloat(currentWeight);
                    Double newWeight = oldWeight + 2.5;

                    if (bBench1.equals("")) {
                        bb1.setText("5");
                        bb1.setBackgroundResource(R.drawable.redroundbutton);
                        bb1.setTextColor(getResources().getColor(R.color.textColor));

                        Toast.makeText(getApplicationContext(), "Congrats! Rest 3 minutes",
                                Toast.LENGTH_LONG).show();

                        startTimerSuccess();

                    }
                    if (bBench1.equals("5")) {
                        bb1.setText("4");
                        bb1.setBackgroundResource(R.drawable.redroundbutton);
                        bb1.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (bBench1.equals("4")) {
                        bb1.setText("3");
                        bb1.setBackgroundResource(R.drawable.redroundbutton);
                        bb1.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (bBench1.equals("3")) {
                        bb1.setText("2");
                        bb1.setBackgroundResource(R.drawable.redroundbutton);
                        bb1.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (bBench1.equals("2")) {
                        bb1.setText("1");
                        bb1.setBackgroundResource(R.drawable.redroundbutton);
                        bb1.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (bBench1.equals("1")) {
                        bb1.setText("0");
                        bb1.setBackgroundResource(R.drawable.redroundbutton);
                        bb1.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (bBench1.equals("0")) {
                        bb1.setText("");
                        bb1.setBackgroundResource(R.drawable.roundedbutton);
                        stopTimer();
                        countDowntimer.setText("");
                    }

                    checkBenchButtonValues(bb1, bb2, bb3, bb4);
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Enter value for weight!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        bb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bBench2 = bb2.getText().toString();


                try {
                    String currentWeight = weightB.getText().toString();
                    Float oldWeight = Float.parseFloat(currentWeight);
                    Double newWeight = oldWeight + 2.5;

                    if (bBench2.equals("")) {
                        bb2.setText("5");
                        bb2.setBackgroundResource(R.drawable.redroundbutton);
                        bb2.setTextColor(getResources().getColor(R.color.textColor));

                        Toast.makeText(getApplicationContext(), "Congrats! Rest 3 minutes",
                                Toast.LENGTH_LONG).show();

                        startTimerSuccess();

                    }
                    if (bBench2.equals("5")) {
                        bb2.setText("4");
                        bb2.setBackgroundResource(R.drawable.redroundbutton);
                        bb2.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (bBench2.equals("4")) {
                        bb2.setText("3");
                        bb2.setBackgroundResource(R.drawable.redroundbutton);
                        bb2.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (bBench2.equals("3")) {
                        bb2.setText("2");
                        bb2.setBackgroundResource(R.drawable.redroundbutton);
                        bb2.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (bBench2.equals("2")) {
                        bb2.setText("1");
                        bb2.setBackgroundResource(R.drawable.redroundbutton);
                        bb2.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (bBench2.equals("1")) {
                        bb2.setText("0");
                        bb2.setBackgroundResource(R.drawable.redroundbutton);
                        bb2.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (bBench2.equals("0")) {
                        bb2.setText("");
                        bb2.setBackgroundResource(R.drawable.roundedbutton);
                        stopTimer();
                        countDowntimer.setText("");
                    }

                    checkBenchButtonValues(bb1, bb2, bb3, bb4);
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Enter value for weight!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        bb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bBench3 = bb3.getText().toString();


                try {
                    String currentWeight = weightB.getText().toString();
                    Float oldWeight = Float.parseFloat(currentWeight);
                    Double newWeight = oldWeight + 2.5;

                    if (bBench3.equals("")) {
                        bb3.setText("5");
                        bb3.setBackgroundResource(R.drawable.redroundbutton);
                        bb3.setTextColor(getResources().getColor(R.color.textColor));

                        Toast.makeText(getApplicationContext(), "Congrats! Rest 3 minutes",
                                Toast.LENGTH_LONG).show();

                        startTimerSuccess();

                    }
                    if (bBench3.equals("5")) {
                        bb3.setText("4");
                        bb3.setBackgroundResource(R.drawable.redroundbutton);
                        bb3.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (bBench3.equals("4")) {
                        bb3.setText("3");
                        bb3.setBackgroundResource(R.drawable.redroundbutton);
                        bb3.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (bBench3.equals("3")) {
                        bb3.setText("2");
                        bb3.setBackgroundResource(R.drawable.redroundbutton);
                        bb3.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (bBench3.equals("2")) {
                        bb3.setText("1");
                        bb3.setBackgroundResource(R.drawable.redroundbutton);
                        bb3.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (bBench3.equals("1")) {
                        bb3.setText("0");
                        bb3.setBackgroundResource(R.drawable.redroundbutton);
                        bb3.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (bBench3.equals("0")) {
                        bb3.setText("");
                        bb3.setBackgroundResource(R.drawable.roundedbutton);
                        stopTimer();
                        countDowntimer.setText("");
                    }

                    checkBenchButtonValues(bb1, bb2, bb3, bb4);
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Enter value for weight!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        bb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bBench4 = bb4.getText().toString();


                try {
                    String currentWeight = weightB.getText().toString();
                    Float oldWeight = Float.parseFloat(currentWeight);
                    Double newWeight = oldWeight + 2.5;

                    if (bBench4.equals("")) {
                        bb4.setText("5");
                        bb4.setBackgroundResource(R.drawable.redroundbutton);
                        bb4.setTextColor(getResources().getColor(R.color.textColor));

                        Toast.makeText(getApplicationContext(), "Congrats! Rest 3 minutes",
                                Toast.LENGTH_LONG).show();

                        startTimerSuccess();

                    }
                    if (bBench4.equals("5")) {
                        bb4.setText("4");
                        bb4.setBackgroundResource(R.drawable.redroundbutton);
                        bb4.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (bBench4.equals("4")) {
                        bb4.setText("3");
                        bb4.setBackgroundResource(R.drawable.redroundbutton);
                        bb4.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (bBench4.equals("3")) {
                        bb4.setText("2");
                        bb4.setBackgroundResource(R.drawable.redroundbutton);
                        bb4.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (bBench4.equals("2")) {
                        bb4.setText("1");
                        bb4.setBackgroundResource(R.drawable.redroundbutton);
                        bb4.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (bBench4.equals("1")) {
                        bb4.setText("0");
                        bb4.setBackgroundResource(R.drawable.redroundbutton);
                        bb4.setTextColor(getResources().getColor(R.color.textColor));
                        Toast.makeText(getApplicationContext(), "Failed! Rest 5 minutes",
                                Toast.LENGTH_LONG).show();
                        stopTimer();
                        startTimerFailed();
                    }
                    if (bBench4.equals("0")) {
                        bb4.setText("");
                        bb4.setBackgroundResource(R.drawable.roundedbutton);
                        stopTimer();
                        countDowntimer.setText("");
                    }

                    checkBenchButtonValues(bb1, bb2, bb3, bb4);
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Enter value for weight!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("lifts");
        Query chatQuery = ref.limitToLast(1);

        DatabaseReference refBench = FirebaseDatabase.getInstance().getReference().child("bench");
        Query benchQuery = refBench.limitToLast(1);

        DatabaseReference refDL = FirebaseDatabase.getInstance().getReference().child("deadlift");
        Query dlQuery = refDL.limitToLast(1);

        chatQuery.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot

                        if (dataSnapshot.exists()) {
                            collcetData((Map<String, Object>) dataSnapshot.getValue());

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });

        benchQuery.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot

                        if (dataSnapshot.exists()) {
                            collcetDataBench((Map<String, Object>) dataSnapshot.getValue());

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });

        dlQuery.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot

                        if (dataSnapshot.exists()) {
                            collcetDataDeadlift((Map<String, Object>) dataSnapshot.getValue());

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });
    }

    private void collcetData(Map<String, Object> data) {

        ArrayList<String> lifts = new ArrayList<>();
        ArrayList<String> weights = new ArrayList<>();

        //iterate through each user, ignoring their UID


        for (Map.Entry<String, Object> entry : data.entrySet()) {

            //Get user map
            Map singleLift = (Map) entry.getValue();
            Map singleWeight = (Map) entry.getValue();
            //Get phone field and append to list
            weights.add(String.valueOf(singleWeight.get("weight")));
            lifts.add((String) singleLift.get("name"));

            String weight = weights.get(weights.size() - 1);

            String lift = lifts.get(lifts.size() - 1);

                weightText.setText(weight);


        }

    }

    private void collcetDataBench(Map<String, Object> data) {

        ArrayList<String> lifts = new ArrayList<>();
        ArrayList<String> weights = new ArrayList<>();

        //iterate through each user, ignoring their UID


        for (Map.Entry<String, Object> entry : data.entrySet()) {

            //Get user map
            Map singleLift = (Map) entry.getValue();
            Map singleWeight = (Map) entry.getValue();
            //Get phone field and append to list
            weights.add(String.valueOf(singleWeight.get("weight")));
            lifts.add((String) singleLift.get("name"));

            String weight = weights.get(weights.size() - 1);

            String lift = lifts.get(lifts.size() - 1);

            weightB.setText(weight);

        }

    }

    private void collcetDataDeadlift(Map<String, Object> data) {

        ArrayList<String> lifts = new ArrayList<>();
        ArrayList<String> weights = new ArrayList<>();

        //iterate through each user, ignoring their UID


        for (Map.Entry<String, Object> entry : data.entrySet()) {

            //Get user map
            Map singleLift = (Map) entry.getValue();
            Map singleWeight = (Map) entry.getValue();
            //Get phone field and append to list
            weights.add(String.valueOf(singleWeight.get("weight")));
            lifts.add((String) singleLift.get("name"));

            String weight = weights.get(weights.size() - 1);

            String lift = lifts.get(lifts.size() - 1);

            weightD.setText(weight);

        }

    }


    public void addWeight(Double weight) {
        String name = nameView.getText().toString().trim();

        String id = ref.push().getKey();

        Lift lift = new Lift(id, name, weight);
        ref.child(id).setValue(lift);
    }

    public void addBenchWeight(Double weight) {
        String name = benchNameView.getText().toString().trim();

        String id = refBench.push().getKey();

        Lift lift = new Lift(id, name, weight);
        refBench.child(id).setValue(lift);
    }

    public void addDeadliftWeight(Double weight) {
        String name = DLNameView.getText().toString().trim();

        String id = refDL.push().getKey();

        Lift lift = new Lift(id, name, weight);
        refDL.child(id).setValue(lift);
    }


    public Boolean checkButtonValues(Button button1, Button button2, Button button3, Button button4, Button button5) {
        String b1 = button1.getText().toString();
        String b2 = button2.getText().toString();
        String b3 = button3.getText().toString();
        String b4 = button4.getText().toString();
        String b5 = button5.getText().toString();
        String currentWeight = weightText.getText().toString();
        Float oldWeight = Float.parseFloat(currentWeight);
        Double newWeight = oldWeight + 2.5;

        try {
            if (b1.equals("5") && b2.equals("5") && b3.equals("5") && b4.equals("5") && b5.equals("5") && !b1.equals("") && !b2.equals("") && !b3.equals("") && !b4.equals("") && !b5.equals("")) {
                result.setText("You strong!" + " Next time pick up " + newWeight + "kg");
                addWeight(newWeight);

                return true;
            } else if (b1.equals("") || b2.equals("") || b3.equals("") || b4.equals("") || b5.equals("")) {
                result.setText("");

            } else
                result.setText("Dude, get stronger. Next time pick up " + currentWeight + "kg");
            Double oldWright = Double.parseDouble(currentWeight);
            addWeight(oldWright);


        } catch (NumberFormatException e) {

        }
        return false;
    }


    public Boolean checkBenchButtonValues(Button bb1, Button bb2, Button bb3, Button bb4) {
        String bBench1 = bb1.getText().toString();
        String bBench2 = bb2.getText().toString();
        String bBench3 = bb3.getText().toString();
        String bBench4 = bb4.getText().toString();
        String currentWeight = weightB.getText().toString();
        Float oldWeight = Float.parseFloat(currentWeight);
        Double newWeight = oldWeight + 2.5;

        try {
            if (bBench1.equals("5") && bBench2.equals("5") && bBench3.equals("5") && bBench4.equals("5") && !bBench1.equals("") && !bBench2.equals("") && !bBench3.equals("") && !bBench4.equals("")) {
                result.setText("You strong!" + " Next time pick up " + newWeight + "kg");
                addBenchWeight(newWeight);

                return true;
            } else if (bBench1.equals("") || bBench2.equals("") || bBench3.equals("") || bBench4.equals("")) {
                result.setText("");

            } else
                result.setText("Dude, get stronger. Next time pick up " + currentWeight + "kg");
            Double oldWright = Double.parseDouble(currentWeight);
            addBenchWeight(oldWright);


        } catch (NumberFormatException e) {

        }
        return false;
    }

    public void startTimerSuccess() {
        String counter = countDowntimer.getText().toString();
        if (!counter.isEmpty()) {
            stopTimer();
        }

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
