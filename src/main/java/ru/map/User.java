package ru.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>(16);
        Calendar birthday = Calendar.getInstance();
        User userOne = new User("Maya", 3, birthday);
        map.put(userOne, new Object());
        int hashCodeOne = userOne.hashCode();
        int hashOne = hashCodeOne ^ (hashCodeOne >>> 16);
        int backetOne = hashOne & 15;
        User userTwo = new User("Maya", 3, birthday);
        map.put(userTwo, new Object());
        int hashCodeTwo = userTwo.hashCode();
        int hashTwo = hashCodeTwo ^ (hashCodeTwo >>> 16);
        int backetTwo = hashTwo & 15;

        System.out.printf("userOne = хешкод: %d , хеш: %d , бакет: %d",
                hashCodeOne, hashOne, backetOne);

        System.out.println();

        System.out.printf("userTwo = хешкод: %d , хеш: %d , бакет: %d",
                hashCodeTwo, hashTwo, backetTwo);

    }
}
