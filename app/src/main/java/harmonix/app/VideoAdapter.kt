package harmonix.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog

class VideoAdapter(val onDownloadClick: (VideoItem) -> Unit) :
    ListAdapter<VideoItem, VideoAdapter.VideoViewHolder>(VideoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(view, onDownloadClick)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class VideoViewHolder(itemView: View, val onDownloadClick: (VideoItem) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.videoTitle)
        private val thumbnailImageView: ImageView = itemView.findViewById(R.id.videoThumbnail)
        private val optionsButton: ImageButton = itemView.findViewById(R.id.optionsButton)

        fun bind(video: VideoItem) {
            // Cargar el título
            titleTextView.text = video.snippet.title

            // Cargar la miniatura usando Glide (puedes elegir otra URL: default, medium o high)
            Glide.with(itemView.context)
                .load(video.snippet.thumbnails.medium.url)
                .placeholder(R.drawable.placeholder)
                .into(thumbnailImageView)

            // Configurar el botón de opciones para mostrar el BottomSheetDialog
            optionsButton.setOnClickListener {
                val bottomSheetDialog = BottomSheetDialog(itemView.context)
                val view = LayoutInflater.from(itemView.context)
                    .inflate(R.layout.bottom_sheet_video_options, null)
                bottomSheetDialog.setContentView(view)

                view.findViewById<TextView>(R.id.option_download).setOnClickListener {
                    onDownloadClick(video)
                    bottomSheetDialog.dismiss()
                }
                view.findViewById<TextView>(R.id.option_demand).setOnClickListener {
                    Toast.makeText(itemView.context, "Demandar seleccionado", Toast.LENGTH_SHORT).show()
                    bottomSheetDialog.dismiss()
                }
                view.findViewById<TextView>(R.id.option_add_playlist).setOnClickListener {
                    Toast.makeText(itemView.context, "Añadir a playlist seleccionado", Toast.LENGTH_SHORT).show()
                    bottomSheetDialog.dismiss()
                }
                bottomSheetDialog.show()
            }
        }
    }
}

class VideoDiffCallback : DiffUtil.ItemCallback<VideoItem>() {
    override fun areItemsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean {
        return oldItem.id.videoId == newItem.id.videoId
    }

    override fun areContentsTheSame(oldItem: VideoItem, newItem: VideoItem): Boolean {
        return oldItem == newItem
    }
}
