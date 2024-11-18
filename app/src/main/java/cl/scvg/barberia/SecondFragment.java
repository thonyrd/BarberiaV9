package cl.scvg.barberia;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment {
    Button viaje, mapaBt;
    TextView datos;
    private static final String ARG_ID = "id"; // Constante para la clave
    private String id_cita;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public SecondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.di
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters

    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public static SecondFragment newInstance(String id) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);








        if (getArguments() != null) {
            id_cita = getArguments().getString(ARG_ID);
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_second, container, false);

        // Asignar el botón después de inflar el layout
        viaje = view.findViewById(R.id.buttonViaje);
        mapaBt= (Button) view.findViewById(R.id.btMapa);

        //datos = view.findViewById(R.id.textViewDATOS);
        TextView datos = view.findViewById(R.id.textViewDATOS);
        if (id_cita != null) {
            datos.setText(id_cita); // Mostrar el valor en el TextView
        }

        // Aquí puedes agregar la lógica para el botón, por ejemplo, un OnClickListener
        viaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MainActivity.class);

                startActivity(intent);
                //datos.setText(DIRECT);



            }

        });

        mapaBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Aquí en lugar de lanzar una actividad, agregamos el MapFragment al contenedor actual
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new MapFragment()); // Aquí se debe usar el ID del contenedor de tu fragmento
                transaction.addToBackStack(null); // Opcional: Para que el fragmento se pueda regresar
                transaction.commit();
            }
        });


        return view;
    }



}
