package com.kcg.project.pazpro.model

interface QuestionMapper {
    fun findOneById(id: Int): Question?
    fun find(): List<Question?>
    fun create(): Int
    fun update(): Int
    fun delete(): Int
}