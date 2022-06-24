import sbt._

object Versions {
  val scala3 = "3.1.2"
  val munit = "0.7.29"
}
object Dependencies {
  val munit = Seq(
      "org.scalameta" %% "munit" % Versions.munit % Test,
      "org.scalameta" %% "munit-scalacheck" % Versions.munit % Test
  )
}
