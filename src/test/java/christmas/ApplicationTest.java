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

    @DisplayName("주문 예외처리 테스트 (없는 메뉴, 수량 입력하지 않음, 수량에 숫자가 아닌 입력, 형식에 맞지않음, 총수량 20개 초과, 음료만 주문")
    @ParameterizedTest
    @CsvSource({
            "3, '양송이수프-1,제로콜라a-3'",
            "10, '타파스,제로콜라-1'",
            "22, '시저샐러드-a,바비큐립-1'",
            "30, '타파스-3-1,레드와인-1'",
            "25, '아이스크림-10,초코케이크-7,레드와인-4'",
            "20, '제로콜라-1,샴페인-2"
    })
    void 주문_예외_테스트(String date, String order) {
        assertSimpleTest(() -> {
            runException(date, order);
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @DisplayName("크리스마스 디데이 혜택 예외 테스트 (25일 이후)")
    @ParameterizedTest
    @CsvSource({
            "26, '양송이수프-1,제로콜라-3'",
            "29, '타파스-3,제로콜라-1'",
            "31, '시저샐러드-2,바비큐립-1'"
    })
    void 크리스마스_디데이_혜택_예외_테스트(String date, String order) {
        assertSimpleTest(() -> {
            runException(date, order);
            assertThat(output()).doesNotContain("크리스마스 디데이 할인");
        });
    }

    @DisplayName("크리스마스 디데이 혜택 테스트")
    @ParameterizedTest
    @CsvSource({
            "1, '양송이수프-7,제로콜라-3', '크리스마스 디데이 할인: -1,000원'",
            "20, '타파스-5,제로콜라-1', '크리스마스 디데이 할인: -2,900원'",
            "25, '시저샐러드-6,바비큐립-1', '크리스마스 디데이 할인: -3,400원'"
    })
    void 크리스마스_디데이_혜택_테스트(String date, String order, String output) {
        assertSimpleTest(() -> {
            runException(date, order);
            assertThat(output()).contains(output);
        });
    }

    @DisplayName("증정 테스트")
    @ParameterizedTest
    @CsvSource({
            "26, '양송이수프-20'",
            "29, '티본스테이크-2,아이스크림-2'"
    })
    void 증정_테스트(String date, String order) {
        assertSimpleTest(() -> {
            runException(date, order);
            assertThat(output()).contains("샴페인 1개");
        });
    }

    @DisplayName("평일 할인 테스트")
    @ParameterizedTest
    @CsvSource({
            "3, '양송이수프-20'",
            "17, '티본스테이크-2,아이스크림-2'",
            "28, '티본스테이크-1,아이스크림-2'"
    })
    void 평일_할인_테스트(String date, String order) {
        assertSimpleTest(() -> {
            runException(date, order);
            assertThat(output()).contains("평일 할인");
        });
    }

    @DisplayName("주말 할인 테스트")
    @ParameterizedTest
    @CsvSource({
            "1, '양송이수프-20'",
            "16, '티본스테이크-2,아이스크림-2'",
            "29, '티본스테이크-1,아이스크림-2'"
    })
    void 주말_할인_테스트(String date, String order) {
        assertSimpleTest(() -> {
            runException(date, order);
            assertThat(output()).contains("주말 할인");
        });
    }

    @DisplayName("특별 할인 테스트")
    @ParameterizedTest
    @CsvSource({
            "3, '양송이수프-20'",
            "31, '티본스테이크-2,아이스크림-2'",
            "25, '티본스테이크-1,아이스크림-2'"
    })
    void 특별_할인_테스트(String date, String order) {
        assertSimpleTest(() -> {
            runException(date, order);
            assertThat(output()).contains("특별 할인");
        });
    }

    @DisplayName("배지 테스트")
    @ParameterizedTest
    @CsvSource({
            "1, '티본스테이크-2,제로콜라-4', '산타'",
            "20, '아이스크림-5', '트리'",
            "27, '아이스크림-3', '별'"
    })
    void 배지_테스트(String date, String order, String output) {
        assertSimpleTest(() -> {
            runException(date, order);
            assertThat(output()).contains(output);
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
