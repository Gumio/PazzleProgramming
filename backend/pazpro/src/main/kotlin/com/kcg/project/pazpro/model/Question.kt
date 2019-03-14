package com.kcg.project.pazpro.model

data class Question(
    val id: Int,
    val title: String,
    val desc: String,
    val content: String,
    val step : Int,
    val args: String,
    val stdout: String
)