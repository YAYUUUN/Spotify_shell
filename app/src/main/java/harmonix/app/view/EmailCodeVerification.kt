package harmonix.app.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import harmonix.app.R

class EmailCodeVerification: AppCompatActivity() {
    // Declaración de los elementos de la interfaz de usuario
    private lateinit var backButton: Button
    private lateinit var emailCodeInput: EditText
    private lateinit var verifyButton: Button
    private lateinit var resendCodeButton: Button
    private lateinit var codeErrorTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Establece el layout de la actividad
        setContentView(R.layout.email_code_verification)

        // Inicialización de los elementos de la interfaz de usuario
        backButton = findViewById(R.id.email_code_verification_page_back_button)
        emailCodeInput = findViewById(R.id.email_code_verification_page_input)
        verifyButton = findViewById(R.id.email_code_verification_page_finish_button)
        resendCodeButton = findViewById(R.id.email_code_verification_page_resend_button)

        // Configuración del listener para el botón de retroceso
        backButton.setOnClickListener {
            // Regresa a la actividad anterior (PasswordActivity)
            finish()
        }

        // Configuración del listener para el botón "Verify"
        verifyButton.setOnClickListener {
            val emailCode = emailCodeInput.text.toString().trim()

            if (isValidCode(emailCode)) {
                // Si el código es válido, puedes continuar con la lógica de verificación
                val intent = Intent(this, PrincipalActivity::class.java)
                startActivity(intent)
            }
        }
    }

    // Función para validar el código de verificación
    private fun isValidCode(code: String): Boolean {
//        TODO - Implement code validation logic
        return code.length == 6
    }
}