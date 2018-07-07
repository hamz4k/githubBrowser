package com.hklouch.data.network.pull

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
    fun should_map_pull_json_to_pull() {
        Truth.assertThat(projectData.json.pull.toPull()).isEqualTo(projectData.domain.pull)

    }

    @Test
    fun should_map_collection_of_pull_json_to_pull_list() {
        val jsonCollection = projectData.json.pullList
        Truth.assertThat(jsonCollection.toPulls())
                .isEqualTo(projectData.domain.pullList)

    }
}