package hr.lknezovic.duptraningprogram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PickSession extends AppCompatActivity {

    private Button s1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_session);


        s1 = (Button) findViewById(R.id.seesion1);

        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PickSession.this, Main.class);
                startActivity(intent);
            }
        });

    }
}
