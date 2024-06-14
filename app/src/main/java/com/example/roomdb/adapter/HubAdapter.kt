import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb.R
import com.example.roomdb.models.Hub

class HubAdapter(private var hubList: List<Hub>) : RecyclerView.Adapter<HubAdapter.HubViewHolder>() {

    inner class HubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvHubName: TextView = itemView.findViewById(R.id.tvHubName)
        val tvRegion: TextView = itemView.findViewById(R.id.tvRegion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HubViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hub, parent, false)
        return HubViewHolder(view)
    }

    override fun onBindViewHolder(holder: HubViewHolder, position: Int) {
        val hub = hubList[position]
        holder.tvHubName.text = hub.hubName
        holder.tvRegion.text = hub.region
    }

    fun updateList(newList: List<Hub>) {
        hubList = newList
        notifyDataSetChanged()
    }

    override fun getItemCount() = hubList.size
}