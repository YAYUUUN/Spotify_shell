package harmonix.app.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import harmonix.app.R

class PasswordActivity: AppCompatActivity() {
    // Declaración de los elementos de la interfaz de usuario
    private lateinit var backButton: Button
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button
    private lateinit var minimunCharTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Establece el layout de la actividad
        setContentView(R.layout.login_password)

        // Inicialización de los elementos de la interfaz de usuario
        backButton = findViewById(R.id.login_password_page_back_button)
        passwordInput = findViewById(R.id.login_password_page_input)
        loginButton = findViewById(R.id.login_password_page_next_button)

        // Configuración del listener para el botón de retroceso
        backButton.setOnClickListener {
            // Regresa a la actividad anterior (LoginEmailActivity) o (SignUpActivity)
            finish()
        }

        // Configuración del listener para el botón "Login"
        loginButton.setOnClickListener {
            val password = passwordInput.text.toString().trim()

            if (isValidPassword(password)) {
                // Si la contraseña es válida, puedes continuar con la lógica de inicio de sesión
//                Toast.makeText(this, "Password is valid: $password", Toast.LENGTH_SHORT).show()
                // Aquí puedes redirigir a otra actividad o realizar la lógica de inicio de sesión
                val intent = Intent(this, EmailCodeVerification::class.java)
                startActivity(intent)
            } else {
                // Si la contraseña no es válida, muestra un mensaje de error
//                Toast.makeText(this, "Please enter a valid password, minimum 8 characters", Toast.LENGTH_SHORT).show()
                minimunCharTextView = findViewById(R.id.textView3)
                minimunCharTextView.text = getString(R.string.minimum_char_error)
            }
        }
    }

    // Función para validar la contraseña
    private fun isValidPassword(password: String): Boolean {
        return password.length >= 8
    }
}