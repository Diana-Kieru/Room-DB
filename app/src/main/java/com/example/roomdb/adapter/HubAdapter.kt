import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdb.R
import com.example.roomdb.models.Hub
import com.google.android.material.button.MaterialButton

class HubAdapter(
    private var hubList: List<Hub>,
    private val onDelete: (Hub) -> Unit,
    private val onEditClickListener: OnEditClickListener
) : RecyclerView.Adapter<HubAdapter.HubViewHolder>() {

    interface OnEditClickListener {
        fun onEditClick(hub: Hub)
    }

    inner class HubViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvHubName: TextView = itemView.findViewById(R.id.tvHubName)
        val tvRegion: TextView = itemView.findViewById(R.id.tvRegion)
        val btnDeleteHub: MaterialButton = itemView.findViewById(R.id.btnDeleteHub)
        val btnEditHub: MaterialButton = itemView.findViewById(R.id.btnEditHub)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HubViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hub, parent, false)
        return HubViewHolder(view)
    }

    override fun onBindViewHolder(holder: HubViewHolder, position: Int) {
        val hub = hubList[position]
        holder.tvHubName.text = hub.hubName
        holder.tvRegion.text = hub.region

        holder.btnDeleteHub.setOnClickListener {
            onDelete(hub)
        }

        holder.btnEditHub.setOnClickListener {
            onEditClickListener.onEditClick(hub)
        }
    }

    fun updateList(newList: List<Hub>) {
        hubList = newList
        notifyDataSetChanged()
    }

    override fun getItemCount() = hubList.size
}