package com.mle.jna

import com.mle.util.TryOps

/**
 *
 * @author Michael
 */
object Hello {

  def main(args: Array[String]) {
    val monitor = Monitor()
    monitor.map(testMonitor).recoverNonFatal(onError)
  }

  def testMonitor(monitor: Monitor): Unit = {
    monitor.turnOff()
    Thread.sleep(5000)
    monitor.turnOn()
  }

  def onError(t: Throwable) = {
    println(s"Failure: ${t.getMessage}")
  }
}
