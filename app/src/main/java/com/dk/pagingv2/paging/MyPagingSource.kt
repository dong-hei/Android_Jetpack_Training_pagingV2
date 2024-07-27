package com.dk.pagingv2.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dk.pagingv2.data.GithubResponse
import com.dk.pagingv2.network.GitApi
import kotlinx.coroutines.delay

private const val STARTING_KEY = 1

class MyPagingSource (

    private val githubService : GitApi
) : PagingSource<Int, GithubResponse.GithubResponseItem>(){

    init {
        Log.d("MyPagingSource", "init")
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubResponse.GithubResponseItem> {


        Log.d("MyPagingSource", "==================================")
        Log.d("MyPagingSource", "==============START===============")
        Log.d("MyPagingSource", "load")
        Log.d("MyPagingSource", "params.key :" + params.key)


        val page = params.key ?: STARTING_KEY

        Log.d("MyPagingSource", "page : $page")


        val res = githubService.getData(page, params.loadSize)

        Log.d("MyPagingSource", "page : $res")
        Log.d("MyPagingSource", res.body().toString())

        val data = res.body()

        Log.d("data", data.toString())

        if(page != 1){
            delay(1500)
        }

        Log.d("params.loadSize", params.loadSize.toString())
        Log.d("params.loadSize", (params.loadSize / 30).toString())


        if(data != null){
            return LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = page + (params.loadSize / 30)
                // 첫번째 페이지(1,2,3)가 90개(90/30 = 3개)
                // 두번째 페이지(4)-> (90/30 +1 = 4)
                // 세번째 페이지(5)-> (90/30 +1 +1 = 5)
            )
        } else{
            return LoadResult.Page(
                data = listOf(),
                prevKey = null,
                nextKey = null
            )
        }

    }

    override fun getRefreshKey(state: PagingState<Int, GithubResponse.GithubResponseItem>): Int? {
        return null
    }

}