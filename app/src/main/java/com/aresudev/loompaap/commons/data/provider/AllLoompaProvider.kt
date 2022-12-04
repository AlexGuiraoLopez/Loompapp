package com.aresudev.loompaap.commons.data.provider

import com.aresudev.loompaap.commons.data.model.LoompaModel
import com.aresudev.loompaap.core.base.BaseProvider
import com.aresudev.loompaap.core.utils.TimeUtils.Companion.FIVE_MINUTES_IN_MILLIS
import javax.inject.Inject

class AllLoompaProvider @Inject constructor(): BaseProvider<List<LoompaModel>>(FIVE_MINUTES_IN_MILLIS)