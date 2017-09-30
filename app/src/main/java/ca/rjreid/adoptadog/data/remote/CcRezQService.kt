package ca.rjreid.adoptadog.data.remote

import ca.rjreid.adoptadog.data.model.Dog
import ca.rjreid.adoptadog.data.model.Person
import io.reactivex.Observable
import retrofit2.http.*

interface CcRezQService {

    @GET("dog")
    fun fetchDogs(): Observable<List<Dog>>

    @POST("fp")
    fun register(@Body person: Person): Observable<String>
}