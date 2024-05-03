package brecori.github.com.listadecompras

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    private val items = mutableListOf<ItemModel>()

    fun addItem(newItem: ItemModel) {
        items.add(newItem)
        notifyDataSetChanged()
    }

    fun clearData() {
        items.clear()
        notifyDataSetChanged()
    }

    fun totalValue(): Double{
        var totalDaCompra: Double = 0.0;
        for (item in items){
            totalDaCompra += item.value
        }
        return totalDaCompra
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]

        holder.bind(item)

        holder.buttonDelete.setOnClickListener {
            removeItem(item)
        }
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewItem = view.findViewById<TextView>(R.id.textViewItem)
        val textViewValue = view.findViewById<TextView>(R.id.textViewValue)
        val buttonDelete = view.findViewById<ImageButton>(R.id.buttonDelete)
        fun bind(item: ItemModel) {
            textViewItem.text = item.name
            textViewValue.text = "R$" + item.value.toString()

            buttonDelete.setOnClickListener {
                item.onRemove(item)
            }
        }
    }
    fun removeItem(item: ItemModel) {
        items.remove(item)
        notifyDataSetChanged()
    }
}