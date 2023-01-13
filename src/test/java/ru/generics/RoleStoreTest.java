package ru.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoleStoreTest {

    @Test
    void whenAddSuccessfully() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "programmer"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("programmer");
    }

    @Test
    void whenAddDuplicate() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "programmer"));
        store.add(new Role("1", "banker"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("programmer");
    }

    @Test
    void whenAddAndFindThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "programmer"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenReplaceSuccessfully() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "programmer"));
        store.replace("1", new Role("1", "banker"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("banker");
    }

    @Test
    void whenReplaceNoChange() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "programmer"));
        store.replace("10", new Role("10", "banker"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("programmer");
    }

    @Test
    void whenDeleteSuccessfully() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "programmer"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "programmer"));
        store.delete("2");
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("programmer");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Programmer"));
        boolean rsl = store.replace("1", new Role("1", "banker"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "programmer"));
        boolean rsl = store.replace("10", new Role("10", "banker"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "programmer"));
        boolean rsl = store.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "programmer"));
        boolean rsl = store.delete("2");
        assertThat(rsl).isFalse();
    }
}