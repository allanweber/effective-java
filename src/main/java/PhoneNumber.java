import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.Comparator;
import java.util.Formattable;
import java.util.Formatter;

import static com.google.common.base.Preconditions.checkArgument;

public class PhoneNumber implements Formattable, Comparable<PhoneNumber> {

    private static final Comparator<PhoneNumber> PHONE_NUMBER_COMPARATOR = Comparator.comparingInt((PhoneNumber p) -> p.areaCode).thenComparingInt(p -> p.number);

    private final int areaCode;

    private final int number;
    private final int hash;

    private PhoneNumber(int areaCode, int number) {
        this.areaCode = areaCode;
        this.number = number;
        this.hash = Objects.hashCode(this.areaCode, this.number);
    }

    public static PhoneNumber of(int areaCode, int number) {
        checkArgument(areaCode > 1);
        checkArgument(number > 1000);
        return new PhoneNumber(areaCode, number);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof PhoneNumber) {
            PhoneNumber that = (PhoneNumber) obj;
            return areaCode == that.areaCode &&
                    number == that.number;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this.hash;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("areadCode", areaCode)
                .add("number", number)
                .toString();
    }

    public void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format("%d-%d", areaCode, number);
    }

    public int compareTo(PhoneNumber o) {

        return PHONE_NUMBER_COMPARATOR
                .compare(this, o);

        // Apos java 8, --desempenho, criando comparators mais de uma vez
//        return Comparator.comparingInt((PhoneNumber p) -> p.areaCode)
//                .thenComparingInt(p -> p.number)
//                .compare(this, o);

        //short circuit - pre java 8
//        return ComparisonChain.start()
//                .compare(this.areaCode, o.areaCode)
//                .compare(this.number, o.number)
//                .result();
    }

    public static void main(String[] args) {
        System.out.println(PhoneNumber.of(51, 993335326));

        System.out.printf("%s", PhoneNumber.of(51, 993335326));
    }


}
