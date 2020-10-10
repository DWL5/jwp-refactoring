package kitchenpos.ui;

import kitchenpos.TestObjectFactory;
import kitchenpos.application.TableService;
import kitchenpos.domain.OrderTable;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TableRestController.class)
class TableRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TableService tableService;

    @DisplayName("테이블 생성 요청 요청 테스트")
    @Test
    void create() throws Exception {
        OrderTable orderTable = TestObjectFactory.creatOrderTableDto();
        orderTable.setId(1L);

        given(tableService.create(any())).willReturn(orderTable);

        mockMvc.perform(post("/api/tables")
                .content("{\n"
                        + "  \"numberOfGuests\": 0,\n"
                        + "  \"empty\": true\n"
                        + "}")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/tables/1"))
                .andExpect(jsonPath("$.id", Matchers.instanceOf(Number.class)))
                .andExpect(jsonPath("$.tableGroupId", Matchers.nullValue()))
                .andExpect(jsonPath("$.numberOfGuests", Matchers.instanceOf(Number.class)))
                .andExpect(jsonPath("$.empty", Matchers.instanceOf(Boolean.class)));
    }

    @DisplayName("테이블 목록 요청 테스트")
    @Test
    void list() throws Exception {
        List<OrderTable> orderTables = new ArrayList<>();
        orderTables.add(new OrderTable());
        orderTables.add(new OrderTable());

        given(tableService.list()).willReturn(orderTables);

        mockMvc.perform(get("/api/tables"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }

    @DisplayName("테이블의 setEmpty를 변경 요청 테스트")
    @Test
    void changeEmpty() throws Exception {
        OrderTable orderTable = TestObjectFactory.creatOrderTableDto();
        orderTable.setId(1L);
        orderTable.setEmpty(false);

        given(tableService.changeEmpty(anyLong(), any())).willReturn(orderTable);

        mockMvc.perform(put("/api/tables/1/empty")
                .content("{\n"
                        + "  \"empty\": false\n"
                        + "}")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.empty", Matchers.is(false)));
    }

    @DisplayName("고객 수 변경 요청 테스트")
    @Test
    void changeNumberOfGuests() throws Exception {
        OrderTable orderTable = TestObjectFactory.creatOrderTableDto();
        orderTable.setId(1L);
        orderTable.setNumberOfGuests(4);

        given(tableService.changeNumberOfGuests(anyLong(), any())).willReturn(orderTable);


        mockMvc.perform(put("/api/tables/1/number-of-guests")
                .content("{\n"
                        + "  \"numberOfGuests\": 4\n"
                        + "}")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numberOfGuests", Matchers.is(4)));

    }
}