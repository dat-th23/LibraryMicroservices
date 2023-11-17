package com.library.service;

import com.library.entity.Book;
import com.library.entity.Category;
import com.library.entity.dto.BookTopSellerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    Book createBook(Book book);

    List<Book> getAllBooks();
    List<Book> getAllBookByCategoryID(Long cateID);

    String deleteBook(Long id);

    Book updateBook(Long id, Book book);

    List<Book> getAllBookByKeyword(String keyword);
    List<Book> getAllBookByCateIDAndKeyword(Long cateID, String keyword);
    List<BookTopSellerDto> getTopSellerOfBook(int topNumber);
    List<Book> getListBook_InOrder(String orderId);


    //Hỗ trợ phân trang
    Page<Book> getAllBooksPaged(Pageable pageable);
    Page<Book> getAllBooksByKeywordPaged(String keyword, Pageable pageable);
    Page<Book> getAllBookByCategoryIDPaged(Long cateID, Pageable pageable);
    Page<Book> getAllBookByCateIDAndKeywordPaged(Long cateID, String keyword, Pageable pageable);
}
