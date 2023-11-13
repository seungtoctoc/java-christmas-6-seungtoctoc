package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ApplicationTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    void 모든_타이틀_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                "<주문 메뉴>",
                "<할인 전 총주문 금액>",
                "<증정 메뉴>",
                "<혜택 내역>",
                "<총혜택 금액>",
                "<할인 후 예상 결제 금액>",
                "<12월 이벤트 배지>"
            );
        });
    }

    @DisplayName("혜택 없음 출력 테스트 (총주문 금액 만원 이하)")
    @ParameterizedTest
    @CsvSource({
            "3, 양송이수프-1",
            "10, 타파스-1,제로콜라-1"
    })
    void 혜택_내역_없음_출력(String date, String order) {
        assertSimpleTest(() -> {
            run(date, order);
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
        });
    }

    @DisplayName("날짜 입력값에 대한 예외 처리")
    @ParameterizedTest
    @ValueSource(strings = {"0", "32", "a"})
    void 날짜_예외_테스트(String input) {
        assertSimpleTest(() -> {
            runException(input);
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @DisplayName("주문 예외처리 테스트")
    @ParameterizedTest
    @CsvSource({
            "3, 양송이수프-1,제로콜라a-3",
            "10, 타파스,제로콜라-1",
            "22, 시저샐러드-a,바비큐립-1",
            "30, 타파스-3-1,레드와인-1"
    })
    void 주문_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
