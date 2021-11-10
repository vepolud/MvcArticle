package ru.javaschool.polud.mvcarticle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
public class ArticlesController {
    UnzipService unzipService;
    ArticleRepository articleRepository;
    String message;

    public ArticlesController(UnzipService unzipService, ArticleRepository articleRepository) {
        this.unzipService = unzipService;
        this.articleRepository = articleRepository;
    }

    @GetMapping("/articles")
    public String getArticleList(Map<String, Object> model) {
        Iterable<Article> articles = articleRepository.findAll();
        model.put("articles", articles);
        return "articles";
    }

    @GetMapping("/article")
    public String newArticle(Map<String, Object> model) {
        message = "Please use form to upload your article";
        model.put("message", message);
        return "article";
    }

    @PostMapping("/article")
    public String uploadArticle(@RequestParam MultipartFile file, Map<String, String> model) {
        message = unzipService.getArticle(file);
        model.put("message", message);
        return "article";
    }
}
