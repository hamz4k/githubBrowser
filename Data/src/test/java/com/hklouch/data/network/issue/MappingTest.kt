package com.hklouch.data.network.issue

import com.google.common.truth.Truth
import com.hklouch.data.network.util.Data
import org.junit.Before
import org.junit.Test

class MappingTest {

    private lateinit var projectData: Data

    @Before
    fun before() {
        projectData = Data()
    }

    @Test
    fun should_map_issue_json_to_issue() {
        Truth.assertThat(projectData.json.issue.toIssue()).isEqualTo(projectData.domain.issue)

    }

    @Test
    fun should_map_collection_of_issue_json_to_issue_list() {
        val jsonCollection = projectData.json.issueList
        Truth.assertThat(jsonCollection.toIssues())
                .isEqualTo(projectData.domain.issueList)

    }
}