package com.BlogApi.BlogApi.service;

import com.BlogApi.BlogApi.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postid,CommentDto commentdto);
    public List<CommentDto> findCommentByPostId(long postId);

   void deleteCommentById(long postId, long id);

    CommentDto geteCommentById(long postId, long id);



    CommentDto updateComment(Long postId, long commentId, CommentDto commentDto);
}
