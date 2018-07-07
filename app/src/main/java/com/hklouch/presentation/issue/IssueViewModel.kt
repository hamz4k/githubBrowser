package com.hklouch.presentation.issue

import com.hklouch.domain.issue.interactor.IssuesUseCase
import com.hklouch.domain.issue.interactor.IssuesUseCase.Params
import com.hklouch.domain.issue.model.Issue
import com.hklouch.presentation.ResourceListViewModel
import com.hklouch.presentation.ResourceListViewModelFactory
import com.hklouch.presentation.model.UiIssueItem
import kotlin.reflect.KFunction1

/**
 * @see [ResourceListViewModelFactory]
 */
class IssueViewModelFactory(private val useCase: IssuesUseCase,
                            private val mapper: KFunction1<Issue, UiIssueItem>) :
        ResourceListViewModelFactory<Issue, UiIssueItem, Params>() {

    override fun supply(params: Params?): ResourceListViewModel<Issue, UiIssueItem, Params> {
        return IssueViewModel(useCase = useCase, mapper = mapper, params = params)
    }
}

class IssueViewModel(useCase: IssuesUseCase, mapper: KFunction1<Issue, UiIssueItem>, params: Params?) :
        ResourceListViewModel<Issue, UiIssueItem, Params>(useCase, mapper, params)