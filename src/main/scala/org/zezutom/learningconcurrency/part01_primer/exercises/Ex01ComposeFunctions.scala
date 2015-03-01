package org.zezutom.learningconcurrency.part01_primer.exercises

object Ex01ComposeFunctions extends App {

  /**
   * #1 Implement a compose method with the following signature:
   * def compose[A, B, C](g: B => C, f: A => B): A => C = ???
   *
   * This method must return a function h, which is the composition of the
   * functions f and g.
   */
  def compose[A, B, C](g: B => C, f: A => B): A => C = g compose f

  // Proof
  val add1 = (x: List[Int]) => x map(_ + 1)
  val square = (x: List[Int]) => x map(n => n * n)
  val add1ComposeSquare = compose(add1, square)
  val x = List(1, 2, 3)
  println(s"add1: ${add1(x)}")
  println(s"square: ${square(x)}")
  println(s"square and add1: ${add1ComposeSquare(x)}")

}
