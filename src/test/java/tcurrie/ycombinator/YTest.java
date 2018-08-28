package tcurrie.ycombinator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.math.BigInteger;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.junit.Test;

public class YTest {

    @Test
    public void shouldReturnNullFromEndPoint() {
        final Y<String,String> m = Y.Combinator.of(r->input->null);
        assertThat(m.apply("asdf"), nullValue(String.class));
    }

    @Test
    public void shouldReturnConstantFromEndPoint() {
        final Y<String,String> m = Y.Combinator.of(r->input->"qed");
        assertThat(m.apply("asdf"), is("qed"));
    }

    @Test
    public void shouldReturnIdentityFromEndPoint() {
        final Y<String,String> m = Y.Combinator.of(r->input->input);
        assertThat(m.apply("asdf"), is("asdf"));
    }

    @Test
    public void shouldRecurseUntilConditionMet() {
        final Y<Integer,Integer> m = Y.Combinator.of(r->i->i>9?i:r.apply(i+1));
        assertThat(m.apply(1), is(10));
    }

    @Test
    public void shouldRecurseAndReturnDifferentType() {
        final Y<Integer,String> m = Y.Combinator.of(r->i->i>9?String.valueOf(i):r.apply(i+1));
        assertThat(m.apply(1), is("10"));
    }

    @Test
    public void shouldCalculateFibonacciUsingNaiveRecursion() {
        final Function<Integer, BigInteger> fib = Y.Combinator.of(y -> index -> {
            if (index == 0) return BigInteger.ZERO;
            if (index == 1) return BigInteger.ONE;
            return y.apply(index - 2).add(y.apply(index - 1));
        });

        final BiConsumer<Integer, String> validate = (index, expected)->assertThat(fib.apply(index), is(new BigInteger(expected)));
        validate.accept(0, "0");
        validate.accept(1, "1");
        validate.accept(2, "1");
        validate.accept(3, "2");
        validate.accept(4, "3");
        validate.accept(5, "5");
        validate.accept(6, "8");
        validate.accept(7, "13");
        validate.accept(20, "6765");
    }

}
