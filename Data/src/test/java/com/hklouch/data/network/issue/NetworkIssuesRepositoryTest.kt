package com.hklouch.data.network.issue

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

class NetworkIssuesRepositoryTest {

    private lateinit var projectData: Data

    @Mock private lateinit var githubReposService: GithubReposService

    private lateinit var repository: NetworkIssuesRepository

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        projectData = Data()
        repository = NetworkIssuesRepository(githubReposService)
    }

    @Test
    fun should_get_issues_return_data() {
        //Given
        val response = Response.success(projectData.json.issueList)
        val searchProjectsResult = Result.response(response)
        given(githubReposService.getIssues("owner", "project", page = 1, resultsPerPage = 50))
                .willReturn(Observable.just(searchProjectsResult))
        val expected = PagingWrapper(nextPage = null,
                                     lastPage = null,
                                     items = projectData.domain.issueList)
        //When
        repository.getIssues("owner", "project", page = 1, resultsPerPage = 50).test()
                //Then
                .assertValue(expected)
    }

    @Test
    fun should_get_issues_complete() {
        //Given
        val response = Response.success(projectData.json.issueList)
        val result = Result.response(response)
        given(githubReposService.getIssues("owner", "project", page = 1, resultsPerPage = 50))
                .willReturn(Observable.just(result))
        //When
        repository.getIssues("owner", "project", page = 1, resultsPerPage = 50).test()
                //Then
                .assertComplete()
    }
}