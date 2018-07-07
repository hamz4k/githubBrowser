package com.hklouch.data.network.contributor

import com.hklouch.domain.shared.model.User


fun UserJson.toUser() = User(id = id,
                             name = name,
                             avatarUrl = avatarUrl)

fun Collection<UserJson>.toUsers() = map { it.toUser() }

