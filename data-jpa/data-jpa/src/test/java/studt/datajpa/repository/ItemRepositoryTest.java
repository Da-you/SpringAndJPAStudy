package studt.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import studt.datajpa.entity.Item;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ItemRepositoryTest  {

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void save() throws Exception {
        //given
        Item item = new Item("A");
        //when
        itemRepository.save(item);
        //then
    }
}