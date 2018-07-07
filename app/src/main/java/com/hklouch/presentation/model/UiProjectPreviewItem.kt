package com.hklouch.presentation.model

import com.hklouch.domain.project.model.Project


data class UiProjectPreviewItem(val project: Project,
                                val id: Int,
                                val url: String,
                                val name: String,
                                val fullName: String,
                                val ownerId: Int,
                                val ownerName: String,
                                val ownerAvatarUrl: String,
                                val description: String?
)

fun Project.toUiProjectPreviewItem() = UiProjectPreviewItem(project = this,
                                                            id = id,
                                                            url = url,
                                                            name = name,
                                                            fullName = fullName,
                                                            ownerId = ownerId,
                                                            ownerName = ownerName,
                                                            ownerAvatarUrl = ownerAvatarUrl,
                                                            description = description)