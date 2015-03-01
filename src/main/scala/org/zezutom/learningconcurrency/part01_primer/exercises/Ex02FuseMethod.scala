package org.zezutom.learningconcurrency.part01_primer.exercises

/**
 * Implement a fuse method with the following signature:
 * def fuse[A, B](a: Option[A], b: Option[B]): Option[(A, B)] = ???
 *
 * The resulting Option object should contain a tuple of values from the
 * Option objects a and b, given that both a and b are non-empty. Use
 * for-comprehensions.
 */
object Ex02FuseMethod extends App {
  def fuse[A, B](a: Option[A], b: Option[B]): Option[(A, B)] = for {
    aVal <- a
    bVal <- b
  } yield (aVal, bVal)

  // Proof
  val a = Option(1, 2, 3)
  val b = Option("one", "two", "three", "four")
  println(s"fuse of $a and $b: ${fuse(a, b)}")
}
