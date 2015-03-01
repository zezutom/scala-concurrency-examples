package org.zezutom.learningconcurrency.part01_primer.examples

/**
 * Traits provide both an interface and common implementation
 */
trait Logging {
  def log(s: String): Unit
  def warn(s: String) = log(s"WARN: $s")
  def error(s: String) = log(s"ERROR: $s")
}

class PrintLogging extends Logging{
  override def log(s: String): Unit = println(s)
}

object PrintLoggingTest extends App {
  val logger = new PrintLogging
  logger.log("For the mockery of it!")
  logger.warn("I dare you!")
  logger.error("Now it broke:(")
}
