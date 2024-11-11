package cl.scvg.barberia;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import cl.scvg.barberia.clases.Peluquero;

public class FourFragment extends Fragment {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private List<Peluquero> listPelo = new ArrayList<>();
    private List<String> ListProb = new ArrayList<>();
    private ListView lvLista;
    private ArrayAdapter<String> arrayAdapterString;

    public FourFragment() {
        // Constructor público vacío requerido
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate el layout para este fragmento
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        // Asignar ListView después de inflar el layout
        lvLista = view.findViewById(R.id.LvLista);

        // Inicializar el adaptador y asignarlo al ListView
        arrayAdapterString = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, ListProb);
        lvLista.setAdapter(arrayAdapterString);

        // Llamar al método para asignar datos de Firebase
        asignar();
        inicializarFireBase();
        return view;
    }



    private void asignar() {
        databaseReference.child("Peluquero").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listPelo.clear();
                ListProb.clear(); // Limpiar la lista para evitar duplicados

                for (DataSnapshot objs : snapshot.getChildren()) {
                    Peluquero pelo = objs.getValue(Peluquero.class);
                    if (pelo != null) {
                        ListProb.add("dato 1: " + pelo.getID() + " dato 2: " + pelo.getNombre() + " dato 3: " + pelo.getCodigo());
                    }
                }

                // Notificar al adaptador que los datos han cambiado
                arrayAdapterString.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Manejo del error si es necesario
            }
        });
    }
    private void inicializarFireBase(){
        FirebaseApp.initializeApp(getContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference =firebaseDatabase.getReference();
    }
}












