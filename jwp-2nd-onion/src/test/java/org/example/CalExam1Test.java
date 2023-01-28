package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalExam1Test {

    CalExam1 cal;

    @BeforeEach
    public void setup() {
        cal = new CalExam1();
    }
    @Test
    void add() {
        assertEquals(9, cal.add("1,2:6"));
        assertEquals(10, cal.add("//;\n1;2;3;4"));
    }
}