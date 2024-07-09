package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach //테스트가 끝날때마다 실행
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Item item = new Item("itemA",10000, 10);
        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(savedItem.getId());
        assertEquals(findItem.getId(), savedItem.getId());
    }

    @Test
    void findAll(){
        //given
        Item item1 = new Item("itemA",10000, 10);
        Item item2 = new Item("itemB",20000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);
        //when
        List<Item> items = itemRepository.findAll();
        //then
        assertEquals(items.size(), 2);
    }

    @Test
    void updateItem(){
        //given
        Item item1 = new Item("itemA",10000, 10);
        Item savedItem = itemRepository.save(item1);
        Long itemId = savedItem.getId();

        //when
        Item updateParam = new Item("itemB",20000, 20);
        itemRepository.update(itemId,updateParam);

        //then
        Item findItem = itemRepository.findById(itemId);
//        assertEquals(findItem.getId(), updateParam.getId());

    }

}