package ca.rjreid.adoptadog.data.remote

import ca.rjreid.adoptadog.data.model.Dog
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CcRezQService {

    @GET("dog")
    fun fetchDogs(): Observable<List<Dog>>
}