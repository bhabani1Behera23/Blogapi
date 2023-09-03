package com.BlogApi.BlogApi.service;

import com.BlogApi.BlogApi.payload.postDto;

import java.util.List;

public interface postService {
     

    postDto getPostById(long id) ;

    public postDto cratepost(postDto postdto);

    List<postDto> getallposts(int pageno, int pagsize, String sortBy, String sortDIr);

    void deletepost(long id);

     postDto updateepost(long id, postDto postdto);

}
