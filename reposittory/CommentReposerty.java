package com.BlogApi.BlogApi.reposittory;

import com.BlogApi.BlogApi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentReposerty  extends JpaRepository<Comment,Long> {
    //14.30
    List<Comment>findByPostId(long id);
}
