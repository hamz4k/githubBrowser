package com.hklouch.presentation.model

import com.google.common.truth.Truth
import com.hklouch.domain.project.model.Project
import com.hklouch.presentation.Data
import org.junit.Test

class MappingTest {

    private val data = Data()

    @Test
    fun should_map_project_to_ui_preview() {
        Truth.assertThat(data.domain.project.toUiProjectPreviewItem()).isEqualTo(data.ui.uiProjectPreviewItem)
    }

    @Test
    fun should_map_project_to_ui_project() {
        Truth.assertThat(data.domain.project.toUiProjectItem()).isEqualTo(data.ui.uiProjectItem)
    }

    @Test
    fun should_map_branch_to_ui_branch() {
        Truth.assertThat(data.domain.branch.toUiBranchItem()).isEqualTo(data.ui.uiBranch)
    }

    @Test
    fun should_map_pull_to_ui_pull() {
        Truth.assertThat(data.domain.pull.toUiPullItem()).isEqualTo(data.ui.uiPull)
    }

    @Test
    fun should_map_contributor_to_ui_contributor() {
        Truth.assertThat(data.domain.contributor.toUiUserItem()).isEqualTo(data.ui.uiContributor)
    }

    @Test
    fun should_map_issue_to_ui_issue() {
        Truth.assertThat(data.domain.issue.toUiIssueItem()).isEqualTo(data.ui.uiIssue)
    }

    @Test
    fun should_map_paging_wrapper_to_ui_paging_wrapper() {
        Truth.assertThat(data.domain.projectList.toUiPagingWrapper(Project::toUiProjectPreviewItem))
                .isEqualTo(data.ui.uiProjectPreviewList)
    }
}