package org.zezutom.learningconcurrency.part01_primer.exercises

/**
 * Implement a check method, which takes a set of values of the type T and
 * a function of the type T => Boolean:
 * def check[T](xs: Seq[T])(pred: T => Boolean): Boolean = ???
 *
 * The method must return true if and only if the pred function returns true
 * for all the values in xs without throwing an exception. Use the check
 * method as follows:
 *
 * check(0 until 10)(40 / _ > 0)
 *
 * Hint:
 * The check method has a curried definition: instead of just one
 * parameter list, it has two of them. Curried definitions allow
 * a nicer syntax when calling the function, but are otherwise
 * semantically equivalent to single-parameter list definitions.
 */
object Ex03CheckMethod extends App {

  def check[T](xs: Seq[T])(pred: T => Boolean): Boolean = try {
    xs.map(x => pred(x)).reduce(_ && _)
  } catch {
    case e:Exception => false
  }

  // Proof
  println(s"check(0 until 10)(40 / _ > 0): ${check(0 until 10)(40 / _ > 0)}")
  println(s"check(1 until 10)(40 / _ > 0): ${check(1 until 10)(40 / _ > 0)}")
  println(s"check(-2 until 2)(_ >= 0): ${check(-2 until 2)(_ >= 0)}")
  println(s"check(0 until 2)(_ >= 0): ${check(0 until 2)(_ >= 0)}")

}
