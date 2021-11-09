package ru.javaschool.polud.mvcarticle;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Optional;

@Controller
public class ArticlesController {
    UnzipService unzipService;
    ArticleRepository articleRepository;

    public ArticlesController(UnzipService unzipService, ArticleRepository articleRepository) {
        this.unzipService = unzipService;
        this.articleRepository = articleRepository;
    }

    @GetMapping("/articles")
    public String getArticleList(
            /*@RequestParam(name="name", required=false, defaultValue="No articles") String name,*/
            Map<String, Object> model) {
        Iterable <Article> articles = articleRepository.findAll();
        model.put("articles", articles);
        return "articles";
    }

    @GetMapping("/upload")
    public String greeting() {

        return "upload";
    }

    @GetMapping("/article")
    public String getArticle(
            @RequestParam(name = "id", required=false, defaultValue="1") String id,
            Model model) {
        System.out.println(id);
        Article article = articleRepository.findById(Long.valueOf(id)).orElse(null) ;

        model.addAttribute("article", article);
        return "article";
    }




    @PostMapping("/article")
    public String newArticle(@RequestParam MultipartFile file) {
        unzipService.getArticle(file);
        return "redirect:/articles";
    }
}
