package com.example.i2e1task.data.repository

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.example.i2e1task.data.api.UserApiService
import com.example.i2e1task.data.model.User
import com.example.i2e1task.data.model.UsersApiResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class UserDataSource(
    private val userApiService: UserApiService
) : RxPagingSource<Int, User>() {

    override fun loadSingle(
        params: LoadParams<Int>
    ): Single<LoadResult<Int, User>> {
        val nextPageNumber = params.key ?: INITIAL_PAGE
        return userApiService.getUsers(nextPageNumber)
            .subscribeOn(Schedulers.io())
            .map { toLoadResult(it, nextPageNumber) }
    }


    private fun toLoadResult(
        response: UsersApiResponse,
        currentPageNumber: Int
    ): LoadResult<Int, User> {
        return LoadResult.Page(
            response.data.users,
            null,
            if (response.data.hasMore) currentPageNumber + 1 else null
        )
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null

        val prevKey = anchorPage.prevKey
        if (prevKey != null) {
            return prevKey + 1
        }
        val nextKey = anchorPage.nextKey
        if (nextKey != null) {
            return nextKey - 1
        }
        return null
    }

    companion object {
        private const val INITIAL_PAGE = 0
    }
}