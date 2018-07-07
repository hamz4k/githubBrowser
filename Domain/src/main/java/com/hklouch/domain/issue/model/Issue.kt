package com.hklouch.domain.issue.model

data class Issue(val url: String,
                 val ownerId: Int,
                 val ownerName: String,
                 val ownerAvatarUrl: String,
                 val title: String,
                 val number: Int,
                 val state: String
)
