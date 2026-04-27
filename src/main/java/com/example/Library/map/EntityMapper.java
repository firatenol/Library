package com.example.Library.map;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.example.Library.Dto.CreateBookRequestDto;
import com.example.Library.Dto.BookResponse;
import com.example.Library.Entity.BookEntity;


@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = "spring"
)public interface EntityMapper {
    BookEntity toEntity(CreateBookRequestDto bookRequest);
    BookResponse toBookResponseDto(BookEntity bookEntity);
}
