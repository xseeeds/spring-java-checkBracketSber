package ru.xs.springjavacheckbracketsber;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class SpringJavaCheckBracketSberApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void mainTest() {
        assertDoesNotThrow(SpringJavaCheckBracketSberApplication::new);
        assertDoesNotThrow(() -> SpringJavaCheckBracketSberApplication.main(new String[]{}));
    }

}
