package jpashop.simpleshop.service;

import jpashop.simpleshop.domain.item.Book;
import jpashop.simpleshop.domain.item.Item;
import jpashop.simpleshop.domain.item.UpdateItemDto;
import jpashop.simpleshop.repositpory.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(UpdateItemDto dto) {
        Book getBook = (Book) itemRepository.findOne(dto.getId());
        getBook.updateItem(dto);
        //Entity 영속화, dirty checking으로 변경
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findItem(Long id) {
        return itemRepository.findOne(id);
    }
}
