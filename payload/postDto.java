package com.BlogApi.BlogApi.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class postDto {
    private long id;
    @NotEmpty
    @Size(min=2,message = "title should be atleast 2 charecter")
    private String title;
    @NotEmpty
    @Size(min = 4,message = "description atleast  more than 4 charecter")
    private String description;
    @NotEmpty
    private String content;
}
