package org.zezutom.learningconcurrency.part02_threads.examples
import org.zezutom.learningconcurrency.part02_threads.thread

object ThreadSharedStateAccessReordering extends App {
  for (i <- 0 until 10000) {
    var (a, b) = (false, false)
    var (x, y) = (-1, -1)

    val t1 = thread {
      a = true
      y = if (b) 0 else 1
    }

    val t2 = thread {
      b = true
      x = if (a) 0 else 1
    }

    t1.join()
    t2.join()

    // Threads are supposed to take turns (you wish!)
    assert(!(x == 1 && y == 1), s"x = $x, y = $y")
  }
}
