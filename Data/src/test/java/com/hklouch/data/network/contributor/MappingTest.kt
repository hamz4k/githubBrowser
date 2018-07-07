package com.hklouch.data.network.contributor

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
    fun should_map_contributor_json_to_contributor() {
        Truth.assertThat(projectData.json.contributor.toUser()).isEqualTo(projectData.domain.contributor)

    }

    @Test
    fun should_map_collection_of_contributor_json_to_contributor_list() {
        val jsonCollection = projectData.json.contributorList
        Truth.assertThat(jsonCollection.toUsers())
                .isEqualTo(projectData.domain.contributorList)

    }
}