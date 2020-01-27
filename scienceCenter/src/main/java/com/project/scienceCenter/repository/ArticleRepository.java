package com.project.scienceCenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.scienceCenter.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
