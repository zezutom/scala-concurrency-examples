package org.zezutom.learningconcurrency.part02_threads.examples

object MainThread extends App {
  val t: Thread = Thread.currentThread
  println(s"I am the thread ${t.getName}")
}
