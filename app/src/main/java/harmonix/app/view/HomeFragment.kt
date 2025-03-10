package harmonix.app.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import harmonix.app.R

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Infla un layout que puede ser tu contenido de "Home"
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}
