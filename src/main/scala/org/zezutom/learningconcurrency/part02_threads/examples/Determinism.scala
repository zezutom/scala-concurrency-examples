package org.zezutom.learningconcurrency.part02_threads.examples
import org.zezutom.learningconcurrency.part02_threads.thread
import org.zezutom.learningconcurrency.log

object Determinism extends App {

  // Deterministic program - the log statements will always appear in the same expected order
  println("--- Deterministic ---")
  val t = thread {
    Thread.sleep(1000)
    log("New thread running")
    Thread.sleep(1000)
    log("Still running")
    Thread.sleep(1000)
    log("Completed")
  }
  t.join()
  log("New thread joined")

  // Non-deterministic program - no guarantee with regard to the order of the log statements
  println("--- Non Deterministic ---")
  val t1 = thread {
    log("New thread running")
  }
  log("First item")
  log("Second item")
  log("Third item")
  t1.join()
  log("New thread joined")
}
