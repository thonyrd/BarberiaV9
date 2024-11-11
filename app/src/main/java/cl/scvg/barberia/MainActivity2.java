package cl.scvg.barberia;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cl.scvg.barberia.clases.Peluquero;

public class MainActivity2 extends AppCompatActivity {

    Button btnAdd;
    EditText et1,et2,et3;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);



        et1 = findViewById(R.id.ET1);
        et2 = findViewById(R.id.ET2);
        et3 = findViewById(R.id.ET3);
        btnAdd =findViewById(R.id.btnF);

        inicializarFireBase();



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = et1.getText().toString();
                String nombre = et2.getText().toString();
                String codigo = et3.getText().toString();

                if(id.isEmpty() && nombre.isEmpty() && codigo.isEmpty()){

                    Toast.makeText(getApplicationContext(),"Ingresar datos",Toast.LENGTH_SHORT).show();

                }else{

                    postPeluquero(id,nombre,codigo);

                }



            }
        });







        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void postPeluquero(String id, String nombre, String codigo) {

        Peluquero peluquero = new Peluquero();

        peluquero.setID(id);
        peluquero.setNombre(nombre);
        peluquero.setCodigo(codigo);

        databaseReference.child("Peluquero").child(peluquero.getID()).setValue(peluquero);



    }

    private void inicializarFireBase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference =firebaseDatabase.getReference();
    }
}