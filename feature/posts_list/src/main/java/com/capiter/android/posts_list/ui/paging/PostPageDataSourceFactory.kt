package com.capiter.android.posts_list.ui.paging


import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.capiter.android.posts_list.ui.model.PostItem
import javax.inject.Inject
import javax.inject.Provider

class PostPageDataSourceFactory @Inject constructor(
    private val providerDataSource: Provider<PostPageDataSource>
) : DataSource.Factory<String, PostItem>() {

    var sourceLiveData = MutableLiveData<PostPageDataSource>()


    /**
     * Create a DataSource.
     * @return The new DataSource.
     * @see DataSource.Factory.create
     */
    override fun create(): DataSource<String, PostItem> {
       val dataSource = providerDataSource.get()
        sourceLiveData.postValue(dataSource)
        return dataSource
    }

    /**
     * Force refresh data source by invalidating and re-create again.
     */
    fun refresh() {
        sourceLiveData.value?.invalidate()
    }

    /**
     * Force retry last fetch operation on data source.
     */
    fun retry() {
        sourceLiveData.value?.retry()
    }


     fun searchForPost(query:String){
        sourceLiveData.value?.searchForPost(query)

    }

}