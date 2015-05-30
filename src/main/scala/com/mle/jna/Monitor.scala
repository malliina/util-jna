package com.mle.jna

import com.sun.jna.Native
import com.sun.jna.platform.win32.WinDef.{LRESULT, LPARAM, HWND, WPARAM}
import com.sun.jna.platform.win32.{WinUser, User32, WinDef}
import com.sun.jna.win32.StdCallLibrary

import scala.util.Try

/**
 * @author mle
 */
class Monitor private {
  trait MyUser32 extends StdCallLibrary {
    def sendMessageA(paramHWND: HWND, paramInt: Int, paramWPARAM: WPARAM, paramLPARAM: LPARAM): LRESULT
    def sendMessageA(paramHWND: HWND, paramInt: Int, paramInt2: Int, paramLPARAM: LPARAM): LRESULT
  }
  object MyUser32 {
    val INSTANCE = Native.loadLibrary("user32", classOf[MyUser32]).asInstanceOf[MyUser32]
    val SC_MONITORPOWER = 0xF170
    val SC_MONITOR_OFF = 2
    val SC_MONITOR_ON = -1
  }

  val user32 = User32.INSTANCE.asInstanceOf[MyUser32]

  def turnOn(): Unit = {
    message(MyUser32.SC_MONITOR_ON)
  }
  def turnOff(): Unit = {
    message(MyUser32.SC_MONITOR_OFF)
  }

  def message(command: Int) = user32.sendMessageA(
    WinUser.HWND_BROADCAST,
    WinUser.WM_SYSCOMMAND,
    MyUser32.SC_MONITORPOWER,
    new LPARAM(command))
}
object Monitor {
  def apply(): Try[Monitor] = Try(new Monitor)
}
