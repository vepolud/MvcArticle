package ru.javaschool.polud.mvcarticle;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ArticlesController {
    UnzipService unzipService;

    public ArticlesController(UnzipService unzipService) {
        this.unzipService = unzipService;
    }

    @GetMapping("/")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "article";
    }
    @GetMapping("/upload")
    public String greeting() {

        return "upload";
    }
    @PostMapping("/")
    public String getArticle(@RequestParam MultipartFile file) {
        unzipService.getArticle(file);
        return "redirect:/";
    }
}
