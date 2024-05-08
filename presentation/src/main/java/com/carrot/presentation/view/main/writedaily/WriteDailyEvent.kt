package com.carrot.presentation.view.main.writedaily

sealed interface WriteDailyEvent {
    data class OnSavingDaily(
        val count: Int,
        val maxCount: Int,
    ) : WriteDailyEvent

    object FinishSaving : WriteDailyEvent
    object FailedSaving : WriteDailyEvent
}