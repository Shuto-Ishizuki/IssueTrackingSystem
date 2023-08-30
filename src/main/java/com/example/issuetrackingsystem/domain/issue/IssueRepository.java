package com.example.issuetrackingsystem.domain.issue;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IssueRepository {

    @Select("select * from issues")
    List<IssueEntity> findAll();

    @Insert("insert into issues (summary, description) values (#{summary}, #{description})")
    void insert(@Param("summary") String summary, @Param("description") String description);

    @Select("select * from issues where id = #{issueId}")
    IssueEntity findById(long issueId);

    @Delete("delete from issues where id = #{issueId}")
    void delete(long issueId);
}
