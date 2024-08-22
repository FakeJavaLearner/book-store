package bookstore.app.servise;

import bookstore.app.dto.BookDto;
import bookstore.app.dto.CreateBookRequestDto;
import java.util.List;

public interface BookService {
    BookDto createBook(CreateBookRequestDto requestDto);

    List<BookDto> getAll();

    BookDto getById(Long id);
}
