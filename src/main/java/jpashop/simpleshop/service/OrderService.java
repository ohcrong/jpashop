package jpashop.simpleshop.service;

import jpashop.simpleshop.domain.Delivery;
import jpashop.simpleshop.domain.Member;
import jpashop.simpleshop.domain.Order;
import jpashop.simpleshop.domain.OrderItem;
import jpashop.simpleshop.domain.item.Item;
import jpashop.simpleshop.repositpory.ItemRepository;
import jpashop.simpleshop.repositpory.MemberRepositoryOld;
import jpashop.simpleshop.repositpory.OrderRepository;
import jpashop.simpleshop.repositpory.OrderSearch;
import jpashop.simpleshop.repositpory.order.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        //엔티티 조회
        Member member = memberRepository.findById(memberId).get();
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);
        //Order 엔티티에 OrderItem, Delivery 모두 CascadeType.ALL 되어있기 때문에 persist 한번만
        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        //엔티티 조회
        Order order = orderRepository.findOne(orderId);
        //주문 취소
        order.cancel();
    }

    /**
     * 주문 검색
     */
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
        //단순 repository 위임인 경우 생략 가능
    }

}
