package org.zezutom.learningconcurrency.part01_primer.exercises

/**
 * Modify the Pair class from this chapter so that it can be used in a
 * pattern match.
 */
class Pair[P, Q](val first: P, val second: Q) {
  override def toString(): String = s"($first, $second)"
}

object Ex04PatterMatching extends App {

  def evenOrOkay(p:Pair[Int, String]): Boolean = p match {
    case x if x.first % 2 == 0 || x.second.eq("OK")  => true
    case _ => false
  }

  // Proof
  val anEven = new Pair(2, "two")
  val anOdd = new Pair(3, "three")
  val anOddButOkay = new Pair(3, "OK")

  println(s"evenOrOkay($anEven): ${evenOrOkay(anEven)}")
  println(s"evenOrOkay($anOdd): ${evenOrOkay(anOdd)}")
  println(s"evenOrOkay($anOddButOkay): ${evenOrOkay(anOddButOkay)}")
}
