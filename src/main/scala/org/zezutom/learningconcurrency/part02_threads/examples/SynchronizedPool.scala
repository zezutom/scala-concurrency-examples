package org.zezutom.learningconcurrency.part02_threads.examples

import org.zezutom.learningconcurrency._

import scala.collection.mutable

/**
 *  The final implementation of a thread pool. It makes use
 *  of a daemon thread, but prevents it from "busy-waiting"
 *  by the means of a guarded block.
 *
 *  @see org.zezutom.learningconcurrency.part02_threads.examples.SynchronizedBadPool
 *  @see org.zezutom.learningconcurrency.part02_threads.examples.SynchronizedGuardedBlocks
 */
object SynchronizedPool extends App {
  private val tasks = mutable.Queue[() => Unit]()

  object Worker extends Thread {
    setDaemon(true)
    def poll() = tasks.synchronized {
      while (tasks.isEmpty) tasks.wait()
      tasks.dequeue()
    }
    override def run() = while(true) {
      val task = poll()
      task()
    }
  }
  Worker.start()

  def asynchronous(body: => Unit) = tasks.synchronized {
    tasks.enqueue(() => body)
    tasks.notify()
  }
  asynchronous { log("Hello") }
  asynchronous { log(" world!") }
  Thread.sleep(5000)
}
