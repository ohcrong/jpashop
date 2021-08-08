package jpashop.simpleshop.service;

import jpashop.simpleshop.domain.Delivery;
import jpashop.simpleshop.domain.Member;
import jpashop.simpleshop.domain.Order;
import jpashop.simpleshop.domain.OrderItem;
import jpashop.simpleshop.domain.item.Item;
import jpashop.simpleshop.repositpory.ItemRepository;
import jpashop.simpleshop.repositpory.MemberRepository;
import jpashop.simpleshop.repositpory.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Long order(Long memberId, Long itemId, int count) {
        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
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
        return order.getId();
    }
    //취소
    //검색
}
