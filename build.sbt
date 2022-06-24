lazy val root = project
  .in(file("."))
  .settings(
    name := "Scoredle",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := Versions.scala3,
    libraryDependencies ++= Dependencies.munit
  )
