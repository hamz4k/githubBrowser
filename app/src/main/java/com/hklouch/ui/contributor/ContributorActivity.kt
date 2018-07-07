package com.hklouch.ui.contributor

import android.arch.lifecycle.Observer
import android.os.Bundle
import com.hklouch.domain.contributor.interactor.ContributorsUseCase.Params
import com.hklouch.githubrepos4cs.R
import com.hklouch.presentation.contributor.ContributorViewModel
import com.hklouch.presentation.contributor.ContributorViewModelFactory
import com.hklouch.presentation.model.UiPagingWrapper
import com.hklouch.presentation.model.UiUserItem
import com.hklouch.ui.ResourceBaseActivity
import com.hklouch.ui.ResourceListBaseFragment
import com.hklouch.ui.State
import com.hklouch.utils.getViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject


class ContributorActivity : ResourceBaseActivity(), ResourceListBaseFragment.Delegate<UiUserItem> {

    /* **************** */
    /*        DI        */
    /* **************** */
    @Inject lateinit var viewModelFactory: ContributorViewModelFactory

    private lateinit var viewModel: ContributorViewModel

    /* ***************** */
    /*     Lifecycle     */
    /* ***************** */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)

        viewModel = getViewModel { viewModelFactory.supply(Params(ownerName, projectName)) } as ContributorViewModel

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.repo_list_container, ContributorFragment())
                    .commit()
        }
    }

    /* **************** */
    /*  Content events  */
    /* **************** */

    override fun onNextPageRequested(next: Int) {
        viewModel.lastQuery()?.let {
            viewModel.fetchResource(it.copy(page = next))
        }
    }

    override fun onRetryRequested() {
        viewModel.retry()
    }

    override fun onRequestDataSubscription(observer: Observer<State<UiPagingWrapper<UiUserItem>>>) {
        viewModel.getResourceResult().observe(this, observer)
    }

    override fun onLoadSuccess() {
        //nothing
    }
}