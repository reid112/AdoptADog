package ca.rjreid.adoptadog.data

import ca.rjreid.adoptadog.data.remote.ExampleService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class DataManagerWrapper @Inject constructor(val exampleService: ExampleService) : DataManager {
//    override fun fetchFrontPage(frontPageType: FrontPageTypes, timeFilter: TimeFilters, after: String): Observable<PostsHolder> {
//        return redditService.fetchFrontPage(frontPageType, frontPageType, timeFilter, after)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//    }
}