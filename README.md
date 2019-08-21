# ScalaCheck by Example

This repository can be found at:
```
git@git.soma.salesforce.com:lripple/scalacheck-examples.git
```

ScalaCheck is a property tester that uses random data generation to allow you to
check that properties about your code hold in the precence of different kinds of
data.  (E.g. List reverse does not grow or shrink the list.)

The library provides a DSL to generate random data and, because it is implementing
a State monad, the individual combinators can be composed to create larger ones.

## Built In Combinators

* const[T]  
Generate a constant

* fail[T]  
Fail to generate something
---
* choose[T](min, max)  
Implemented for T <: AnyVal

* oneOf[T](xs: Seq[T])  
Pick a random element

* oneOf[T](g0: Gen[T], g1: Gen[T], gs: Gen[T]*)  
Pick a random Gen

* someOf[T](ts: Iterable[T])
Pick randomly from a collection

* atLeastOneOf(ts: Iterable[T])
Pick randomly, but at least one, from a collection

* pick(n: Int, ts: Iterable[T])
Pick n random elements from a collection
---
* option[T](g: Gen[T]): Gen[Option[T]]  
Generate Options of T

* some[T](g: Gen[T]): Gen[Option[T]]  
Generate nonEmpty Option of T
---
* frequency[T]((Int, T)*)
Pick an item using a weighted distribution  
E.g., frequency(1 -> false, 9 -> true)

* frequency[T]((Int, Gen[T])*)  
Generate an item using a weighted distribution
---
* listOf[T](g: Gen[T])  
Random List[T]

* nonEmptyListOf[T](g: Gen[T])
Random nonEmpty List[T]

* listOfN[T](n: Int, g: Gen[T])
Random list[T] of given length
---
* mapOf(g: Gen[(K,V)])  
Random Map[K,V]

* nonEmptyMap(g: Gen[(K,V)])  
Random nonEmpty Map[K,V]

* mapOfN(n: Int, g: Gen[(K,V)])  
Random Map[K,V] of given size

## Built In Generators
### Chars

* numChar  
Generates a numerical character

* alphaUpperChar  
Generates an upper-case alpha character

* alphaLowerChar  
Generates a lower-case alpha character

* alphaChar  
Generates an alpha character

* alphaNumChar  
Generates an alphanumerical character

* asciiChar  
Generates a ASCII character, with extra weighting for printable characters

* asciiPrintableChar  
Generates a ASCII printable character

### Strings

* identifier  
Generates a string that starts with a lower-case alpha character,
and only contains alphanumerical characters

* numStr  
Generates a string of digits

* alphaUpperStr  
Generates a string of upper-case alpha characters

* alphaLowerStr  
Generates a string of lower-case alpha characters

* alphaStr  
Generates a string of alpha characters

* alphaNumStr  
Generates a string of alphanumerical characters

* asciiStr  
Generates a string of ASCII characters, with extra weighting for
printable characters

* asciiPrintableStr  
Generates a string of ASCII printable characters
  
### Numbers (T <: Numeric)

* posNum[T]  
Generates positive numbers of uniform distribution, with an
upper bound of the generation size parameter.

* negNum[T]  
Generates negative numbers of uniform distribution, with an
lower bound of the negated generation size parameter.

* chooseNum[T]  
Generates numbers within the given inclusive range, with
extra weight on zero, +/- unity, both extremities, and any special
numbers provided. The special numbers must lie within the given range,
otherwise they won't be included.

### Misc

* uuid  
Generates a version 4 (random) UUID.

