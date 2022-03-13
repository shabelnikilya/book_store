package spring.bookstore.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.bookstore.model.Book;
import spring.bookstore.repository.service.BooksService;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.List;

@Controller
@PropertySource("classpath:application.properties")
@Slf4j
public class IndexControl {
    private final BooksService service;
    private final ServletContext servletContext;

    @Autowired
    public IndexControl(BooksService service, ServletContext servletContext) {
        this.service = service;
        this.servletContext = servletContext;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Book> books = (List) service.findAll();
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/style/images/phone.png")
    @ResponseBody
    public ResponseEntity<byte[]> loadImage(@Value("${spring.images.url}") String path) {
        byte[] image = null;
        String linkImage = servletContext.getRealPath("/") + path;
        try (BufferedInputStream in = new BufferedInputStream(
                new FileInputStream(linkImage))) {
            image = in.readAllBytes();
        } catch (IOException e) {
            log.error("Image file not found, check the path: {}", linkImage);
        }
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(image, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/style/table.css")
    @ResponseBody
    public ResponseEntity<String> cssTable(@Value("${spring.table.css}") String path) {
        StringBuilder result = new StringBuilder();
        String linkTable = servletContext.getRealPath("/") + path;
        try (BufferedReader in = new BufferedReader(
                new FileReader(linkTable))) {
            String css;
            while ((css = in.readLine()) != null) {
                result.append(css + System.lineSeparator());
            }
        } catch (IOException e) {
            log.error("Image file not found, check the path: {}", linkTable);
        }
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "text/css; charset=utf-8");
        return new ResponseEntity<>(result.toString(), httpHeaders, HttpStatus.OK);
    }
}
