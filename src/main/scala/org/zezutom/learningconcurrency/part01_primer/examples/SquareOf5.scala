package org.zezutom.learningconcurrency.part01_primer.examples

object SquareOf5 extends App {
  def square(x: Int): Int = x * x
  val s = square(5)
  println(s"Result: $s")
}
