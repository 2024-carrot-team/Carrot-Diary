package com.carrot.presentation.model.mapper

import com.carrot.data.model.Diary
import com.carrot.data.model.DiaryListDTO
import com.carrot.presentation.model.DiaryHeader

internal fun DiaryListDTO.toView(): List<DiaryHeader> =
    this.data!!.map { diaryHeaderDTO ->
        DiaryHeader(
            postId = diaryHeaderDTO.postId,
            title = diaryHeaderDTO.title,
            imageUrl = diaryHeaderDTO.imageUrl
        )
    }


