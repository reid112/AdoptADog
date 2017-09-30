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


class ListController : Controller(), SwipeRefreshLayout.OnRefreshListener {
    //region Variables/Views
    private var dm: DataManager? = null
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private var adapter = ListAdapter({ dog -> dogClick(dog) })
    //endregion

    //region Lifecycle
    override fun onCreateView(@NonNull inflater: LayoutInflater, @NonNull container: ViewGroup): View {
        val view = inflater.inflate(R.layout.controller_list, container, false)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
        recyclerView = view.findViewById(R.id.recyclerView)
        return view
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        val layoutManager = GridLayoutManager(activity, 2)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener(this)

        refresh()
    }

    override fun onDetach(view: View) {
        super.onDetach(view)
        swipeRefreshLayout.setOnRefreshListener(null)
    }
    //endregion

    //region Setters
    fun setDataManager(dm: DataManager) {
        this.dm = dm
    }
    //endregion

    //region Refresh Layout Listener Implementation
    override fun onRefresh() {
        refresh()
    }
    //endregion

    //region Helpers
    private fun refresh() {
        swipeRefreshLayout.isRefreshing = true
        dm?.fetchDogs()?.subscribe(
                { updateDogs(it) },
                { showError() })
    }

    private fun updateDogs(dogs: List<Dog>) {
        swipeRefreshLayout.isRefreshing = false
        adapter.populateDogs(dogs)
    }

    private fun showError() {
        swipeRefreshLayout.isRefreshing = false
        Toast.makeText(activity, "Oops, something has gone wrong :(", Toast.LENGTH_LONG).show()
    }

    private fun dogClick(dog: Dog) {
        activity?.let{
            startActivity(DogDetailsActivity.createIntent(it, dog))
        }
    }
    //endregion
}
