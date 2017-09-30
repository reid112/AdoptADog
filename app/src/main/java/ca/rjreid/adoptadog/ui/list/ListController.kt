package ca.rjreid.adoptadog.ui.list

import android.support.annotation.NonNull
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import ca.rjreid.adoptadog.R
import ca.rjreid.adoptadog.data.model.Dog
import ca.rjreid.adoptadog.ui.dogdetails.DogDetailsActivity
import com.bluelinelabs.conductor.Controller


class ListController : Controller() {

    //region hard coded data
    val dog1 = Dog("http://ccrezqs.com/wp-content/uploads/2016/01/4-3.jpg", "Fido", "Breed 1", "Male", "4 Months")
    val dog2 = Dog("http://ccrezqs.com/wp-content/uploads/2016/01/4-3.jpg", "Buddy", "Breed 2", "Female", "2 Years")
    val dog3 = Dog("http://ccrezqs.com/wp-content/uploads/2016/01/4-3.jpg", "Luna", "Breed 3", "Female", "6 Months")
    val dog4 = Dog("http://ccrezqs.com/wp-content/uploads/2016/01/4-3.jpg", "Doggy", "Breed 4", "Male", "7 Months")
    val dog5 = Dog("http://ccrezqs.com/wp-content/uploads/2016/01/4-3.jpg", "Spot", "Breed 1", "Male", "1 Year")
    val dog6 = Dog("http://ccrezqs.com/wp-content/uploads/2016/01/4-3.jpg", "Ryan", "Breed 1", "Male", "4 Months")
    val dog7 = Dog("http://ccrezqs.com/wp-content/uploads/2016/01/4-3.jpg", "Fido", "Breed 1", "Male", "4 Months")
    val dog8 = Dog("http://ccrezqs.com/wp-content/uploads/2016/01/4-3.jpg", "George", "Breed 1", "Female", "3 Months")
    val dog9 = Dog("http://ccrezqs.com/wp-content/uploads/2016/01/4-3.jpg", "Jimmy", "Breed 1", "Male", "4 Months")

    val dogs = listOf(dog1, dog2, dog3, dog4, dog5, dog6, dog7, dog8, dog9)
    //endregion

    private var recyclerView: RecyclerView? = null
    private var adapter = ListAdapter({ dog -> dogClick(dog) })

    override fun onCreateView(@NonNull inflater: LayoutInflater, @NonNull container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_list, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        return view
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        val layoutManager = GridLayoutManager(activity, 2)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = adapter
        updateDogs(dogs)
    }

    private fun updateDogs(dogs: List<Dog>) {
        adapter.populateDogs(dogs)
    }

    private fun dogClick(dog: Dog) {
        activity?.let{
            startActivity(DogDetailsActivity.createIntent(it, dog))
        }
    }

}
