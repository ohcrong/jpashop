package jpashop.simpleshop.service;

import jpashop.simpleshop.domain.Address;
import jpashop.simpleshop.domain.Member;
import jpashop.simpleshop.domain.Order;
import jpashop.simpleshop.domain.OrderStatus;
import jpashop.simpleshop.domain.item.Book;
import jpashop.simpleshop.repositpory.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    
    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = new Member();
        member.setName("둘리");
        member.setAddress(new Address("서울","종로1길","123-45"));
        entityManager.persist(member);

        Book book = new Book();
        book.setName("JPA101");
        book.setPrice(10000);
        book.setStockQuantity(10);
        entityManager.persist(book);

        int count = 2;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), count);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("상품주문상태->ORDER", OrderStatus.ORDER, getOrder.getOrderStatus());
        Assert.assertEquals("주문한 상품 종류 수가 정확해야한다", 1, getOrder.getOrderItems().size());
        Assert.assertEquals("주문 가격은 가격 * 수량", 20000, getOrder.getTotalPrice());
        Assert.assertEquals("주문 수량만큰 재고가 줄어야한다.", 8, book.getStockQuantity());
    }
    
    @Test
    public void 주문취소() throws Exception {
        //given
        
        //when
        //then
    }
    
    @Test
    public void 상품주문_재고수량초과() throws Exception {
        //given
        
        //when
        //then
    }

}