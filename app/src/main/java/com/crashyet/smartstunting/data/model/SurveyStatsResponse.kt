package com.crashyet.smartstunting.data.model

import com.google.gson.annotations.SerializedName

data class SurveyStatsResponse(
    @SerializedName("survey_hari_ini")
    val surveyHariIni: Int,

    @SerializedName("survey_bulan_ini")
    val surveyBulanIni: Int,

    @SerializedName("total_anak_aktif")
    val totalAnakAktif: Int,

    @SerializedName("belum_survey_hari_ini")
    val belumSurveyHariIni: Int
)