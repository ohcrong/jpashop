package jpashop.simpleshop.domain.item;

import jpashop.simpleshop.controller.BookForm;
import lombok.Getter;

@Getter
public class UpdateItemDto {

    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;

    public UpdateItemDto(BookForm bookForm) {
        this.id = bookForm.getId();
        this.name = bookForm.getName();
        this.price = bookForm.getPrice();
        this.stockQuantity = bookForm.getStockQuantity();
        this.author = bookForm.getAuthor();
        this.isbn = bookForm.getIsbn();
    }
}
