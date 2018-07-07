package com.hklouch.domain.pull.model

import javax.inject.Inject

data class Pull @Inject constructor(val url: String,
                                    val ownerId: Int,
                                    val ownerName: String,
                                    val ownerAvatarUrl: String,
                                    val title: String,
                                    val number: Int,
                                    val state: String
)
