package ru.javaschool.polud.mvcarticle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Scanner;
import java.util.zip.ZipInputStream;

@Component
public class UnzipService {
    final
    ArticleRepository articleRepository;

    public UnzipService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article getArticle(MultipartFile file) {

//        Article article = new Article();
        try {
            ZipInputStream zipInputStream = new ZipInputStream(file.getInputStream());
            while (zipInputStream.getNextEntry() != null){
                Scanner scanner = new Scanner(zipInputStream);
                String header = scanner.nextLine();
                StringBuilder sb = new StringBuilder();
                while (scanner.hasNext()) {
                    sb.append(scanner.nextLine());
//                    System.out.println(scanner.nextLine());
                }
                articleRepository.save(new Article(header, sb.toString()));
                System.out.println(header);
                System.out.println(sb);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
