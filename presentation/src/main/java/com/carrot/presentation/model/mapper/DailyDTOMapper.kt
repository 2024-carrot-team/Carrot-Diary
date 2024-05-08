package com.carrot.presentation.model.mapper

import com.carrot.data.model.DailyDTO
import com.carrot.data.model.DailyHeaderListDTO
import com.carrot.presentation.model.Accident
import com.carrot.presentation.model.DailyHeader

internal fun DailyDTO.toView(): List<Accident> =
    this.data!!.map { accidentDTO ->
        Accident(
            id = accidentDTO.diaryId,
            content = accidentDTO.content,
            imageList = accidentDTO.imageInfo.map { imageInfo ->
                imageInfo.imageUrl
            }
        )
    }