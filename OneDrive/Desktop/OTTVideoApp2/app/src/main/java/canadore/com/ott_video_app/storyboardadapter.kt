package canadore.com.ott_video_app
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView

class StoryboardAdapter(
    private val storyboardList: List<StoryboardItem>,
    private val context: Context
) : RecyclerView.Adapter<StoryboardAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title_text)

        init {
            itemView.setOnClickListener {
                // Handle item click
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_storyboard, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = storyboardList[position].title
    }

    override fun getItemCount(): Int {
        return storyboardList.size
    }
}