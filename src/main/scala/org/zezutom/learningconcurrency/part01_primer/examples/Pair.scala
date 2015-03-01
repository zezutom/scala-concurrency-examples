package org.zezutom.learningconcurrency.part01_primer.examples

/**
 * Type parameters
 */
class Pair[P, Q](val first: P, val second: Q) {
  override def toString(): String = s"($first, $second)"
}

object PairTest extends App {
  val pair = new Pair(5, "Hi!")
  println(s"My pair: $pair")
}
