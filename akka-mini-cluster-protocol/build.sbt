name := """akka-mini-cluster-protocol"""

organization := "albgorski"

version := "1.0"

scalaVersion := "2.11.4"

resolvers ++= Seq(
                  Resolver.mavenLocal
)

libraryDependencies ++= Seq()

publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository")))

fork in run := true