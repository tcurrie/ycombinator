# ycombinator [![Build Status](https://travis-ci.org/tcurrie/ycombinator.svg?branch=master)](https://travis-ci.org/tcurrie/ycombinator) [![Coverage Status](https://coveralls.io/repos/github/tcurrie/ycombinator/badge.svg?branch=master)](https://coveralls.io/github/tcurrie/ycombinator?branch=master)

Simple ycombinator factory to allow construction of a recursive function through composition.

For example:

```
final Function<Integer, BigInteger> fib = Y.Combinator.of(y -> index -> {
            if (index == 0) return BigInteger.ZERO;
            if (index == 1) return BigInteger.ONE;
            return y.apply(index - 2).add(y.apply(index - 1));
        });
```        
