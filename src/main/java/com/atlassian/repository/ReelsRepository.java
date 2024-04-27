package com.atlassian.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atlassian.models.Comment;
import com.atlassian.models.Reels;

public interface ReelsRepository extends JpaRepository<Reels,Integer>{

	public List<Reels> findByUserId(Integer userId);
}
