package com.hklouch.data.network.project.browse

import com.hklouch.data.network.GithubReposService
import com.hklouch.data.network.project.ProjectJson
import com.hklouch.data.network.util.Data
import com.hklouch.domain.shared.model.PagingWrapper
import io.reactivex.Observable
import okhttp3.Headers
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response
import retrofit2.adapter.rxjava2.Result

class NetworkBrowseRepositoryTest {

    private lateinit var projectData: Data

    @Mock private lateinit var githubReposService: GithubReposService

    private lateinit var repository: NetworkBrowseRepository

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        projectData = Data()
        repository = NetworkBrowseRepository(githubReposService)
    }

    @Test
    fun should_get_projects_return_data() {
        //Given
        val response = Response.success(listOf(projectData.json.project))
        val getProjectsResult = Result.response(response)
        given(githubReposService.getProjects(anyInt())).willReturn(Observable.just(getProjectsResult))
        val expected = PagingWrapper(nextPage = null,
                                     lastPage = null,
                                     items = listOf(projectData.domain.project))
        //When
        repository.getProjects(1).test()
                //Then
                .assertValue(expected)
    }

    @Test
    fun should_get_projects_complete() {
        //Given
        val response = Response.success(listOf(projectData.json.project))
        val getProjectsResult = Result.response(response)
        given(githubReposService.getProjects(anyInt())).willReturn(Observable.just(getProjectsResult))
        //When
        repository.getProjects(1).test()
                //Then
                .assertComplete()
    }

    @Test
    fun should_get_projects_extract_next_pagination_param_from_headers() {
        //Given
        val responseHeaders = Headers.of(mapOf("Link" to "<https://api.github.com/repositories?since=364>; rel=\"next\""))
        val response = Response.success(listOf(projectData.json.project), responseHeaders)
        val getProjectsResult = Result.response(response)
        given(githubReposService.getProjects(anyInt())).willReturn(Observable.just(getProjectsResult))
        val expected = PagingWrapper(nextPage = 364,
                                     lastPage = null,
                                     items = listOf(projectData.domain.project))
        //When
        repository.getProjects(1).test()
                //Then
                .assertValue(expected)
    }

    @Test
    fun should_get_projects_extract_last_pagination_param_from_headers() {
        //Given
        val responseHeaders = Headers.of(mapOf("Link" to "<https://api.github.com/repositories?since=833>; rel=\"last\""))
        val response = Response.success(listOf(projectData.json.project), responseHeaders)
        val getProjectsResult = Result.response(response)
        given(githubReposService.getProjects(anyInt())).willReturn(Observable.just(getProjectsResult))
        val expected = PagingWrapper(nextPage = null,
                                     lastPage = 833,
                                     items = listOf(projectData.domain.project))
        //When
        repository.getProjects(1).test()
                //Then
                .assertValue(expected)
    }

    @Test
    fun should_get_projects_extract_next_and_last_pagination_param_from_headers() {
        //Given
        val responseHeaders = Headers.of(mapOf("Link" to "<https://api.github.com/repositories?since=362>; rel=\"next\",<https://api.github.com/repositories?since=2010784>; rel=\"last\""))
        val response = Response.success(listOf(projectData.json.project), responseHeaders)
        val getProjectsResult = Result.response(response)
        given(githubReposService.getProjects(anyInt())).willReturn(Observable.just(getProjectsResult))
        val expected = PagingWrapper(nextPage = 362,
                                     lastPage = 2010784,
                                     items = listOf(projectData.domain.project))
        //When
        repository.getProjects(1).test()
                //Then
                .assertValue(expected)
    }

    @Test
    fun should_get_projects_emit_unknown_error() {
        //Given
        val errorBody = ResponseBody.create(null, "Broken!")
        val response = Response.error<List<ProjectJson>>(400, errorBody)
        val getProjectsResult = Result.response(response)
        given(githubReposService.getProjects(anyInt())).willReturn(Observable.just(getProjectsResult))
        //When
        repository.getProjects(1).test()
                //Then
                .assertError { it.message == "Unknown problem occurred" }
    }

    @Test
    fun should_get_projects_emit_error() {
        //Given
        val expectedException = Exception("I'm broken!")
        val getProjectsResult = Result.error<List<ProjectJson>>(expectedException)
        given(githubReposService.getProjects(anyInt())).willReturn(Observable.just(getProjectsResult))
        //When
        repository.getProjects(1).test()
                //Then
                .assertError { it == expectedException }
    }
}