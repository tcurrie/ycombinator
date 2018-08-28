package tcurrie.ycombinator;

import java.util.function.Function;

public interface Y<U, V> extends Function<U, V> {
    class Combinator {
        public static <U, V> Y<U, V> of(final Y<Y<U, V>, Y<U, V>> r) {
            final T<U, V> uvt = t -> t.apply(t);
            return uvt.apply(t -> r.apply(v -> t.apply(t).apply(v)));
        }
        private interface T<U, V> extends Function<T<U, V>, Y<U, V>> {
        }
    }
}
