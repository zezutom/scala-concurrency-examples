package org.zezutom.learningconcurrency.part01_primer.examples

/**
 * A class is exactly what you would expect. Params replace an explicit constructor.
 */
class Printer(val greeting: String) {
  def printMessage(): Unit = println(s"$greeting!")
  def printNumber(x: Int): Unit = println(s"Number: $x")
}

/**
 * A singleton object
 */
object PrinterTest extends App {
  val printy = new Printer("Hi")
  printy.printMessage()
  printy.printNumber(5)
}
