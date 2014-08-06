name := """calculator"""

version := "1.0"

scalaVersion := "2.11.2"

libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.11.2"

libraryDependencies += "org.parboiled" %% "parboiled" % "2.0.0"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.1" % "test"

scalacOptions ++= Seq("-deprecation")