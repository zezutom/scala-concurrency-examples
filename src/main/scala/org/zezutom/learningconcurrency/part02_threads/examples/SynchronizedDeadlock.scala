package org.zezutom.learningconcurrency.part02_threads.examples
import org.zezutom.learningconcurrency.part02_threads.thread
import org.zezutom.learningconcurrency.log
/**
 * Demos a dead-lock caused by two synchronized object waiting
 * for each others' monitor to be released.
 */
object SynchronizedDeadlock extends App {
  import SynchronizedNesting.Account

  /**
   * Transfers funds from one account to another.
   *
   * Creates a setup for a deadlock:
   *
   * Note how both a and b require each others' resources
   * without releasing their own.
   *
   * @param a   account A
   * @param b   account B
   * @param n   money
   */
  def send(a: Account, b: Account, n: Int) = a.synchronized {
    b.synchronized {
      a.money -= n
      b.money += n
    }
  }
  val a = new Account("Jack", 1000)
  val b = new Account("Jill", 2000)
  val t1 = thread { for (i<- 0 until 100) send(a, b, 1) }
  val t2 = thread { for (i<- 0 until 100) send(b, a, 1) }
  t1.join(); t2.join()
  log(s"a = ${a.money}, b = ${b.money}")
}
