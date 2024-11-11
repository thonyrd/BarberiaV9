package cl.scvg.barberia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
    String DIREC,codex,nombre1,nombre2;
    ListView lvLista;
    //lista para tener los datos de los peluqueros

    private List<Peluquero> listPelo = new ArrayList<Peluquero>();
    private List<String> ListProb = new ArrayList();
    private List<String> nombresP = new ArrayList();
    ArrayAdapter<String> arrayAdapterString;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);

        Intent intent = getIntent();

        DIREC = intent.getStringExtra("direccion");


        btn1 =findViewById(R.id.btnA1);
        btn2 =findViewById(R.id.btnB2);
        btn3 =findViewById(R.id.botonP);
        tv1 =findViewById(R.id.TVa);
        tv2 =findViewById(R.id.TVb);
        lvLista=findViewById(R.id.LvLista);

        inicializarFireBase();



//aqui puede estar el problema
        asignar();
            //PRECISAMENTE AQUI ESTA EL PROBLEM IGNORAR EL RESTO DE COMENTARIOS
        //tv1.setText(nombresP.get(0));
        //tv2.setText(nombresP.get(1));


        //tv1.setText(nombre1);
        //tv2.setText(nombre2);







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

                listPelo.clear();

                for(DataSnapshot objs : snapshot.getChildren()){

                    Peluquero pelo = objs.getValue(Peluquero.class);


                    codex=pelo.getCodigo();

                    if(DIREC.equals(codex)){

                        listPelo.add(pelo);

                    }

                    ListProb.add("dato 1: " + pelo.getID()+" dato2: "+ pelo.getNombre() + " dato 3"+pelo.getCodigo()+"     "+codex);
                    arrayAdapterString = new ArrayAdapter<>(MainActivity3.this, android.R.layout.simple_list_item_1,ListProb);
                    lvLista.setAdapter(arrayAdapterString);

                    //if(DIREC.equals(codex)){

                        /*if(nombre1.isEmpty()){
                            nombre1= pelo.getNombre();
                        }else{
                            nombre2 = pelo.getNombre();
                        }*/


                    //}



                }

                actualizarTextViews();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void actualizarTextViews() {
        if (listPelo.size() > 1) {
            tv1.setText(listPelo.get(0).getNombre());
            tv2.setText(listPelo.get(1).getNombre());
        } else {
            tv1.setText("No hay datos disponibles");
            tv2.setText("No hay datos disponibles");
        }
    }

    private void inicializarFireBase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference =firebaseDatabase.getReference();
    }
}