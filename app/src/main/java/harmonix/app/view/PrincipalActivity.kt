package harmonix.app.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import harmonix.app.R

class PrincipalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.principal_activity)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        // Listener para la barra inferior
        bottomNav.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.navigation_home -> {
                    loadFragment(HomeFragment())
                    Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.navigation_library -> {
                    loadFragment(LibraryFragment())
                    Toast.makeText(this, "Library clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.navigation_search -> {
                    loadFragment(SearchFragment())
                    Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        // Cargar fragmento por defecto
        if (savedInstanceState == null) {
            bottomNav.selectedItemId = R.id.navigation_home
        }
    }

    // Metodo para cargar fragmentos en el contenedor
    private fun loadFragment(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
