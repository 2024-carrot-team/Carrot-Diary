package com.carrot.presentation.model.mapper


import com.carrot.data.model.DiaryHeaderListDTO
import com.carrot.presentation.model.DiaryHeader

internal fun DiaryHeaderListDTO.toView(): List<DiaryHeader> =
    this.data!!.map { diaryHeaderDTO ->
        DiaryHeader(
            postId = diaryHeaderDTO.postId,
            title = diaryHeaderDTO.title,
            imageUrl = diaryHeaderDTO.imageUrl
        )
    }