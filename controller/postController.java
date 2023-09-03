package com.BlogApi.BlogApi.controller;

import com.BlogApi.BlogApi.payload.postDto;
import com.BlogApi.BlogApi.service.postService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")     //api urls
public class postController {

private postService postrepo;

    public postController(postService postrepo) {
        this.postrepo = postrepo;
    }

  //    http://localhost:8080/api/posts


    @PostMapping                         //@requestbody =  json to post dto
    public ResponseEntity<?>createpost(@Valid  @RequestBody postDto postDto, BindingResult result){ // status code =201,200,404
    if(result.hasErrors()) {
    return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
}
        postDto savedto = postrepo.cratepost(postDto);
        return new ResponseEntity<>(savedto, HttpStatus.CREATED);
    }
    //    http://localhost:8080/api/posts/1
    @GetMapping ("{id}")
    public ResponseEntity<postDto>  getPostById(@PathVariable("id")long id){ // stats code 201
        postDto dto = postrepo.getPostById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK) ;
    }
    //    http://localhost:8080/api/posts?pageno=0&pagesize=3&sortBy=content&sortDir
    @GetMapping
    public List<postDto>getallposts (
            @RequestParam(value = "pageno",defaultValue = "0",required = false)int  pageno,
            @RequestParam(value = "pagesize",defaultValue = "5",required = false)int  pagesize ,
            @RequestParam(value = "sortBy",defaultValue = "id",required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "id",required = false)String sortDIr
                    ){
       List<postDto> postdtos= postrepo.getallposts(pageno,pagesize,sortBy,sortDIr);
       return postdtos;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String>deletepost(@PathVariable long id){
        postrepo.deletepost(id);
        return new ResponseEntity<>("post is deleted !!", HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<postDto>updateepost(@PathVariable long id,@RequestBody postDto postdto){
        postDto dto = postrepo.updateepost(id, postdto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}
