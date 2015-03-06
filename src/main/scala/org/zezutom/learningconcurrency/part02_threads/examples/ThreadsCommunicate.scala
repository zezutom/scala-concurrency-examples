package org.zezutom.learningconcurrency.part02_threads.examples
import org.zezutom.learningconcurrency.part02_threads.thread
import org.zezutom.learningconcurrency.log

/**
 * All the writes to memory performed by the thread being joined
 * occur before the join call returns, and are visible to the thread
 * that called the join method.
 */
object ThreadsCommunicate extends App {
  var result: String = null
  val t = thread { result = "\nTitle\n" + "=" * 5 }
  t.join()
  log(result) // It never prints null since the main thread always waits for the thread 't' to finish
}
