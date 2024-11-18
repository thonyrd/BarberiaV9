package cl.scvg.barberia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cl.scvg.barberia.clases.Cita;
import cl.scvg.barberia.clases.Peluquero;

public class MainActivity4 extends AppCompatActivity {

    CalendarView calendarView;
    //TimePicker timePicker;
    Button guardar;
    String lugar, trabajador,fecha,hora;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main4);

        Intent intent = getIntent();

        lugar = intent.getStringExtra("direccion");
        trabajador = intent.getStringExtra("peluquero");


        calendarView = findViewById(R.id.calendario);
        //timePicker = findViewById(R.id.timePicker);
        guardar = findViewById(R.id.buttonGuardar);

        inicializarFireBase();


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // La fecha seleccionada
                fecha = dayOfMonth + "/" + (month + 1) + "/" + year;



            }
        });

        /*timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                // Formatea la hora seleccionada
                hora = String.format("%02d:%02d", hourOfDay, minute);

            }
        });*/



        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //id deberia ser algo ligado al inicio de sesion
                //pa que no de error
                String id = "33333";
                postCita(id,trabajador,lugar,fecha,hora);

                Intent intent_lista = new Intent(MainActivity4.this, MainActivitymenu.class);

                intent_lista.putExtra("id",id);


                startActivity(intent_lista);


            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void postCita(String id, String peluquero, String lugar,String dates,String horas) {

        Cita cita = new Cita();

        cita.setID(id);
        cita.setPeluquero(peluquero);
        cita.setLugar(lugar);
        cita.setFecha(dates);
        cita.setHora(horas);

        databaseReference.child("Citas").child(cita.getID()).setValue(cita);



    }

    private void inicializarFireBase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference =firebaseDatabase.getReference();
    }
}