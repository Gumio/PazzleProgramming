package com.kcg.project.pazpro.repository

import com.kcg.project.pazpro.model.Components
import com.kcg.project.pazpro.model.ComponentsMapper
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface ComponentsRepository: ComponentsMapper {
    @Select(
        """
                SELECT id, type, name
                FROM Components
                WHERE type=#{type}
            """
    )
    override fun findOneByType(type: Int): Components?

    @Select(
        """
                SELECT id, type, name
                FROM Components
            """
    )
    override fun find(): List<Components?>
    override fun create(): Int
    override fun update(): Int
    override fun delete(): Int
}