package com.hklouch.ui.contributor

import android.content.Context
import com.hklouch.presentation.model.UiUserItem
import com.hklouch.ui.ResourceBaseAdapter
import com.hklouch.ui.ResourceBaseAdapter.ItemListener
import com.hklouch.ui.ResourceListBaseFragment
import com.hklouch.ui.ResourcePagingAdapter.RefreshCallbacks
import com.hklouch.ui.contributor.ContributorAdapter.ViewHolder
import dagger.android.AndroidInjection

class ContributorFragment : ResourceListBaseFragment<UiUserItem, ViewHolder>() {

    override lateinit var adapter: ResourceBaseAdapter<UiUserItem, ViewHolder>

    override val itemListener: ItemListener<UiUserItem> = object : ItemListener<UiUserItem> {
        override fun onItemClicked(item: UiUserItem) {
            // do nothing
        }
    }

    override val refreshCallbacks: RefreshCallbacks = object : RefreshCallbacks {
        override fun onLoadNext(nextPosition: Int) {
            delegate?.onNextPageRequested(nextPosition)
        }

        override fun onRetry() {
            delegate?.onRetryRequested()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        AndroidInjection.inject(this)
        adapter = ContributorAdapter()
    }
}