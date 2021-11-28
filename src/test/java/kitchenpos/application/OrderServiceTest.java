package kitchenpos.application;

import kitchenpos.menu.dao.MenuDao;
import kitchenpos.dao.OrderLineItemDao;
import kitchenpos.dao.OrderTableDao;
import kitchenpos.menu.repository.MenuRepository;
import kitchenpos.order.application.OrderService;
import kitchenpos.order.domain.Order;
import kitchenpos.order.domain.OrderStatus;
import kitchenpos.order.dto.request.OrderRequest;
import kitchenpos.order.dto.response.OrderResponse;
import kitchenpos.order.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static kitchenpos.fixtures.OderTableFixture.비어있는주문테이블1;
import static kitchenpos.fixtures.OderTableFixture.첫번째주문테이블;
import static kitchenpos.fixtures.OrderFixture.첫번째주문;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private MenuRepository menuRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderLineItemDao orderLineItemDao;
    @Mock
    private OrderTableDao orderTableDao;

    @InjectMocks
    private OrderService orderService;

    @DisplayName("주문을 받을 수 있다.")
    @Test
    void testCreateOrder() {
        //given
        Order order = 첫번째주문();
        given(menuRepository.countByIdIn(any())).willReturn((long) order.getOrderLineItems().size());
        given(orderTableDao.findById(any())).willReturn(Optional.of(첫번째주문테이블()));
        given(orderRepository.save(any())).willReturn(첫번째주문());
        given(orderLineItemDao.save(any())).willReturn(any());

        //when
        Order actual = orderService.create(order);

        //then
        assertThat(actual.getId()).isEqualTo(1L);
    }

    @DisplayName("주문 시 주문 항목이 비어있으면 안된다.")
    @Test
    void testCreateOrderOrderItemEmpty() {
        //given
        Order order = new Order();
        order.setOrderLineItems(Collections.EMPTY_LIST);

        //when, then
        assertThatThrownBy(() -> {
            orderService.create(order);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 시 주문 항목의 메뉴는 등록된 메뉴여야 한다.")
    @Test
    void testCreateOrderMenuNotExist() {
        //given
        Order order = 첫번째주문();
        given(menuRepository.countByIdIn(any())).willReturn(0L);

        //when, then
        assertThatThrownBy(() -> {
            orderService.create(order);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 시, 주문 항목의 주문테이블은 등록된 테이블이어야 한다.")
    @Test
    void testCreateOrderTableNotExist() {
        //given
        Order order = 첫번째주문();
        given(menuRepository.countByIdIn(any())).willReturn((long) order.getOrderLineItems().size());
        given(orderTableDao.findById(any())).willReturn(Optional.empty());

        //when, then
        assertThatThrownBy(() -> {
            orderService.create(order);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 시, 비어있는 주문 테이블에서 주문이 되면 안된다.")
    @Test
    void testCreateOrderEmptyTable() {
        //given
        Order order = 첫번째주문();
        given(menuRepository.countByIdIn(any())).willReturn((long) order.getOrderLineItems().size());
        given(orderTableDao.findById(any())).willReturn(Optional.of(비어있는주문테이블1()));

        //when, then
        assertThatThrownBy(() -> {
            orderService.create(order);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 리스트를 조회 할 수 있다.")
    @Test
    void testList() {
        //given
        given(orderRepository.findAll()).willReturn((Collections.singletonList(첫번째주문())));

        //when
        List<Order> actual = orderService.list();

        //then
        assertThat(actual).hasSize(1);
    }

    @DisplayName("등록된 주문의 상태를 변경 할 수 있다.")
    @Test
    void testChangeOrderStatus() {
        //given
        OrderRequest orderRequest = new OrderRequest(1L, OrderStatus.MEAL.name(), Collections.emptyList());
        given(orderRepository.findById(any())).willReturn(Optional.of(첫번째주문(OrderStatus.COOKING)));

        //when
        OrderResponse actual = orderService.changeOrderStatus(1L, orderRequest);

        //then
        assertThat(actual.getOrderStatus()).isEqualTo(OrderStatus.MEAL.name());
    }
}