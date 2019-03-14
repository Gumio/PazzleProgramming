package com.kcg.project.pazpro.repository

import com.kcg.project.pazpro.model.Question
import com.kcg.project.pazpro.model.QuestionMapper
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository

@Mapper
@Repository
interface QuestionRepository : QuestionMapper {

    @Select(
        """
                SELECT id, title, description, content, step, args, stdout
                FROM Question
                WHERE id=#{id}
            """
    )
    override fun findOneById(id: Int): Question?

    @Select(
        """
                SELECT id, title, description, content, step, args, stdout
                FROM Question
            """
    )
    override fun find(): List<Question?>
    override fun create(): Int
    override fun update(): Int
    override fun delete(): Int
}