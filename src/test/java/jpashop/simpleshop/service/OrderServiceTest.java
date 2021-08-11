package jpashop.simpleshop.service;

import jpashop.simpleshop.domain.*;
import jpashop.simpleshop.domain.item.Book;
import jpashop.simpleshop.exception.NotEnoughStockException;
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
        Member member = createMember();

        Book book = createBook(10000, 10, "JPA101");

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

    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception {
        //given
        Member member = createMember();
        Book book = createBook(10000, 10, "JPA101");

        int orderCount = 11;

        //when
        orderService.order(member.getId(), book.getId(), orderCount);

        //then
        fail("재고 수량 부족 예외가 발생해야합니다");
    }

    @Test
    public void 주문취소() throws Exception {
        //given
        Member member = createMember();
        Book book = createBook(10000, 10, "JPA101");

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);
        Assert.assertEquals("주문상태가 CANCEL이어야한다", OrderStatus.CANCEL, getOrder.getOrderStatus());
        Assert.assertEquals("취소된 만큰 재고수량 원복", 10, book.getStockQuantity());

    }

    private Book createBook(int price, int stockQuantity, String name) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        entityManager.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("둘리");
        member.setAddress(new Address("서울","종로1길","123-45"));
        entityManager.persist(member);
        return member;
    }

}