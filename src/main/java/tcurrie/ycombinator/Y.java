package tcurrie.ycombinator;

import java.util.function.BiFunction;
import java.util.function.Function;

public interface Y<U, V> extends Function<U, V> {
    class Combinator {
        private Combinator() {
            throw new UnsupportedOperationException("Do not create.");
        }

        public static <U, V> Y<U, V> of(final Y<Y<U, V>, Y<U, V>> r) {
            final T<U, V> uvt = t -> t.apply(t);
            return uvt.apply(t -> r.apply(v -> t.apply(t).apply(v)));
        }
        private interface T<U, V> extends Function<T<U, V>, Y<U, V>> {
        }


    }

    interface Bi<T, U, R> extends BiFunction<T, U, R> {
    }

    class BiCombinator {
        private BiCombinator() {
            throw new UnsupportedOperationException("Do not create.");
        }

        public static <T, U, R> Y.Bi<T, U, R> of(final Y<Y.Bi<T, U, R>, Y.Bi<T, U, R>> r) {
            final Y.BiCombinator.T2<T, U, R> uvt = t -> t.apply(t);
            return uvt.apply(t -> r.apply((u, v) -> t.apply(t).apply(u, v)));
        }
        private interface T2<T, U, R> extends Function<Y.BiCombinator.T2<T, U, R>, Y.Bi<T, U, R>> {
        }
    }
}
