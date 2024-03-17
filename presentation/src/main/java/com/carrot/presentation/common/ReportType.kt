package com.carrot.presentation.common

enum class ReportType {
    VIOLENT_CONTENT,               //  (폭력적인 내용),
    OFFENSIVE_CONTENT,             //  (수치적인 내용),
    SEXUAL_CONTENT,                //  (선정적인 내용),
    OTHER,                         //  (기타)
}

class ReportTypeKey {
    companion object {
        val items = listOf(
            "R/WTR",
            "C/TWR",
            "Polisher",
            "S.C Recovery",
            "F/Oil tank",
            "GTG",
            "Elec'",
            "BLR",
            "AUX'",
            "외곽",
            "기타"
        )

    }
}