package com.hklouch.ui.issue

import android.arch.lifecycle.Observer
import android.os.Bundle
import com.hklouch.domain.issue.interactor.IssuesUseCase.Params
import com.hklouch.githubrepos4cs.R
import com.hklouch.presentation.issue.IssueViewModel
import com.hklouch.presentation.issue.IssueViewModelFactory
import com.hklouch.presentation.model.UiIssueItem
import com.hklouch.presentation.model.UiPagingWrapper
import com.hklouch.ui.ResourceBaseActivity
import com.hklouch.ui.ResourceListBaseFragment
import com.hklouch.ui.State
import com.hklouch.utils.getViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject


class IssueActivity : ResourceBaseActivity(), ResourceListBaseFragment.Delegate<UiIssueItem> {

    /* **************** */
    /*        DI        */
    /* **************** */
    @Inject lateinit var viewModelFactory: IssueViewModelFactory

    private lateinit var viewModel: IssueViewModel

    /* ***************** */
    /*     Lifecycle     */
    /* ***************** */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)

        viewModel = getViewModel { viewModelFactory.supply(Params(ownerName, projectName)) } as IssueViewModel

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.repo_list_container, IssueFragment())
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

    override fun onRequestDataSubscription(observer: Observer<State<UiPagingWrapper<UiIssueItem>>>) {
        viewModel.getResourceResult().observe(this, observer)
    }

    override fun onLoadSuccess() {
        //nothing
    }
}