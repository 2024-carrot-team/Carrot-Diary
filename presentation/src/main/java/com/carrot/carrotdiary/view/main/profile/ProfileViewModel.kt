package com.carrot.carrotdiary.view.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carrot.carrotdiary.common.Dummy
import com.carrot.carrotdiary.model.Diary


class ProfileViewModel : ViewModel() {
    val _diaryList = MutableLiveData<List<Diary>>()
    val diaryList: LiveData<List<Diary>> get() = _diaryList

    init {
        dummyDiary()
    }

    fun dummyDiary() {
        _diaryList.value = Dummy.diaryList
    }
}