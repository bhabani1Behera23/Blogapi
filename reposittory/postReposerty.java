package com.BlogApi.BlogApi.reposittory;

import com.BlogApi.BlogApi.entity.post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface postReposerty extends JpaRepository<post,Long> {
}
