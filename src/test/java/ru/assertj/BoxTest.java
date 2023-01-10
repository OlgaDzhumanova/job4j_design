package ru.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .isNotEmpty()
                .startsWith("Sph")
                .endsWith("ere");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(9, 11);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object")
                .isNotEmpty()
                .startsWith("Unknown")
                .endsWith("object");
    }

    @Test
    void vertexIsNegative() {
        Box box = new Box(8, 0);
        int ver = box.getNumberOfVertices();
        assertThat(ver).isEqualTo(-1)
                .isLessThan(0)
                .isNotPositive();
    }

    @Test
    void vertexIsPositive() {
        Box box = new Box(8, 4);
        int ver = box.getNumberOfVertices();
        assertThat(ver).isEqualTo(8)
                .isNotNegative()
                .isPositive();
    }

    @Test
    void isExistIsTrue() {
        Box box = new Box(8, 4);
        boolean ex = box.isExist();
        assertThat(ex).isTrue()
                .isNotNull();
    }

    @Test
    void isExistIsFalse() {
        Box box = new Box(8, 0);
        boolean ex = box.isExist();
        assertThat(ex).isFalse()
                .isNotNull();
    }

    @Test
    void areaIsZero() {
        Box box = new Box(4, 4);
        double ar = box.getArea();
        assertThat(ar).isEqualTo(27.71d, withPrecision(0.003d))
                .isCloseTo(27.71d, withPrecision(0.01d))
                .isLessThan(27.72d)
                .isGreaterThan(27.70d);
    }

    @Test
    void areaIsNotZero() {
        Box box = new Box(7, 0);
        double ar = box.getArea();
        assertThat(ar).isEqualTo(0.0d)
                .isCloseTo(0.0d, withPrecision(0.01d))
                .isLessThan(0.1d);
    }
}