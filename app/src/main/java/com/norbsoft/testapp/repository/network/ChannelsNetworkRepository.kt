package com.norbsoft.testapp.repository.network

import com.norbsoft.testapp.model.Channel
import com.norbsoft.testapp.repository.network.api.ChannelsAPI
import com.norbsoft.testapp.repository.network.exception.NetworkException
import com.norbsoft.testapp.repository.network.exception.UnknownErrorException
import io.reactivex.Single
import io.reactivex.SingleTransformer
import retrofit2.Response
import java.io.IOException

class ChannelsNetworkRepository(
    private val api: ChannelsAPI
) {

    fun getChannelsList(): Single<List<Channel>> =
        api.getChannels()
            .compose(handleNetworkExceptions())
            .compose(unpackResponseAndHandleErrors())
            .map { it.items }

    private fun <T> unpackResponseAndHandleErrors(): SingleTransformer<Response<T>, T> {
        return SingleTransformer { upstream ->
            upstream.flatMap {
                if (it.isSuccessful) {
                    Single.just(it.body())
                } else {
                    Single.error(UnknownErrorException())
                }
            }
        }
    }

    private fun <T> handleNetworkExceptions(): SingleTransformer<Response<T>, Response<T>> {
        return SingleTransformer { upstream ->
            upstream.onErrorResumeNext { throwable ->
                Single.error(when (throwable) {
                    is IOException -> NetworkException()
                    else -> throwable
                })
            }
        }
    }

}