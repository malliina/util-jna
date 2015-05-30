import com.mle.sbtutils.SbtProjects
import sbt._
import sbt.Keys._

/**
 * A scala build file template.
 */
object TemplateBuild extends Build {

  lazy val jnaProject = SbtProjects.testableProject("util-jna").settings(projectSettings: _*)

  lazy val projectSettings = Seq(
    version := "0.0.1",
    scalaVersion := "2.11.6",
    fork in Test := true,
    libraryDependencies ++= Seq(
      "com.github.malliina" %% "util-base" % "0.7.0",
      "net.java.dev.jna" % "jna" % "4.1.0",
      "net.java.dev.jna" % "jna-platform" % "4.1.0")
  )
}
