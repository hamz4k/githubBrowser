package com.hklouch.data.network.project.detail

import com.hklouch.data.network.GithubReposService
import com.hklouch.data.network.util.Data
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class NetworkProjectRepositoryTest {

    private lateinit var projectData: Data

    @Mock private lateinit var githubReposService: GithubReposService

    private lateinit var repository: NetworkProjectRepository

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        projectData = Data()
        repository = NetworkProjectRepository(githubReposService)
    }

    @Test
    fun should_get_project_details_return_data() {
        //Given
        val getProjectResult = projectData.json.project
        given(githubReposService.getProject(anyString(), anyString())).willReturn(Single.just(getProjectResult))
        val expected = projectData.domain.project
        //When
        repository.getProject("foo", "bar").test()
                //Then
                .assertValue(expected)
    }

    @Test
    fun should_get_project_details_complete() {
        //Given
        val getProjectResult = projectData.json.project
        given(githubReposService.getProject(anyString(), anyString())).willReturn(Single.just(getProjectResult))
        //When
        repository.getProject("foo", "bar").test()
                //Then
                .assertComplete()
    }
}