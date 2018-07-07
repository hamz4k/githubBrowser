package com.hklouch.domain.branch.model

data class Branch(val name: String, val head: Commit) {

    data class Commit(val sha: String, val url: String)
}