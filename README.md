# ycombinator [![Build Status](https://travis-ci.org/tcurrie/ycombinator.svg?branch=master)](https://travis-ci.org/tcurrie/ycombinator) [![Coverage Status](https://img.shields.io/coveralls/github/tcurrie/ycombinator.svg)](https://coveralls.io/github/tcurrie/ycombinator?branch=master) [![](https://jitpack.io/v/tcurrie/ycombinator.svg)](https://jitpack.io/#tcurrie/ycombinator)



Simple ycombinator factory to allow construction of a recursive function through composition.

For example:

```
final Function<Integer, BigInteger> fib = Y.Combinator.of(y -> index -> {
            if (index == 0) return BigInteger.ZERO;
            if (index == 1) return BigInteger.ONE;
            return y.apply(index - 2).add(y.apply(index - 1));
        });
```        
