package com.BlogApi.BlogApi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts",uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title" ,nullable = false)
    private String title;
    @Column(name = "Content" ,nullable = false)
    private String Content;
    @Column(name = "description" ,nullable = false)
    private  String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Comment> comments=new ArrayList<>();
}
