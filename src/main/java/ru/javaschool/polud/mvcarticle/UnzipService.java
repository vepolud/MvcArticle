package ru.javaschool.polud.mvcarticle;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Scanner;
import java.util.zip.ZipInputStream;

@Component
public class UnzipService {
    public Article getArticle(MultipartFile file) {
        Article article = new Article();
        try {
            ZipInputStream zipInputStream = new ZipInputStream(file.getInputStream());
            while (zipInputStream.getNextEntry() != null){
                Scanner scanner = new Scanner(zipInputStream);
                while (scanner.hasNext()) {

                    System.out.println(scanner.nextLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
