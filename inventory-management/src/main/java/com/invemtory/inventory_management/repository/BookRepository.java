package com.invemtory.inventory_management.repository;



import com.invemtory.inventory_management.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
//    @Query("")
//    public void insertBook(Book book);
    public Book findById(long id);
    public List<Book> findAll();
}


