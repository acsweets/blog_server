package com.blog;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "文章管理")
@RestController
@RequestMapping("/api/articles")
@CrossOrigin(origins = "*")
public class ArticleController {
    
    @Autowired
    private ArticleRepository articleRepository;
    
    @Operation(summary = "获取所有文章")
    @ApiResponse(responseCode = "200", description = "成功获取文章列表")
    @GetMapping
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }
    
    @Operation(summary = "根据ID获取文章")
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@Parameter(description = "文章ID", required = true) @PathVariable Long id) {
        Optional<Article> article = articleRepository.findById(id);
        return article.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @Operation(summary = "创建文章")
    @ApiResponse(responseCode = "200", description = "成功创建文章")
    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        return articleRepository.save(article);
    }
    
    @Operation(summary = "更新文章")
    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@Parameter(description = "文章ID", required = true) @PathVariable Long id, @RequestBody Article articleDetails) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            article.setTitle(articleDetails.getTitle());
            article.setContent(articleDetails.getContent());
            article.setAuthor(articleDetails.getAuthor());
            return ResponseEntity.ok(articleRepository.save(article));
        }
        return ResponseEntity.notFound().build();
    }
    
    @Operation(summary = "删除文章")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(@Parameter(description = "文章ID", required = true) @PathVariable Long id) {
        if (articleRepository.existsById(id)) {
            articleRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}