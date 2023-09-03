package com.BlogApi.BlogApi.service.impl;

import com.BlogApi.BlogApi.entity.post;
import com.BlogApi.BlogApi.exception.ResoucrceNotFoundException;
import com.BlogApi.BlogApi.payload.postDto;
import com.BlogApi.BlogApi.reposittory.postReposerty;
import com.BlogApi.BlogApi.service.postService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class postServiceImpl implements postService {

    private postReposerty postRepo; // there 2 way dependencey add  1=@auto wired / 2= constructor


    public postServiceImpl(postReposerty postRepo,ModelMapper modelmapper) {
        this.modelmapper=modelmapper;
        this.postRepo = postRepo;
    }
    private ModelMapper modelmapper; // it s not build in thats why you make constructor to intilize ; there not working @autowired ; externally library


    @Override
    public postDto getPostById(long id) {
        post post = postRepo.findById(id).orElseThrow       (
                ()->new ResoucrceNotFoundException(id)      );
        postDto dto = maptodto(post);
        return dto;
    }

    @Override
    public postDto cratepost(postDto postdto) {

        post post = maptoentity(postdto);
        post savePost = postRepo.save(post);
        postDto dto = maptodto(savePost);

        return dto;
    }

    @Override
    public List<postDto> getallposts(int pageno, int pagsize, String sortBy, String sortDIr) {
      Sort sort=  sortDIr.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageno, pagsize, Sort.by(sortBy));
        Page<post> posts = postRepo.findAll(pageable);
        posts.getContent();
        List<postDto> collect = posts.stream().map(post -> maptodto(post)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public void deletepost(long id) {
        post post = postRepo.findById(id).orElseThrow(() -> new ResoucrceNotFoundException(id));
        postRepo.deleteById(id);

    }

    @Override
    public postDto updateepost(long id, postDto postdto) {
        post post = postRepo.findById(id).orElseThrow(() -> new ResoucrceNotFoundException(id));
 post updatecontent=maptoentity(postdto);
        updatecontent.setId(post.getId());
        post updatepostinfo = postRepo.save(updatecontent);
        return maptodto(updatepostinfo);
    }

    postDto maptodto(post post){
       // postDto dto=new postDto();
        postDto dto = modelmapper.map(post, postDto.class);
//        dto.setId(post.getId());
//        dto.setContent(post.getContent());
//        dto.setTitle(post.getTitle());
//        dto.setDescription(post.getDescription());
        return dto;
    }
    post maptoentity(postDto postdto){
        post post = modelmapper.map(postdto, post.class);
//        post post =new post();
//        post.setTitle(postdto.getTitle());
//        post.setDescription(postdto.getDescription());
//        post.setContent(postdto.getContent());
        return post;
    }

}
