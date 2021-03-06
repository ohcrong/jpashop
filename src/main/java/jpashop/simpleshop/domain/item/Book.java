package jpashop.simpleshop.domain.item;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Book")
@Getter
@Setter
public class Book extends Item{

    private String author;
    private String isbn;

    //==생성 메서드==//
    public static Book createBook(String name, int price, int stockQuantity, String author, String isbn) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        book.setAuthor(author);
        book.setIsbn(isbn);

        return book;
    }

    public void updateItem(UpdateItemDto dto) {
        this.author = dto.getAuthor();
        this.isbn = dto.getIsbn();
        this.updateItem(dto.getName(), dto.getPrice(), dto.getStockQuantity());

    }
}
