package com.kcg.project.pazpro.model

interface ComponentsMapper {
    fun findOneById(id: Int): Components?
    fun find(): List<Components?>
    fun create(): Int
    fun update(): Int
    fun delete(): Int
}