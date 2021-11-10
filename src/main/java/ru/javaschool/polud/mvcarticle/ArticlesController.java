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
    public String newArticle(
//            @RequestParam(name = "id", required = false, defaultValue = "1") String id,
            Map<String, Object> model) {
        System.out.println("--------------------/article is running");
//        Article article = articleRepository.findById(Long.valueOf(id)).orElse(null);


//        model.addAttribute("article", article);
        message = "Please use form to upload your article";
        model.put("message", message);
        return "article";
    }

    @PostMapping("/article")
    public String uploadArticle(@RequestParam MultipartFile file, Map<String, String> model) {
        message = unzipService.getArticle(file);
        System.out.println("-------------------------" + message);
        model.put("message", message);
        return "article";
    }
}
