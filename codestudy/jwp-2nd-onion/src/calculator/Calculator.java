package calculator;

public class Calculator {

    public Integer add(String param) {
        Integer result = 0;

        param = param.trim();
        String[] paramArray;

        // 문자열이 없을 때
        if (param == null || param.equals("")) {
            result = 0;
            return result;
        }

        // 쉼표 포함 시
        if (param.contains(",")) {
            paramArray = param.split(",");
        }

        return result;
    }

}
