package com.plcoding.bookpedia.core.presentation

import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.error_disk_full
import cmp_bookpedia.composeapp.generated.resources.error_no_internet
import cmp_bookpedia.composeapp.generated.resources.error_request_timeout
import cmp_bookpedia.composeapp.generated.resources.error_serialization
import cmp_bookpedia.composeapp.generated.resources.error_too_many_requests
import cmp_bookpedia.composeapp.generated.resources.error_unknown
import com.plcoding.bookpedia.core.domain.IDataError


fun IDataError.toUiText(): IUiText {
    val stringRes = when (this) {
        IDataError.Local.DISK_FULL -> Res.string.error_disk_full
        IDataError.Local.UNKNOWN -> Res.string.error_unknown
        IDataError.Remote.REQUEST_TIMEOUT -> Res.string.error_request_timeout
        IDataError.Remote.TOO_MANY_REQUESTS -> Res.string.error_too_many_requests
        IDataError.Remote.NO_INTERNET -> Res.string.error_no_internet
        IDataError.Remote.SERVER -> Res.string.error_unknown
        IDataError.Remote.SERIALIZATION -> Res.string.error_serialization
        IDataError.Remote.UNKNOWN -> Res.string.error_unknown
    }

    return IUiText.StringResourceId(stringRes)
}