package harmonix.app.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import harmonix.app.R

class SearchOptionsBottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout del Bottom Sheet
        val view = inflater.inflate(R.layout.bottom_sheet_options, container, false)

        // Obtener referencias a los TextView
        val optionDownload = view.findViewById<TextView>(R.id.option_download)
        val optionLike = view.findViewById<TextView>(R.id.option_like)
        val optionShare = view.findViewById<TextView>(R.id.option_share)

        // Asignar listeners a los TextView
        optionDownload.setOnClickListener {
            onDownloadClicked()
        }

        optionLike.setOnClickListener {
            onLikeClicked()
        }

        optionShare.setOnClickListener {
            onShareClicked()
        }

        return view
    }

    // Metodo para la acción de "Descargar"
    private fun onDownloadClicked() {
        showToast("Descargar")
    }

    // Metodo para la acción de "Me gusta"
    private fun onLikeClicked() {
        showToast("Me gusta")
    }
    private fun onShareClicked() {
        showToast("Compartir")
    }

    // Función auxiliar para mostrar un Toast
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}