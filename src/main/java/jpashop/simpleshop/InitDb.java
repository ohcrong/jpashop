package jpashop.simpleshop;

import jpashop.simpleshop.domain.*;
import jpashop.simpleshop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {

        initService.dbInit1();
        initService.dbInit2();
    }
    //loading 시점에 생성

    @Service
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;
        public void dbInit1(){
            Member member = createMember("userA", "seoul", "111", "11100");
            em.persist(member);

            Book book1 = createBook("JPA1", 10000, 100);
            em.persist(book1);

            Book book2 = createBook("JPA2", 20000, 200);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);

        }

        public void dbInit2(){
            Member member = createMember("userB", "busan", "222", "22200");
            em.persist(member);

            Book book1 = createBook("SPRING1", 10000, 130);
            em.persist(book1);

            Book book2 = createBook("SPRING2", 20000, 150);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);

        }

        private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }

        private Book createBook(String name, int i, int i2) {
            Book book1 = new Book();
            book1.setName(name);
            book1.setPrice(i);
            book1.setStockQuantity(i2);
            return book1;
        }

        private Member createMember(String name, String city, String s, String s2) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, s, s2));
            return member;
        }


    }
}
