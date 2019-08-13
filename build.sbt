val scalaVer = "2.11.11-bin-typelevel-4"

lazy val root =
  (project in file(".")).
    settings(
      name := "scalacheck-examples",
      organization := "net.sfdc.ljr",
      version := "0.1.0-SNAPSHOT",
      scalaOrganization := "org.typelevel",
      scalaVersion := scalaVer,
      scalacOptions ++= Seq(
        "-deprecation"
        , "-explaintypes"
        , "-feature"
        , "-unchecked"
        , "-Ypartial-unification"
      ),
      libraryDependencies ++= Seq(
        "org.scalatest" %% "scalatest" % "3.0.8" % Test,
        "org.scalacheck" %% "scalacheck" % "1.14.0" % Test
      )
    )

