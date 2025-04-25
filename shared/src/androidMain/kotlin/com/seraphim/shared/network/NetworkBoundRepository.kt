package com.seraphim.shared.network

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.seraphim.pokemon.invoker.infrastructure.HttpResponse
import com.seraphim.shared.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundRepository<RESULT, REQUEST : Any> {

    fun asFlow() = flow<Resource<RESULT>> {

        // Emit Database content first
        emit(Resource.Success(fetchFromLocal().first()))

        val apiResponse = fetchFromRemote()

        // Parse body
        val remoteData = apiResponse.body()

        // Check for response validation
        if (apiResponse.success) {
            saveRemoteData(remoteData)
        } else {
            // Something went wrong! Emit Error state.
            emit(Resource.Failed(apiResponse.toString()))
        }

        emitAll(
            fetchFromLocal().map {
                Resource.Success<RESULT>(it)
            }
        )
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