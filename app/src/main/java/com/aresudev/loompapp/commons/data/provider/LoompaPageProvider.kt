package com.aresudev.loompapp.commons.data.provider

import com.aresudev.loompapp.commons.data.model.LoompaPageModel
import com.aresudev.loompapp.core.base.BaseProvider
import com.aresudev.loompapp.core.utils.TimeUtils.Companion.ONE_MINUTE_IN_MILLIS
import javax.inject.Inject

class LoompaPageProvider @Inject constructor(): BaseProvider<LoompaPageModel>(ONE_MINUTE_IN_MILLIS)