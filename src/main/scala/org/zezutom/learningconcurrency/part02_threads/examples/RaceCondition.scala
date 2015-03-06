package org.zezutom.learningconcurrency.part02_threads.examples
import org.zezutom.learningconcurrency.part02_threads.thread
import org.zezutom.learningconcurrency.log

/**
 * Assigning unique identifiers
 *
 * A set of threads must concurrently choose numbers such that no two
 * threads produce the same number.
 *
 * This is an example of an incorrect approach resulting into a race condition.
 */
object RaceCondition extends App {
  var uidCount = 0L

  def getUniqueId():Long = {
    val freshUid = uidCount + 1
    uidCount = freshUid
    freshUid
  }

  // Problem: it's not atomic (unprotected writes)
  def printUniqueIds(n: Int) {
    val uids = for (i <- 0 until n) yield getUniqueId()
    log(s"Generated uids: $uids")
  }


  def printSyncUniqueIds(n: Int) = this.synchronized {
    val uids = for (i <- 0 until n) yield getUniqueId()
    log(s"Generated uids: $uids")
  }

  println("Race Condition\n" + "=" * 15)
  // There might be shared uids between the main thread and t
  // The output depends on the timing of the thread execution
  //
  // Example:
  // Thread-0: Generated uids: Vector(1, 2, 4, 6, 8)
  // main: Generated uids: Vector(1, 3, 5, 7, 9)
  val t = thread { printUniqueIds(5) }
  printUniqueIds(5)
  t.join()

  println("\nSynchronized \n" + "=" * 15)
  val t1 = thread { printSyncUniqueIds(5) }
  printSyncUniqueIds(5)
  t1.join()

}
