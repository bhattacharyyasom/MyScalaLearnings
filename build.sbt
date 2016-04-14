name := "MyScalaLearnings"

mainClass in Compile := Some("Main")

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.scalaz"                 %% "scalaz-core"          % "7.2.0"
)

fork in run := true