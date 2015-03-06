package org.zezutom.learningconcurrency.part02_threads.examples

import org.zezutom.learningconcurrency._
import org.zezutom.learningconcurrency.part02_threads._
import org.zezutom.learningconcurrency.part02_threads.examples.SynchronizedNesting.Account

/**
 * Shows how prevent a deadlock by establishing a total order
 * between resources when requiring them. This guarantees
 * that no set of threads will be kept cyclically waiting
 * for the resources they previously acquired.
 * 
 * @see org.zezutom.learningconcurrency.part02_threads.examples.SynchronizedDeadlock
 */
object SynchronizedDeadlockFree extends App {
  import RaceCondition.getUniqueId
  class Account(val name: String, var money: Int) {
    // Each account has a unique identifier
    val uid = getUniqueId()
  }
  // No synchronization, no dead-lock
  def send(a: Account, b: Account, n: Int): Unit = {
    def adjust(): Unit = {
      a.money -= n
      b.money += n
    }
    // Deadlocks are prevented by a strict ordering of nested synchronized blocks
    if (a.uid < b.uid) a.synchronized { b.synchronized { adjust() }}
    else b.synchronized { a.synchronized { adjust() }}
  }
  val a = new Account("Jack", 1000)
  val b = new Account("Jill", 2000)
  val t1 = thread { for (i<- 0 until 100) send(a, b, 1) }
  val t2 = thread { for (i<- 0 until 100) send(b, a, 1) }
  t1.join(); t2.join()
  log(s"a = ${a.money}, b = ${b.money}")
}
