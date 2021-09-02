package jpashop.simpleshop.api;

import jpashop.simpleshop.domain.Address;
import jpashop.simpleshop.domain.Order;
import jpashop.simpleshop.domain.OrderStatus;
import jpashop.simpleshop.repositpory.OrderRepository;
import jpashop.simpleshop.repositpory.OrderSearch;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


/**
 * xToOne (ManyToOne, OneToOne)
 * ORDER
 * ORDER -> MEMBER
 * ORDER -> DELIVERY
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> orderV2() {
        List<Order> orders = orderRepository.findAll(new OrderSearch());
        List<SimpleOrderDto> collect = orders.stream().
                map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());
        return collect;
    }

    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> orderV3() {
        List<Order> order = orderRepository.findAllWithMemberDelivery();
        List<SimpleOrderDto> collect = order.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());
        return collect;
    }

    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            this.orderId = order.getId();
            this.name = order.getMember().getName();
            this.orderDate = order.getOrderDate();
            this.orderStatus = order.getStatus();
            this.address = order.getDelivery().getAddress();
        }
    }
}
