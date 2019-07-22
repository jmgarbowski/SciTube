package com.norbsoft.testapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.norbsoft.testapp.model.Channel
import com.norbsoft.testapp.repository.network.ChannelsNetworkRepository
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity :
    AppCompatActivity(),
    ChannelsListContract.View {

    private val repository: ChannelsNetworkRepository by inject()

    private val presenter: ChannelsListPresenter = ChannelsListPresenter(repository)

    private val channelsAdapter = ChannelsRecyclerViewAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prepareViews()
        presenter.apply {
            attachView(this@MainActivity)
            getChannelsList()
        }
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun showChannels(channels: List<Channel>) =
        channelsAdapter.addItems(channels)

    override fun showError() =
        Toast.makeText(this, R.string.error_oops, Toast.LENGTH_SHORT).show()

    private fun prepareViews() {
        list.adapter = channelsAdapter
    }

}
