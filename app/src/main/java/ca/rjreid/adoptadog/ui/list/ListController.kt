package ca.rjreid.adoptadog.ui.list

import android.support.annotation.NonNull
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import ca.rjreid.adoptadog.R
import ca.rjreid.adoptadog.data.DataManager
import ca.rjreid.adoptadog.data.model.Dog
import ca.rjreid.adoptadog.ui.dogdetails.DogDetailsActivity
import com.bluelinelabs.conductor.Controller


class ListController : Controller() {

    private var dm: DataManager? = null
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
        refresh()
    }

    fun setDataManager(dm: DataManager) {
        this.dm = dm
    }

    private fun refresh() {
        dm?.fetchDogs()?.subscribe(
                { updateDogs(it) },
                { showError() })
    }

    private fun updateDogs(dogs: List<Dog>) {
        adapter.populateDogs(dogs)
    }

    private fun showError() {
        Toast.makeText(activity, "Oops, something has gone wrong :(", Toast.LENGTH_LONG).show()
    }

    private fun dogClick(dog: Dog) {
        activity?.let{
            startActivity(DogDetailsActivity.createIntent(it, dog))
        }
    }

}
