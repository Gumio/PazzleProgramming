package com.kcg.project.pazpro.controller

import com.kcg.project.pazpro.model.Components
import com.kcg.project.pazpro.model.ComponentsType
import com.kcg.project.pazpro.service.ComponentsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/components")
class ComponentsController(
    private val componentsService: ComponentsService
) {

    @GetMapping("")
    fun find(): List<ComponentsResponse> =
        componentsService.findAll()

    @GetMapping("/{type}")
    fun select(@PathVariable type: Int = 1): ComponentsResponse =
        componentsService.find(type)?.toResponse() ?: dummy(1)

    data class ComponentsResponse(
        val type: Int,
        val name: String
    )

    fun Components.toResponse(): ComponentsResponse = ComponentsResponse(this.type, this.name)

    private fun dummy(id: Int): ComponentsResponse
            = ComponentsResponse(ComponentsType.ASSIGNMENT.id, "<input> = <components>")
}