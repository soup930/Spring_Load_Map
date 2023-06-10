package lamdastream;


@FunctionalInterface
interface Myfunction {
    void run();
}
public class LamndaEx1 {

    static void execute(Myfunction f) {
        // 매개변수의 타입이 Myfunction인 메서드
        f.run();
    }

    static Myfunction getMyFunction() {
        // 반환 타입이 MyFunction인 메서드
        Myfunction f = () -> System.out.println("f3.run()");
        return f;
    }

    public static void main(String[] args) {
        // 람다식으로 MyFunction의 run()을 구현
        Myfunction f1 = () -> System.out.println("f1.run()");

        Myfunction f2 = new Myfunction() {
            @Override
            public void run() {
                System.out.println("f2.run()");

            }
        };

        Myfunction f3 = getMyFunction();

        f1.run();
        f2.run();
        f3.run();

        execute(f1);
        execute(() -> System.out.println("run()"));
    }
}
