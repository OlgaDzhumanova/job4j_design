package ru.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void thereIsNoEqual() {
        NameLoad nameLoad = new NameLoad();
        String name = "name";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: name does not contain the symbol \"=\"")
                .hasMessageContaining(name);
    }

    @Test
    void startWithEqualSymbol() {
        NameLoad nameLoad = new NameLoad();
        String name = "=name";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: =name does not contain a key")
                .hasMessageContaining(name);
    }

    @Test
    void endsWithEqualSymbol() {
        NameLoad nameLoad = new NameLoad();
        String name = "name=";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: name= does not contain a value")
                .hasMessageContaining(name);
    }

}