package org.zezutom.learningconcurrency.part01_primer.examples

/**
 * Various kinds of loops and data transformations
 */

// #1 basic for loop
object ForLoop extends App {
  print("0 until 10: ")
  for (i <- 0 until 10) print(s"$i ")
}

// #2 for each
object ForEach extends App {
  print("foreach 0 until 10: ")
  (0 until 10).foreach(i => print(s"$i "))
}

/**
 * For comprehensions are used to transform data
 */

// #3 Turn all numbers into negatives
object ForNegative extends App {
  val negatives = for (i <- 1 to 10) yield -i
  println(negatives)
}

/**
 * Transformations can also be done by using the map function
 */

// #4 Turn all numbers into negatives
object MapNegatives extends App {
  val negatives = (1 to 10) map(i => -1 * i)
  println(negatives)
}

/**
 * It is also possible to transform data from multiple inputs
 */

// #5 Create pairs of integers
object ForPairs extends App {
  val pairs = for (x <- 0 until 4; y <- 0 until 4) yield (x, y)
  println(pairs)
}

// #6 Identical result can be achieved by using flatMap
object FlatMapPairs extends App {
  val pairs = (0 until 4).flatMap(x => (0 until 4).map(y => (x, y)))
}

/**
 * Sequences, maps and sets are common kinds of collections
 */

// #7 A string sequence
object StringSeq extends App {
  val messages: Seq[String] = Seq("Hello", "world", "!")
  println(messages)
}

/**
 * String interpolation allows to work with dynamic texts in a convenient way
 */

// #8 Let's print out some variables
object MagicNumber extends App {
  val magic = 7
  println(s"My magic number is $magic, which makes me ${magic * 2} times more excited!")
}

/**
 * Pattern matching is like a switch statement on steriods
 */

// #9 Search values by key
object Successors extends App {
  val successors = Map(1 -> 2, 2 -> 3, 3 -> 4)
  successors.get(2) match {
    case Some(n) => println(s"Successor is $n")
    case None => println("Could not find a successor")
  }
}

/**
 * Operator overloading: In Scala, most operators can be overloaded.
 */

// #10 Change your position by using a '+' operator
class Position(val x: Int, val y: Int) {
  def +(that: Position) = new Position(x + that.x, y + that.y)
  override def toString = s"($x, $y)"
}
object MoveNow extends App {
  val a = new Position(0, 0)
  val b = a + (new Position(10, 10))
  println(s"Moved from $a to $b")
}
