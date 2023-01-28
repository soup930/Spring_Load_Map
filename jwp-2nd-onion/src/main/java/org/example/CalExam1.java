package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalExam1 {

    public int add(String stringParam) {

        if (stringParam == null || stringParam.isEmpty()) {
            return 0;
        }

        if (stringParam.contains(",") || stringParam.contains(":")) {
            String[] paramArray = stringParam.split(",|:");
            return validation(paramArray);
        }

        Matcher m = Pattern.compile("//(.)\n(.*)").matcher(stringParam);
        if (m.find()) {
            String customDelimeter = m.group(1);
            String[] tokens = m.group(2).split(customDelimeter);
            return validation(tokens);
        }

        return Integer.parseInt(stringParam);

    }

    private int validation(String[] paramArray) {
        int result = 0;
        for (String s : paramArray) {
            int paramInt = Integer.parseInt(s);
            if (paramInt < 0) {
                throw new RuntimeException("음수는 사용할 수 없습니다.");
            }

            result += paramInt;
        }

        return result;
    }
}
