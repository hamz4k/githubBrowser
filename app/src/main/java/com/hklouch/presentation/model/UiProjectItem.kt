package com.hklouch.presentation.model

import com.hklouch.domain.project.model.Project


data class UiProjectItem(val project: Project,
                         val id: Int,
                         val url: String,
                         val name: String,
                         val fullName: String,
                         val ownerId: Int,
                         val ownerName: String,
                         val ownerAvatarUrl: String,
                         val description: String?,
                         val collaboratorsUrl: String?,
                         val isFork: Boolean,
                         val forksCount: Int?,
                         val issuesCount: Int?,
                         val starsCount: Int?,
                         val watchersCount: Int?,
                         val issuesUrl: String?,
                         val contributorsUrl: String?,
                         val branchesUrl: String?,
                         val pullsUrl: String?
)


fun Project.toUiProjectItem() = UiProjectItem(project = this,
                                              id = id,
                                              url = url,
                                              name = name,
                                              fullName = fullName,
                                              ownerId = ownerId,
                                              ownerName = ownerName,
                                              ownerAvatarUrl = ownerAvatarUrl,
                                              description = description,
                                              collaboratorsUrl = collaboratorsUrl,
                                              isFork = isFork,
                                              starsCount = starsCount,
                                              forksCount = forksCount,
                                              issuesCount = issuesCount,
                                              watchersCount = watchersCount,
                                              issuesUrl = issuesUrl,
                                              contributorsUrl = contributorsUrl,
                                              branchesUrl = branchesUrl,
                                              pullsUrl = pullsUrl)