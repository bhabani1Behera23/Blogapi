package com.BlogApi.BlogApi.service.impl;

import com.BlogApi.BlogApi.entity.Comment;
import com.BlogApi.BlogApi.entity.post;
import com.BlogApi.BlogApi.exception.ResoucrceNotFoundException;
import com.BlogApi.BlogApi.payload.CommentDto;
import com.BlogApi.BlogApi.reposittory.CommentReposerty;
import com.BlogApi.BlogApi.reposittory.postReposerty;
import com.BlogApi.BlogApi.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private postReposerty PostRepo;
    private CommentReposerty commentRepo;

    public CommentServiceImpl(postReposerty postRepo, CommentReposerty commentRepo) {
        this.PostRepo = postRepo;
        this.commentRepo = commentRepo;
    }

    @Override
    public CommentDto createComment(long postid, CommentDto commentdto) {
        post post = PostRepo.findById(postid).orElseThrow(() -> new ResoucrceNotFoundException(postid));

        Comment comment = new Comment();

        comment.setId(commentdto.getId());
        comment.setName(commentdto.getName());
        comment.setEmail(commentdto.getEmail());
        comment.setBody(commentdto.getBody());
        comment.setPost(post);

        Comment savedComment = commentRepo.save(comment);

        CommentDto dto = new CommentDto();

        dto.setId(savedComment.getId());
        dto.setName(savedComment.getName());
        dto.setEmail(savedComment.getEmail());
        dto.setBody(savedComment.getBody());

        return dto;
    }


    public List<CommentDto> findCommentByPostId(long postId) {
        post post = PostRepo.findById(postId).orElseThrow(() -> new ResoucrceNotFoundException(postId));
        List<Comment> comments = commentRepo.findByPostId(postId);
        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public void deleteCommentById(long postId, long id) {
        post post = PostRepo.findById(postId).orElseThrow(() -> new ResoucrceNotFoundException(postId));
        Comment comment = commentRepo.findById(id).orElseThrow(() -> new ResoucrceNotFoundException(id));
        commentRepo.deleteById(id);
    }

    @Override
    public CommentDto geteCommentById(long postId, long id) {
        post post = PostRepo.findById(postId).orElseThrow(() -> new ResoucrceNotFoundException(postId));
        Comment comment = commentRepo.findById(id).orElseThrow(() -> new ResoucrceNotFoundException(id));
        CommentDto dto = mapToDto(comment);
        return dto;
    }

    @Override
    public CommentDto updateComment(Long postId, long commentId, CommentDto commentRequest) {
        post post = PostRepo.findById(postId).orElseThrow(() -> new ResoucrceNotFoundException(postId));
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResoucrceNotFoundException(commentId));
        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());
        Comment updatedComment = commentRepo.save(comment);
        return mapToDto(updatedComment);
    }


    CommentDto mapToDto(Comment  co){
            CommentDto dto=new CommentDto();

            dto.setId(co.getId());
            dto.setName(co.getName());
            dto.setEmail(co.getEmail());
            dto.setBody(co.getBody());
            return dto;
        }

}
