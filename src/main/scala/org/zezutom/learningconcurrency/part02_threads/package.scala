package org.zezutom.learningconcurrency

package object part02_threads {
  // A template for starting new threads
  def thread(body: => Unit): Thread = {
    val t = new Thread {
      override def run() = body
    }
    t.start()
    t
  }
}
