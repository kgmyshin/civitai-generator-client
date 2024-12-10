package com.kgmyshin.civitai.api.json.base

enum class JobEventType {
    Initialized,
    Claimed,
    Rejected,
    LateRejected,
    ClaimExpired,
    Updated,
    Failed,
    Succeeded,
    Expired,
    Deleted
}
