package org.zeorck.java;

interface Parseable {
    public abstract void parse(String fileName);
}

class ParseManager {
    // 리턴타입이 Parseable 인터페이스다.
    public static Parseable getParser(String type) {
        if (type.equals("XML")) {
            return new XMLParser();
        } else {
            Parseable p = new HTMLParser();
            return p;
        }
    }
}

class XMLParser implements Parseable {
    @Override
    public void parse(String fileName) {
        // 구문 분석작업을 수행하는 코드를 적는다.
        System.out.println(fileName + "- XML parsing completed.");
    }
}

class HTMLParser implements Parseable {
    @Override
    public void parse(String fileName) {
        // 구문 분석작업을 수행하는 코드를 적는다.
        System.out.println(fileName + "-HTML parsing completed.");
    }
}

public class ParserTest {
    public static void main(String[] args) {
        Parseable parser = ParseManager.getParser("XML");
        parser.parse("document.xml");
        parser = ParseManager.getParser("HTML");
        parser.parse("document2.html");
    }
}
