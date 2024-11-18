package cl.scvg.barberia;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.Task;

public class Login extends AppCompatActivity {

    private FirebaseAuth auth;
    private GoogleSignInClient googleSignInClient;
    private static final int RC_SIGN_IN = 9001; // Código de solicitud para Google Sign-In
    private static final String TAG = "MainActivity";

    Button btLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        btLogin =(Button) findViewById(R.id.btSign);

        // Configurar Google Sign-In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("66882570676-d0cv6kkj7llgnsn8bkil2modt6rjph41.apps.googleusercontent.com") // Usa tu propio client ID aquí
                .requestEmail()
                .build();

        // Inicializa el cliente de Google Sign-In
        googleSignInClient = GoogleSignIn.getClient(this, gso);

        // Inicializa Firebase Auth
        auth = FirebaseAuth.getInstance();

        // Configurar el botón de inicio de sesión de Google
        btLogin.setOnClickListener(view -> signIn());


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    // Método para iniciar sesión con Google
    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Si el código de solicitud es RC_SIGN_IN, manejar el resultado
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // El inicio de sesión de Google fue exitoso, autenticar con Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class); // Aquí corregimos el manejo de la excepción
                if (account != null) {
                    firebaseAuthWithGoogle(account.getIdToken());
                }
            } catch (ApiException e) {
                // El inicio de sesión de Google falló
                Log.w(TAG, "Google sign in failed", e);
                Toast.makeText(this, "Google sign in failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Autenticación de Firebase con el ID Token de Google
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Inicio de sesión exitoso, obtener el usuario
                        FirebaseUser user = auth.getCurrentUser();
                        if (user != null) {
                            // Redirigir a otra actividad después del login exitoso
                            Intent intent = new Intent(this, MainActivitymenu.class); // Cambia HomeActivity por el nombre de tu siguiente actividad
                            startActivity(intent);
                            finish(); // Finaliza la actividad actual para que el usuario no vuelva atrás
                        }
                    } else {
                        // Si el inicio de sesión falla
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}