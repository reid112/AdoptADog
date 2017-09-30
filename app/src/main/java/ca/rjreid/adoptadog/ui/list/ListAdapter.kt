package ca.rjreid.adoptadog.ui.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ca.rjreid.adoptadog.R
import ca.rjreid.adoptadog.data.model.Dog
import ca.rjreid.adoptadog.util.image
import kotlinx.android.synthetic.main.component_dog_list_item.view.*

class ListAdapter(private val dogClick: (Dog) -> Unit) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    //region Variables
    private var dogs = mutableListOf<Dog>()
    //endregion

    //region Adapter Implementation
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.component_dog_list_item, parent, false)
        return ViewHolder(view, dogClick)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindDog(dogs[position])
    }

    override fun getItemCount(): Int {
        return dogs.count()
    }
    //endregion

    //region Commands
    fun populateDogs(dogs: List<Dog>) {
        this.dogs.clear()
        this.dogs.addAll(dogs)
        notifyDataSetChanged()
    }

    fun clearDogs() {
        this.dogs.clear()
        notifyDataSetChanged()
    }
    //endregion

    //region View Holder Inner Class
    class ViewHolder(view: View, private val dogClick: (Dog) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bindDog(dog: Dog) {
            with(dog) {
                itemView.dogImage.image(dog.imageUrl)
                itemView.dogName.text = dog.name
                itemView.dogAge.text = dog.age
                itemView.dogBreed.text = dog.breed

                itemView.setOnClickListener { dogClick(this) }
            }
        }
    }
    //endregion
}
