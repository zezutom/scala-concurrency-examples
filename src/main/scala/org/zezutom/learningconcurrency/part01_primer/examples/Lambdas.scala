package org.zezutom.learningconcurrency.part01_primer.examples

/**
 * First-class function objects, a.k.a. Lambdas
 */

// #1 Calculated constant values
object Turbo extends App {
  // lambda: a complete declaration
  val twice: Int => Int = (x: Int) => x * 2

  // lambda: parameter types can be skipped
  val triple: Int => Int = x => x * 3

  // lambda: provided the argument only appears once in the body, it can be replaced by an underscore
  val quadruple: Int => Int = _ * 4

  println("5 * 2 = " + twice(5))
  println("6 * 3 = " + triple(6))
  println("7 * 4 = " + quadruple(7))
}

// #2 An easy handling of blocks of code. The example below executes the provided block twice
object Repeater extends App {
  def runTwice(body: => Unit) = {
    body
    body
  }
  runTwice(println("I told you!"))
  runTwice { println("Hello?") }
}
