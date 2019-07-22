package com.norbsoft.testapp

import com.norbsoft.testapp.core.BaseView
import com.norbsoft.testapp.core.PresenterLifecycle
import com.norbsoft.testapp.model.Channel

interface ChannelsListContract {

    interface View : BaseView {

        fun showChannels(channels: List<Channel>)

        fun showError()

    }

    interface Presenter : PresenterLifecycle<View> {

        fun getChannelsList()

    }

}