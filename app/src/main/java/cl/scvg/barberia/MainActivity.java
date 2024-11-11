package cl.scvg.barberia;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button regreso;
    /*

    asignacion de botones y demaces

     */

    Button button1,button2,button5;
    String nombre = "";
    String nombre2 = "";
    TextView tv1;
    TextView tv2;

    DatabaseReference mDataBase;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        button1=findViewById(R.id.bt1);
        button2=findViewById(R.id.bt2);
        regreso =findViewById(R.id.buttonRegreso);

        button5=findViewById(R.id.bt5);
        tv1 = findViewById(R.id.textView1);
        tv2 = findViewById(R.id.textView1);


        mDataBase = FirebaseDatabase.getInstance().getReference();



        /*
        los botones asignaran una direccion para pasarla a otra pantalla con el %%intent%%
         */
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre2 = "ZZ";
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre2 = "FF";
            }
        });
/*
en este boton se pasa a la otra pantalla y se pasa el dato con el intent pero no funciona o al menos eso creo (aislar problema)
 */

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv1.setText(nombre);

                Intent intent = new Intent(MainActivity.this, MainActivity3.class);

                intent.putExtra("direccion",nombre);


                startActivity(intent);

            }
        });
        regreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                 Intent intent5 = new Intent(MainActivity.this, MainActivitymenu.class);

                intent5.putExtra("direcciono",nombre2);


                startActivity(intent5);

            }
        });






        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}