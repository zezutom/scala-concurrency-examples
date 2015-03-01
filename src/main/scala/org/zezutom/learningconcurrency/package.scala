package org.zezutom

package object learningconcurrency {
  /**
   * Application-wide logging
   * @param msg
   */
  def log(msg: String): Unit =
    println(s"${Thread.currentThread().getName}: $msg")
}
