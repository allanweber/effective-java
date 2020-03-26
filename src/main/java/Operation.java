import java.util.Objects;
import java.util.function.IntBinaryOperator;

public enum Operation {

    ADD ((x,y) -> x + y),
    SUBTRACT((x,y) -> x - y);

    private final IntBinaryOperator operator;

    Operation(IntBinaryOperator operator) {
        this.operator = operator;
    }

    public int compute(int x, int y){
        return operator.applyAsInt(x, y);
    }

    public static void main(String[] args) {
        System.out.println(Operation.ADD.compute(1,2));
        System.out.println(Operation.SUBTRACT.compute(2,1));
    }


    // Pre java 8
//    ADD {
//        @Override
//        public int compute(int x, int y) {
//            return x + y;
//        }
//    },
//    SUBTRACT {
//        @Override
//        public int compute(int x, int y) {
//            return x - y;
//        }
//    };
//
//    public abstract int compute(int x, int y);
}
