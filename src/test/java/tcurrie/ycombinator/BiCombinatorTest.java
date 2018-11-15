package tcurrie.ycombinator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BiCombinatorTest {
    @Rule public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldNotCreate() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        thrown.expect(InvocationTargetException.class);
        thrown.expectCause(Matchers.isA(UnsupportedOperationException.class));
        final Constructor c = Y.BiCombinator.class.getDeclaredConstructor();
        c.setAccessible(true);
        c.newInstance();
    }

    @Test
    public void shouldReturnNullFromEndPoint() {
        final Y.Bi<String,String,String> m = Y.BiCombinator.of(r->(a,b)->null);
        assertThat(m.apply("asdf", "qwert"), nullValue(String.class));
    }

    @Test
    public void shouldReturnConstantFromEndPoint() {
        final Y.Bi<String,String,String> m = Y.BiCombinator.of(r->(a,b)->"qed");
        assertThat(m.apply("asdf", "qwert"), is("qed"));
    }

    @Test
    public void shouldReturnIdentityFromEndPoint() {
        final Y.Bi<String,String,String> m = Y.BiCombinator.of(r->(a,b)->a+b);
        assertThat(m.apply("asdf", "qwert"), is("asdfqwert"));
    }

    @Test
    public void shouldRecurseUntilConditionMet() {
        final Y.Bi<String,String,String> m = Y.BiCombinator.of(r->(a,b)->a.length()>10?a:r.apply(a+b, b+a));
        assertThat(m.apply("a", "b"), is("abbabaabbaababba"));
    }

    @Test
    public void shouldRecurseAndReturnDifferentType() {
        final Y.Bi<String,String,Integer> m = Y.BiCombinator.of(r->(a,b)->a.length()>10?a.hashCode():r.apply(a+b, b+a));
        assertThat(m.apply("a", "b"), is("abbabaabbaababba".hashCode()));
    }
}
