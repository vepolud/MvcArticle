package ru.javaschool.polud.mvcarticle;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Component
public class UnzipService {
    final ArticleRepository articleRepository;

    public UnzipService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public String getArticle(MultipartFile file) {

        if (file.isEmpty() || !file.getContentType().equals("application/x-zip-compressed")) {
            return "Uploaded file is not a zip archive";
        }

        try (ZipInputStream zipInputStream = new ZipInputStream(file.getInputStream())) {
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            if (zipEntry != null && zipEntry.getName().equals("article.TXT")) {
                Scanner scanner = new Scanner(zipInputStream);
                String header = scanner.nextLine();
                if (!scanner.hasNext()) {
                    return "There is only one line in the file";
                }
                StringBuilder sb = new StringBuilder();
                while (scanner.hasNext()) {
                    sb.append(scanner.nextLine());
                }
                if (zipInputStream.getNextEntry() != null) {
                    return "There are more then one files in the zip archive";
                }
                articleRepository.save(new Article(header, sb.toString()));
                return "The file was successfully uploaded";
            } else return "There are no article.TXT in uploaded zip archive";
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "It's ok";
    }
}
