package com.carrot.presentation.model.mapper

import com.carrot.data.model.DailyHeaderListDTO
import com.carrot.presentation.model.DailyHeader

internal fun DailyHeaderListDTO.toView(): List<DailyHeader> =
    this.data!!.map { dailyHeaderDTO ->
        DailyHeader(
            content = dailyHeaderDTO.content,
            diaryDate = dailyHeaderDTO.diaryDate,
            postDiaryId = dailyHeaderDTO.postDiaryId
        )
    }