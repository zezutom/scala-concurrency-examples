package org.zezutom.learningconcurrency.part02_threads.examples

/**
 * Nested synchronized statements
 *
 * Example:
 * - a hypothetical online banking system
 * - money transfers handled indirectly, by the means of a dedicated logTransfer method
 * - synchronization applies since the transfers are kept in a mutable ArrayBuffer
 * - notice how synchronized statements are nested (add -> logTransfer)
 */
object SynchronizedNesting extends App {
  import scala.collection._
  import org.zezutom.learningconcurrency.part02_threads.thread
  import org.zezutom.learningconcurrency.log

  private val transfers = mutable.ArrayBuffer[String]()

  def logTransfer(name: String, n: Int) = transfers.synchronized {
    transfers += s"transfer to account '$name' = $n"
  }

  def add(account: Account, n: Int) = account.synchronized {
    account.money += n

    // Another synchronized method being invoked from an (already) synchronized block
    if (n > 10) logTransfer(account.name, n)
  }

  class Account(val name: String, var money: Int)

  val jane = new Account("jane", 100)
  val john = new Account("john", 200)
  val t1 = thread { add(jane, 5) }
  val t2 = thread { add(john, 50) }
  val t3 = thread { add(jane, 70) }
  t1.join(); t2.join(); t3.join()
  log(s"----- transfers -----\n$transfers")
}
