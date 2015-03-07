package org.zezutom.learningconcurrency.part02_threads.examples
import org.zezutom.learningconcurrency.log
import org.zezutom.learningconcurrency.part02_threads.thread

/**
 * This implementation of a thread pool solves the "busy-waiting" problem present
 * in the SynchronizedBadPool.
 *
 * @see org.zezutom.learningconcurrency.part02_threads.examples.SynchronizedBadPool
 */
object SynchronizedGuardedBlocks extends App {
  // Threads use the monitor from a fresh lock object (AnyRef maps to java.lang.Object)
  val lock = new AnyRef
  var message: Option[String] = None
  val greeter = thread {
    // synchronized + while loop = guarded block
    lock.synchronized {
      // Let's release the lock, if there is no message. The main thread now
      // has a chance to acquire the lock and send a message.
      // The loop is there to guard against "spurious wakeups". That's the situation
      // when JVM can wake up a thread that called wait() even though there isn't
      // a corresponding notify().
      while (message == None) lock.wait()
      log(message.get)
    }
  }
  lock.synchronized {
    message = Some("Hello!")
    // Message sent, let's notify the greeter. The greeter wakes up, acquires the lock
    // and handles the message.
    lock.notify()
  }
  greeter.join()
}
