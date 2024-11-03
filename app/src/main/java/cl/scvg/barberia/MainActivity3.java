package cl.scvg.barberia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import cl.scvg.barberia.clases.Peluquero;

public class MainActivity3 extends AppCompatActivity {

    Button btn1,btn2,btn3;
    TextView tv1,tv2;
    //lista para tener los datos de los peluqueros
    private ArrayList<Peluquero> listilla = new ArrayList<>();

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Intent intent = getIntent();

    String DIREC = intent.getStringExtra("direccion");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);

        inicializarFireBase();

        btn1 =findViewById(R.id.btnA1);
        btn2 =findViewById(R.id.btnB2);
        btn3 =findViewById(R.id.botonP);
        tv1 =findViewById(R.id.TVa);
        tv2 =findViewById(R.id.TVb);

//aqui puede estar el problema
        asignar();

        tv1.setText(listilla.get(0).getNombre());
        tv2.setText(listilla.get(1).getNombre());







        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
/*
aqui estaria el meoyo(meollo?)


aqui se recorreria la base de datos (probar como en el programa anterior)

de esta forma se lograria encontrar a los peluqueros atraves de la direccion
se usarian los nombres para rellenar los text view

y se mantienen en un array para asignar la id correspondiente en una variable pra con los botones llevarla a otra pantalla....
 */
    private void asignar() {

        databaseReference.child("Peluquero").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot objs : snapshot.getChildren()){

                    Peluquero pelo = objs.getValue(Peluquero.class);

                    if(pelo.getCodigo().equals(DIREC)){

                        listilla.add(pelo);


                    }



                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void inicializarFireBase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference =firebaseDatabase.getReference();
    }
}