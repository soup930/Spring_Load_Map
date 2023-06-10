package lamdastream;


@FunctionalInterface
interface MyFunction {
    void myMethod();
}
public class LambdaEx2 {

    public static void main(String[] args) {
        Myfunction f = () -> {};
        Object obj = (Myfunction) (() -> {});
        String str = ( (Object) (MyFunction) (() -> {}) ).toString();

        System.out.println(f);
        System.out.println(obj);
        System.out.println(str);

        System.out.println((MyFunction) (() -> {}));
        System.out.println(((Object) (MyFunction) (() -> {})).toString());
    }
}
