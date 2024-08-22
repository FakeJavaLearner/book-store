package bookstore.app.mapper;

import bookstore.app.config.MapperConfig;
import bookstore.app.dto.BookDto;
import bookstore.app.dto.CreateBookRequestDto;
import bookstore.app.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);
}
