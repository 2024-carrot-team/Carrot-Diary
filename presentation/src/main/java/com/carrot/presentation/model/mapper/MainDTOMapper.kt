package com.carrot.presentation.model.mapper

import com.carrot.data.model.DailyDTO
import com.carrot.data.model.MainDTO
import com.carrot.presentation.model.MainData
import com.carrot.presentation.model.MainDiary


//data 모듈과 presentation 모듈 Model 이원화
// filter 과정으로 비어있는 diaries 걸러내기
internal fun MainDTO.toView(): MainData =
    MainData(
        dataList = this.dataList.filter { it.diaries.isNotEmpty() }.map { mainDiaries ->
            MainDiary(
                memberId = mainDiaries.memberId,
                postDiaryId = mainDiaries.postDiaryId,
                diaries = mainDiaries.diaries.map { mainDiary ->
                    MainDiary.MainDaily(
                        diaryId = mainDiary.diaryId,
                        content = mainDiary.content,
                        imageInfo = mainDiary.imageInfo
                    )
                }
            )
        }
    )

internal fun MainDTO.toMember(): Long = this.dataList.first().memberId

