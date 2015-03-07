package org.zezutom.learningconcurrency.part02_threads.examples

import scala.collection.mutable
import org.zezutom.learningconcurrency.log

/**
 * There is a single thread (a worker) in this thread pool.
 * The worker constantly polls the task queue and executes
 * the tasks queued by other threads.
 *
 * While there is nothing wrong polling per se, the problem
 * stems from the fact that the worker runs as daemon. That's
 * necessary due to asynchronous nature of the whole setup
 * (client threads don't block waiting for their jobs to be executed).
 *
 * Having a daemon thread means that a JVM process won't stop
 * when the main thread terminates. That causes extra load on CPU
 * as the worker thread is busy-waiting.
 */
object SynchronizedBadPool extends App {
  private val tasks = mutable.Queue[() => Unit]()

  val worker = new Thread {
    def poll(): Option[() => Unit] = tasks.synchronized {
      if (tasks.nonEmpty) Some(tasks.dequeue()) else None
    }
    override def run() = while(true) poll() match {
      case Some(task) => task()
      case None =>
    }
  }
  worker.setName("Worker")
  worker.setDaemon(true)
  worker.start()

  def asynchronous(body: => Unit) = tasks.synchronized {
    tasks.enqueue(() => body)
  }
  asynchronous { log("Hello") }
  asynchronous { log(" world!") }
  Thread.sleep(5000)
}
