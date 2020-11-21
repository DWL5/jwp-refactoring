package kitchenpos.dao;

import static kitchenpos.fixture.TableGroupFixture.createTableGroup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import kitchenpos.domain.TableGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DaoTest
public class TableGroupDaoTest {
    @Autowired
    private TableGroupDao tableGroupDao;

    @DisplayName("단체 지정을 저장할 수 있다.")
    @Test
    void save() {
        TableGroup tableGroup = createTableGroup(null, LocalDateTime.now());

        TableGroup savedTableGroup = tableGroupDao.save(tableGroup);

        assertAll(
            () -> assertThat(savedTableGroup.getId()).isNotNull(),
            () -> assertThat(savedTableGroup.getCreatedDate())
                .isEqualTo(tableGroup.getCreatedDate())
        );
    }

    @DisplayName("단체 지정 아이디로 단체 지정을 조회할 수 있다.")
    @Test
    void findById() {
        TableGroup tableGroup = tableGroupDao
            .save(createTableGroup(null, LocalDateTime.now()));

        Optional<TableGroup> foundTableGroup = tableGroupDao.findById(tableGroup.getId());

        assertThat(foundTableGroup.get()).isEqualToComparingFieldByField(tableGroup);
    }

    @DisplayName("단체 지정 목록을 조회할 수 있다.")
    @Test
    void findAll() {
        List<TableGroup> savedTableGroups = Arrays.asList(
            tableGroupDao.save(createTableGroup(null, LocalDateTime.now())),
            tableGroupDao.save(createTableGroup(null, LocalDateTime.now())),
            tableGroupDao.save(createTableGroup(null, LocalDateTime.now()))
        );

        List<TableGroup> allTableGroups = tableGroupDao.findAll();

        assertThat(allTableGroups).usingFieldByFieldElementComparator()
            .containsAll(savedTableGroups);
    }
}