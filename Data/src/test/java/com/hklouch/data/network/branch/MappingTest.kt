package com.hklouch.data.network.branch

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
    fun should_map_branch_json_to_branch() {
        Truth.assertThat(projectData.json.branch.toBranch()).isEqualTo(projectData.domain.branch)

    }

    @Test
    fun should_map_collection_of_branch_json_to_branch_list() {
        val jsonCollection = projectData.json.branchList
        Truth.assertThat(jsonCollection.toBranches())
                .isEqualTo(projectData.domain.branchList)

    }
}