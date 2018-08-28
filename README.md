# ycombinator

Simple ycombinator factory to allow construction of a recursive function through composition.

For example:

```
final Function<Integer, BigInteger> fib = Y.Combinator.of(y -> index -> {
            if (index == 0) return BigInteger.ZERO;
            if (index == 1) return BigInteger.ONE;
            return y.apply(index - 2).add(y.apply(index - 1));
        });
```        
