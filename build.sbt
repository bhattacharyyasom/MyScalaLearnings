name := "MyScalaLearnings"

mainClass in Compile := Some("Main")

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.scalaz"                 %% "scalaz-core"          % "7.2.0",
  "org.scalactic"              %% "scalactic"            % "2.2.6" ,
  "org.scalatest"              %% "scalatest"            % "2.2.6"  % "test",
  "org.scalamock" %% "scalamock-scalatest-support" % "3.2.2" % "test"
)

fork in run := true