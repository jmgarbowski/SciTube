package com.norbsoft.testapp

import com.norbsoft.testapp.core.BasePresenter
import com.norbsoft.testapp.repository.network.ChannelsNetworkRepository
import com.norbsoft.testapp.utils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class ChannelsListPresenter(
    private val channelsNetworkRepository: ChannelsNetworkRepository
) : BasePresenter<ChannelsListContract.View>(),
    ChannelsListContract.Presenter {

    private val disposables = CompositeDisposable()

    override fun getChannelsList() {
        disposables += channelsNetworkRepository.getChannelsList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { channels ->
                    view?.showChannels(
                        channels.sortedBy { channel ->
                            val desc = channel.snippet.description
                            if (desc.isBlank()) 0 else desc.length }
                    )
                }, {
                    view?.showError()
                }
            )
    }
}