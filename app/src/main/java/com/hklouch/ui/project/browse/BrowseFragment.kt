package com.hklouch.ui.project.browse

import android.content.Context
import com.hklouch.presentation.model.UiProjectPreviewItem
import com.hklouch.ui.ResourceBaseAdapter
import com.hklouch.ui.ResourceBaseAdapter.ItemListener
import com.hklouch.ui.ResourceListBaseFragment
import com.hklouch.ui.ResourcePagingAdapter.RefreshCallbacks
import com.hklouch.ui.project.browse.BrowseProjectsAdapter.ViewHolder
import com.hklouch.ui.project.detail.ProjectDetailActivity
import dagger.android.AndroidInjection

class BrowseFragment : ResourceListBaseFragment<UiProjectPreviewItem, ViewHolder>() {

    override lateinit var adapter: ResourceBaseAdapter<UiProjectPreviewItem, ViewHolder>

    override val itemListener: ItemListener<UiProjectPreviewItem> = object : ItemListener<UiProjectPreviewItem> {
        override fun onItemClicked(item: UiProjectPreviewItem) {
            startActivity(ProjectDetailActivity.createIntent(activity, ownerName = item.ownerName, projectName = item.name))
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
        adapter = BrowseProjectsAdapter()
    }
}