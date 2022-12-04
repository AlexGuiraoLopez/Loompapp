package com.aresudev.loompapp.commons.data.provider

import com.aresudev.loompapp.commons.data.model.LoompaModel
import com.aresudev.loompapp.commons.data.model.LoompaPageModel
import com.aresudev.loompapp.core.base.BaseProvider
import com.aresudev.loompapp.core.utils.TimeUtils.Companion.FIVE_MINUTES_IN_MILLIS
import javax.inject.Inject

class AllLoompaProvider @Inject constructor(): BaseProvider<LoompaPageModel>(FIVE_MINUTES_IN_MILLIS)