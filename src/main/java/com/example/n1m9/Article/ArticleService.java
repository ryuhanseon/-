package com.example.n1m9.Article;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import com.example.n1m9.Article.DataNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> articlelist(){
        return this.articleRepository.findAll();
    }
    public Article create(Integer id){
        Optional<Article> article = this.articleRepository.findById(id);
        if (article.isPresent()){
            return article.get();
        }
        else {
            throw new DataNotFoundException("article not found");
        }
    }
    public void articleCreate(String subject, String content){
        Article a = new Article();
        a.setSubject(subject);
        a.setContent(content);
        a.setCreateDate(LocalDateTime.now());
        this.articleRepository.save(a);
    }
    public void modify(Article article, String subject, String content) {
        article.setSubject(subject);
        article.setContent(content);
        article.setModifyDate(LocalDateTime.now());
        this.articleRepository.save(article);
    }


    public void delete(Integer id) {
        articleRepository.deleteById(id);
    }
}
