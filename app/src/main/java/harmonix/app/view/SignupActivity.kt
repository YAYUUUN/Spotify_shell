package harmonix.app.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import harmonix.app.R

class SignupActivity : AppCompatActivity() {

    // Declaración de los elementos de la interfaz de usuario
    private lateinit var backButton: Button
    private lateinit var emailInput: EditText
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Establece el layout de la actividad
        setContentView(R.layout.signin_username)

        // Inicialización de los elementos de la interfaz de usuario
        backButton = findViewById(R.id.signup_page_back_button)
        emailInput = findViewById(R.id.signup_page_email_input)
        nextButton = findViewById(R.id.signup_page_next_button)

        // Configuración del listener para el botón de retroceso
        backButton.setOnClickListener {
            // Regresa a la actividad anterior (LoginActivity)
            finish()
        }

        // Configuración del listener para el botón "Next"
        nextButton.setOnClickListener {
            val email = emailInput.text.toString().trim()

            if (isValidEmail(email)) {
                // Si el correo electrónico es válido, puedes continuar con la lógica de inicio de sesión
//                Toast.makeText(this, "Email is valid: $email", Toast.LENGTH_SHORT).show()
                // Aquí puedes redirigir a otra actividad o realizar la lógica de inicio de sesión
                val intent = Intent(this, PasswordActivity::class.java)
                startActivity(intent)
            } else {
                // Si el correo electrónico no es válido, muestra un mensaje de error
                Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Función para validar el formato del correo electrónico
    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        return email.matches(emailRegex.toRegex())
    }
}