package pl.edu.amu.dji.jms.lab10.books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.amu.dji.jms.lab10.books.controller.exception.BookAlreadyExistsException;
import pl.edu.amu.dji.jms.lab10.books.controller.exception.BookNotFoundException;
import pl.edu.amu.dji.jms.lab10.books.model.Book;
import pl.edu.amu.dji.jms.lab10.books.repository.BookRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Book> books(){
        return repository.findAll();
    }


    @RequestMapping(value = "/{isbn}", method = RequestMethod.GET)
    public Book getByIsbn(@PathVariable String isbn) {
        Book book = repository.findOne(isbn);
        if(book == null){
            throw new BookNotFoundException();
        }

        return book;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Book addBook(@Valid @RequestBody Book book) {
        if(repository.exists(book.getIsbn())){
            throw new BookAlreadyExistsException();
        }

        return repository.save(book);
    }

    @RequestMapping(value = "/{isbn}", method = RequestMethod.PUT)
    public Book updateBook(@PathVariable String isbn, @Valid @RequestBody Book book) {
        Book existingBook = repository.findOne(isbn);
        if(existingBook==null){
            throw new BookNotFoundException();
        }

        existingBook.setTitle(book.getTitle());
        existingBook.setDescription(book.getDescription());
        existingBook.setAuthors(book.getAuthors());

        return repository.save(existingBook);
    }

    @RequestMapping(value = "/{isbn}", method = RequestMethod.DELETE)
    public Book removeBook(@PathVariable String isbn) {
        Book book = repository.findOne(isbn);
        if(book==null){
            throw new BookNotFoundException();
        }

        repository.delete(isbn);
        return book;
    }
}
