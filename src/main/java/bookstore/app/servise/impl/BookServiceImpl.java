package bookstore.app.servise.impl;

import bookstore.app.dto.BookDto;
import bookstore.app.dto.CreateBookRequestDto;
import bookstore.app.exeption.EntityNotFoundException;
import bookstore.app.mapper.BookMapper;
import bookstore.app.model.Book;
import bookstore.app.repository.BookRepository;
import bookstore.app.servise.BookService;
import jakarta.validation.ConstraintViolationException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    private BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDto createBook(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        Book saved = null;

        try {
            saved = bookRepository.save(book);
        } catch (DataIntegrityViolationException e) {
            // Error handling when data integrity is violated
        } catch (ConstraintViolationException e) {
            // Constraint Violation Error Handling
        }

        return bookMapper.toDto(saved);
    }

    @Override
    public List<BookDto> getAll() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            throw new EntityNotFoundException("No books found");
        }
        return books.stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto getById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book with id "
                        + id + "is not found"));
        return bookMapper.toDto(book);
    }
}
