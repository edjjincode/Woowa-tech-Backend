package christmas.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import camp.nextstep.edu.missionutils.Console;

import java.io.ByteArrayInputStream;

class SystemInputTest {
    @DisplayName("입력 값 정수로 바꾸는 지 확인")
    @Test
    void checkReadDate() {
        String inputData = "15";
        System.setIn(new ByteArrayInputStream(inputData.getBytes()));

        // Act
        Integer result = SystemInput.readDate();

        // Assert
        assertThat(result).isEqualTo(15);

    }
}