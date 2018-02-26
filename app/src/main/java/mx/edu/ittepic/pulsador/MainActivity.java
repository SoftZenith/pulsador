package mx.edu.ittepic.pulsador;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    Button btnPulsar;
    TextView txtNumero, txtValue;
    SeekBar bar;
    Timer timer;
    double random = 0;
    double value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNumero = findViewById(R.id.txtRandom);
        txtValue = findViewById(R.id.txtValue);
        btnPulsar = findViewById(R.id.btnPulsar);
        bar = findViewById(R.id.skbSelector);
        bar.setMax(21);

        generarRandom();

        txtNumero.setText(random + "");

    }//onCreate

    private void generarRandom() {
        random = Math.round( ((Math.random() * (3.0 - 1.0)) + 1.0 ) * 10d)/10d;
        txtNumero.setText(random+"");
    }

    @Override
    protected void onResume() {
        super.onResume();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(bar.getProgress()==21){
                    bar.setProgress(0);
                }else {
                    value = ((bar.getProgress()*1.0)+10.0)/10;
                    bar.setProgress(bar.getProgress() + 1);
                }
            }
        },0,200);
    }


    public void jugar(View view) {

        if(random == value){
            txtValue.setText("Ganaste!");
            generarRandom();
        }else{
            txtValue.setText(value+"");
        }
    }
}
