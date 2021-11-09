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

//    @GetMapping("/upload")
//    public String greeting() {
//
//        return "upload";
//    }

    @GetMapping("/article")
    public String newArticle(
//            @RequestParam(name = "id", required = false, defaultValue = "1") String id,
            Map<String, String> model) {

//        Article article = articleRepository.findById(Long.valueOf(id)).orElse(null);

//        model.addAttribute("article", article);
        model.put("message", "Please use form to upload your article");
        return "article";
    }


    @PostMapping("/article")
    public String uploadArticle(@RequestParam MultipartFile file, Map<String, String> model) {
        String message = unzipService.getArticle(file);
        model.put("message", message);
        return "/article";
    }
}
