package com.hklouch.presentation.model

import com.hklouch.domain.branch.model.Branch

data class UiBranchItem(val name: String, val sha: String, val url: String)

fun Branch.toUiBranchItem() = UiBranchItem(name = name, sha = head.sha, url = head.url)
