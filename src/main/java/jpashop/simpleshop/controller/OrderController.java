package jpashop.simpleshop.controller;

import jpashop.simpleshop.domain.Member;
import jpashop.simpleshop.domain.item.Item;
import jpashop.simpleshop.service.ItemService;
import jpashop.simpleshop.service.MemberService;
import jpashop.simpleshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model) {
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "orders/orderForm";
    }
}
