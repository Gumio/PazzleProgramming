package com.kcg.project.pazpro.controller

import com.kcg.project.pazpro.model.Components
import com.kcg.project.pazpro.model.ComponentsType
import com.kcg.project.pazpro.service.ComponentsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

class ComponentsController(
    private val componentsService: ComponentsService
) {
    @GetMapping("/components/{id}")
    fun select(@PathVariable id: Int = 1): ComponentsResponse =
        componentsService.find(id)?.toResponse() ?: dummy(1)

    data class ComponentsResponse(
        val type: ComponentsType,
        val name: String
    )

    fun Components.toResponse(): ComponentsResponse = ComponentsResponse(this.type, this.name)

    private fun dummy(id: Int): ComponentsResponse
            = ComponentsResponse(ComponentsType.ASSIGNMENT, "<input> = <components>")
}