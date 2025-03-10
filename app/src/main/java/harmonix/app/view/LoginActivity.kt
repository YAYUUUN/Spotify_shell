package harmonix.app.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import harmonix.app.R

class LoginActivity : AppCompatActivity() {

    // Declaración de los elementos de la interfaz de usuario
    private lateinit var btnLogin: Button
    private lateinit var btnSignup: Button
    private lateinit var tvGuest: TextView

//    onCreate() es un metodo que se llama cuando la actividad se inicia
//    El super.onCreate(savedInstanceState) es una llamada al metodo de la clase padre,
//    el padre de la clase LoginActivity es AppCompatActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Establece el layout de la actividad
        setContentView(R.layout.activity_login) // Asegúrate de que el archivo XML se llame "activity_login.xml"

        // Inicialización de los elementos de la interfaz de usuario
        btnLogin = findViewById(R.id.btnLogin)
        btnSignup = findViewById(R.id.btnSignup)
        tvGuest = findViewById(R.id.tvGuest)

        // Configuración de los listeners para los botones
        btnLogin.setOnClickListener {
            // Acción cuando se hace clic en el botón "Log in"
//            Toast.makeText(this, "Log in clicked", Toast.LENGTH_SHORT).show()

            // Navegar a LoginEmailActivity
            val intent = Intent(this, LoginEmailActivity::class.java)
            startActivity(intent)
        }

        btnSignup.setOnClickListener {
            // Acción cuando se hace clic en el botón "Sign up with E-mail"
//            Toast.makeText(this, "Sign up clicked", Toast.LENGTH_SHORT).show()

            // Navegar a SignupActivity (si existe)
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        tvGuest.setOnClickListener {
            // Acción cuando se hace clic en el texto "Start as a guest"
            Toast.makeText(this, "Guest mode clicked", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, PrincipalActivity::class.java)
            startActivity(intent)
            finish() // Cierra la actividad actual
        }
    }
}