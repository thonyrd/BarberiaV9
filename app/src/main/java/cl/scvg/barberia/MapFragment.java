package cl.scvg.barberia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    public MapFragment() {
        // Constructor vacío
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Infla el layout para este fragmento
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Configura el mapa aquí (marcadores, cámara, etc.)
        mMap = googleMap;
        LatLng sydney = new LatLng(-34.0, 151.0);
        LatLng Barberia01 = new LatLng(-36.60720924727195, -72.09975734777828);
        LatLng Barberia02 = new LatLng(-36.60783967296077, -72.09950223243297);

        mMap.addMarker(new MarkerOptions().position(Barberia01).title("Sucursal Principal"));
        mMap.addMarker(new MarkerOptions().position(Barberia02).title("Segunda Sucursal"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Barberia01, 18f));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Asegúrate de que el ID usado aquí coincida con el del layout del Fragment
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_fragment);
        if (mapFragment == null) {
            // Si el fragmento no se encontró, deberías agregarlo aquí
            mapFragment = new SupportMapFragment();
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.map_fragment, mapFragment) // Este ID debe coincidir con el del layout fragment_map
                    .commit();
        } else {
            mapFragment.getMapAsync(this);
        }
    }
}