package kitchenpos.fixtures;

import kitchenpos.order.domain.Order;
import kitchenpos.order.domain.OrderStatus;

import java.time.LocalDateTime;
import java.util.Arrays;

import static kitchenpos.fixtures.OderLineItemFixture.첫번째주문의아이템1;
import static kitchenpos.fixtures.OderLineItemFixture.첫번째주문의아이템2;

public class OrderFixture {

    public static Order 첫번째주문() {
        Order order = new Order(1L, 1L, LocalDateTime.now(), Arrays.asList(첫번째주문의아이템1(), 첫번째주문의아이템2())));
        return order;
    }

    public static Order 첫번째주문(OrderStatus orderStatus) {
        Order order = 첫번째주문();
        order.setOrderStatus(orderStatus.name());
        return order;
    }
}
