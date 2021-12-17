package org.zeorck.java.optional;

import java.util.Optional;
import java.util.OptionalInt;

public class OptionalEx0 {

    public static void main(String[] args) {

        Optional<String> opt = Optional.ofNullable(null);
        Optional<String> opt2 = Optional.empty();

        System.out.println(opt.equals(opt2));
    }

}
