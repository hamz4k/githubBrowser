package com.hklouch.data.network.contributor

import com.hklouch.data.network.GithubReposService
import com.hklouch.data.network.util.Data
import com.hklouch.domain.shared.model.PagingWrapper
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response
import retrofit2.adapter.rxjava2.Result

class NetworkContributorsRepositoryTest {

    private lateinit var projectData: Data

    @Mock private lateinit var githubReposService: GithubReposService

    private lateinit var repository: NetworkContributorsRepository

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        projectData = Data()
        repository = NetworkContributorsRepository(githubReposService)
    }

    @Test
    fun should_get_contributor_return_data() {
        //Given
        val response = Response.success(projectData.json.contributorList)
        val result = Result.response(response)
        given(githubReposService.getContributors("owner", "project", page = 1, resultsPerPage = 50))
                .willReturn(Observable.just(result))
        val expected = PagingWrapper(nextPage = null,
                                     lastPage = null,
                                     items = projectData.domain.contributorList)
        //When
        repository.getContributors("owner", "project", page = 1, resultsPerPage = 50).test()
                //Then
                .assertValue(expected)
    }

    @Test
    fun should_get_contributor_complete() {
        //Given
        val response = Response.success(projectData.json.contributorList)
        val result = Result.response(response)
        given(githubReposService.getContributors("owner", "project", page = 1, resultsPerPage = 50))
                .willReturn(Observable.just(result))
        //When
        repository.getContributors("owner", "project", page = 1, resultsPerPage = 50).test()
                //Then
                .assertComplete()
    }
}