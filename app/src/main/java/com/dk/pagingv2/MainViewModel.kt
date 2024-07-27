package com.dk.pagingv2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dk.pagingv2.data.GithubResponse
import com.dk.pagingv2.network.GitApi
import com.dk.pagingv2.network.RetrofitInstance
import com.dk.pagingv2.paging.MyPagingSource
import kotlinx.coroutines.flow.Flow

class MainViewModel : ViewModel() {
    private val api = RetrofitInstance.getInstance().create(GitApi::class.java)

    val items : Flow<PagingData<GithubResponse.GithubResponseItem>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {
            MyPagingSource(api)
        }
    )
        .flow
        .cachedIn(viewModelScope)
}