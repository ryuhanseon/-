package com.example.n1m9.Article;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Article> articleList = this.articleService.articlelist();
        model.addAttribute("articleList", articleList);
        return "article_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {

        Article article = this.articleService.create(id);
        model.addAttribute("article", article);

        return "article_detail";
    }

    @GetMapping("/create")
    public String articleCreate() {

        return "article_form";
    }

    @PostMapping("/create")
    public String articleCreate(@RequestParam(value = "subject") String subject,
                                @RequestParam(value = "content") String content) {
        this.articleService.articleCreate(subject, content);
        return "redirect:/article/list";
    }

    @GetMapping("/modify/{id}")
    public String articleModify(@PathVariable("id") Integer id, Model model) {

        Article article = this.articleService.create(id);
        model.addAttribute("article", article);
        return "article_form";
    }

    @PostMapping("/modify/{id}")
    public String articleModify(@PathVariable("id") Integer id,
                                @RequestParam(value = "subject") String subject,
                                @RequestParam(value = "content") String content) {
        Article article = this.articleService.create(id);

        if (article != null) {
            // 기존 코드 수정 없이 modify 메소드 호출 시 인자를 맞춰줌
            articleService.modify(article, subject, content);

            return "redirect:/article/detail/" + id;
        } else {
            return "error";
        }
    }
    @GetMapping("/delete/{id}")
    public String showDeleteConfirmation(Model model, @PathVariable("id") Integer id) {
        Article article = articleService.create(id);
        model.addAttribute("article", article);
        return "article_delete";
    }

    @PostMapping("/delete/{id}")
    public String articleDelete(@PathVariable("id") Integer id) {
        articleService.delete(id);
        return "redirect:/article/list";
    }




}

