package kitchenpos.menugroup.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenuGroupTest {

    @DisplayName("메뉴그룹 이름 값이 없으면 실패한다")
    @Test
    void createFailBecauseOfNameNullTest() {
        //when && then
        assertThatThrownBy(() -> new MenuGroup(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("메뉴그룹 이름은 필수로 입력되어야 합니다.");
    }

}