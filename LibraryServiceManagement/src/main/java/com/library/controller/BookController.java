package com.library.controller;


import com.library.entity.Book;
import com.library.entity.Category;
import com.library.repository.BookRepository;
import com.library.repository.CategoryRepository;
import com.library.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookController {
    private final BookRepository bookRepository;
    private final BookService bookService;
    private final CategoryRepository categoryRepository;

    @GetMapping("/books")
    public ResponseEntity<Page<Book>> getAllBooksPaged(Pageable pageable) {
        Page<Book> books = bookService.getAllBooksPaged(pageable);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/books/search")
    public ResponseEntity<Page<Book>> getAllBooksByKeywordPaged(@RequestParam("keyword") String keyword, Pageable pageable) {
        Page<Book> books = bookService.getAllBooksByKeywordPaged(keyword, pageable);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }


    @GetMapping("/book/{id}")
    public ResponseEntity<?> getBookByID(@PathVariable Long id){
        if(bookRepository.findById(id) == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with id "+id+" is not existed !");
        }else {
            return ResponseEntity.ok().body(bookRepository.findById(id));
        }
    }

    @GetMapping("/books/category/{cateId}")
    public ResponseEntity<Page<Book>> getBookByCateIDPaged(@PathVariable Long cateId, Pageable pageable) {
        Page<Book> books = bookService.getAllBookByCategoryIDPaged(cateId, pageable);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/books/cateID-search")
    public ResponseEntity<Page<Book>> getBookByCateIDAndKeywordPaged(@RequestParam("cateID") Long cateID,
                                                                     @RequestParam("keyword") String keyword,
                                                                     Pageable pageable) {
        Page<Book> books = bookService.getAllBookByCateIDAndKeywordPaged(cateID, keyword, pageable);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }


    @GetMapping("/books/best-seller/top")
    public ResponseEntity<?> getTopBestSeller(@RequestParam("topNumber") int topNumber){
        return ResponseEntity.ok().body(bookService.getTopSellerOfBook(topNumber));
    }

    @PostMapping("/books/add")
    public Book createBook(@RequestParam("categoryId") Long categoryId ,@RequestBody Book book) {
        Category categoryFind = categoryRepository.findById(categoryId).get();
        book.setCategory(categoryFind);
        System.out.println(book);
        return bookService.createBook(book);
    }

    @DeleteMapping("/books/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.deleteBook(id));
    }

    @PutMapping("/books/save/{id}")
    public ResponseEntity<Book> updateBook(@RequestParam("categoryId") Long categoryId ,@PathVariable Long id,
                                           @RequestBody Book book) {
        Category categoryFind = categoryRepository.findById(categoryId).get();
        book.setCategory(categoryFind);
        book = bookService.updateBook(id, book);
        return ResponseEntity.ok(book);
    }
}
