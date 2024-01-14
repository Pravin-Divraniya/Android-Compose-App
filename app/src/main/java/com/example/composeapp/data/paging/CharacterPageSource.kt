package com.example.composeapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.composeapp.data.model.Characters
import com.example.composeapp.data.repo.remote.RemoteRepository

class CharacterPageSource(
    private val remoteRepository: RemoteRepository
): PagingSource<Int, Characters.Result>() {

    override fun getRefreshKey(state: PagingState<Int, Characters.Result>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Characters.Result> {
        return try {
            val pageNumber = params.key ?: 1
            val response = remoteRepository.getCharacterData(page = pageNumber)

            val nextKey:Int? = response.body()?.info?.count?.let{
                when{
                    (params.loadSize * (pageNumber.plus(1))) < it -> pageNumber.plus(1)
                    else -> null
                }
            }

            LoadResult.Page(
                data = response.body()?.results ?: emptyList(),
                prevKey = null,
                nextKey = nextKey
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}