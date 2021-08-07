package jpashop.simpleshop.repositpory;

import jpashop.simpleshop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.EAN;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    //public List<Order> findAll(OrderSearch orderSearch) {}
}
