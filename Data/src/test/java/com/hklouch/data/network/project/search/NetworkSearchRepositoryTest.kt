package com.hklouch.data.network.project.search

import com.hklouch.data.network.GithubReposService
import com.hklouch.data.network.util.Data
import com.hklouch.domain.shared.model.PagingWrapper
import io.reactivex.Observable
import okhttp3.Headers
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response
import retrofit2.adapter.rxjava2.Result

class NetworkSearchRepositoryTest {

    private lateinit var projectData: Data

    @Mock private lateinit var githubReposService: GithubReposService

    private lateinit var repository: NetworkSearchRepository

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        projectData = Data()
        repository = NetworkSearchRepository(githubReposService)
    }


    @Test
    fun should_search_projects_return_data() {
        //Given
        val response = Response.success(projectData.json.projectSearchResponse)
        val searchProjectsResult = Result.response(response)
        given(githubReposService.search("q", 1, 20)).willReturn(Observable.just(searchProjectsResult))
        val expected = PagingWrapper(nextPage = null,
                                     lastPage = null,
                                     items = projectData.domain.projectList)
        //When
        repository.searchProjects(query = "q",
                                  page = 1,
                                  resultsPerPage = 20).test()
                //Then
                .assertValue(expected)
    }

    @Test
    fun should_search_projects_complete() {
        //Given
        val response = Response.success(projectData.json.projectSearchResponse)
        val searchProjectsResult = Result.response(response)
        given(githubReposService.search("q", 1, 20)).willReturn(Observable.just(searchProjectsResult))
        //When
        repository.searchProjects(query = "q",
                                  page = 1,
                                  resultsPerPage = 20).test()
                //Then
                .assertComplete()
    }

    @Test
    fun should_search_projects_extract_next_pagination_param_from_headers() {
        //Given
        val responseHeaders = Headers.of(mapOf("Link" to "<https://api.github.com/search/repositories?q=tetris&page=3>; rel=\"next\""))
        val response = Response.success(projectData.json.projectSearchResponse, responseHeaders)
        val searchProjectsResult = Result.response(response)
        given(githubReposService.search("q", 1, 20)).willReturn(Observable.just(searchProjectsResult))
        val expected = PagingWrapper(nextPage = 3,
                                     lastPage = null,
                                     items = projectData.domain.projectList)
        //When
        repository.searchProjects(query = "q",
                                  page = 1,
                                  resultsPerPage = 20).test()
                //Then
                .assertValue(expected)
    }

    @Test
    fun should_search_projects_extract_last_pagination_param_from_headers() {
        //Given
        val responseHeaders = Headers.of(mapOf("Link" to "<https://api.github.com/search/repositories?q=tetris&page=38>; rel=\"last\""))
        val response = Response.success(projectData.json.projectSearchResponse, responseHeaders)
        val searchProjectsResult = Result.response(response)
        given(githubReposService.search("q", 1, 20)).willReturn(Observable.just(searchProjectsResult))
        val expected = PagingWrapper(nextPage = null,
                                     lastPage = 38,
                                     items = projectData.domain.projectList)
        //When
        repository.searchProjects(query = "q",
                                  page = 1,
                                  resultsPerPage = 20).test()
                //Then
                .assertValue(expected)
    }

    @Test
    fun should_search_projects_extract_next_and_last_pagination_param_from_headers() {
        //Given
        val responseHeaders = Headers.of(mapOf("Link" to "<https://api.github.com/search/repositories?q=tetris&page=2>; rel=\"next\",<https://api.github.com/search/repositories?q=tetris&page=38>; rel=\"last\""))
        val response = Response.success(projectData.json.projectSearchResponse, responseHeaders)
        val searchProjectsResult = Result.response(response)
        given(githubReposService.search("q", 1, 20)).willReturn(Observable.just(searchProjectsResult))
        val expected = PagingWrapper(nextPage = 2,
                                     lastPage = 38,
                                     items = projectData.domain.projectList)
        //When
        repository.searchProjects(query = "q",
                                  page = 1,
                                  resultsPerPage = 20).test()
                //Then
                .assertValue(expected)
    }

    @Test
    fun should_search_projects_emit_unknown_error() {
        //Given
        val errorBody = ResponseBody.create(null, "Broken!")
        val response = Response.error<ProjectSearchResponse>(400, errorBody)
        val searchProjectsResult = Result.response(response)
        given(githubReposService.search("q", 1, 20)).willReturn(Observable.just(searchProjectsResult))
        //When
        repository.searchProjects(query = "q",
                                  page = 1,
                                  resultsPerPage = 20).test()
                //Then
                .assertError { it.message == "Unknown problem occurred" }
    }

    @Test
    fun should_search_projects_emit_error() {
        //Given
        val expectedException = Exception("I'm broken!")
        val searchProjectsResult = Result.error<ProjectSearchResponse>(expectedException)
        given(githubReposService.search("q", 1, 2)).willReturn(Observable.just(searchProjectsResult))
        //When
        repository.searchProjects(query = "q",
                                  page = 1,
                                  resultsPerPage = 2).test()
                //Then
                .assertError { it == expectedException }
    }

}