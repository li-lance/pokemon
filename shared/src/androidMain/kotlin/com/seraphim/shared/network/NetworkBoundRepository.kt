package com.seraphim.shared.network

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.seraphim.pokemon.invoker.infrastructure.HttpResponse
import com.seraphim.shared.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundRepository<RESULT, REQUEST : Any> {

    fun asFlow(forceRemote: Boolean = false) = flow<Resource<RESULT>> {
        if (forceRemote) {
            // 先请求接口，保存到本地，再 emit 本地数据
            val apiResponse = fetchFromRemote()
            val remoteData = apiResponse.body()
            if (apiResponse.success) {
                saveRemoteData(remoteData)
            } else {
                emit(Resource.Failed(apiResponse.toString()))
            }
            emitAll(
                fetchFromLocal().map {
                    Resource.Success<RESULT>(it)
                }
            )
        } else {
            // 原有逻辑：先 emit 本地，再请求接口，再 emit 本地
            val localData = fetchFromLocal().firstOrNull()
            if (localData is Collection<*> && localData.isNotEmpty()) {
                emit(Resource.Success(localData))
            }
            if (localData != null && localData !is Collection<*>) {
                emit(Resource.Success(localData))
            }
            val apiResponse = fetchFromRemote()
            val remoteData = apiResponse.body()
            if (apiResponse.success) {
                saveRemoteData(remoteData)
            } else {
                emit(Resource.Failed(apiResponse.toString()))
            }
            emitAll(
                fetchFromLocal().map {
                    Resource.Success<RESULT>(it)
                }
            )
        }
    }.catch { e ->
        e.printStackTrace()
        emit(Resource.Failed("Network error!"))
    }

    /**
     * Saves retrieved from remote into the persistence storage.
     */
    @WorkerThread
    protected abstract suspend fun saveRemoteData(response: REQUEST)

    /**
     * Retrieves all data from persistence storage.
     */
    @MainThread
    protected abstract fun fetchFromLocal(): Flow<RESULT>

    /**
     * Fetches [HttpResponse] from the remote end point.
     */
    @MainThread
    protected abstract suspend fun fetchFromRemote(): HttpResponse<REQUEST>
}