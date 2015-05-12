import com.typesafe.sbt.SbtMultiJvm

val akkaVersion = "2.3.10"

val project = Project(
  id = "akka-mini-cluster",
  base = file("."),
  settings = Project.defaultSettings ++ SbtMultiJvm.multiJvmSettings ++ Seq(
    name := """akka-mini-cluster""",
    scalaVersion := "2.11.4",
    scalacOptions in Compile ++= Seq("-encoding", "UTF-8", "-target:jvm-1.6", "-deprecation", "-feature", "-unchecked", "-Xlog-reflective-calls", "-Xlint"),
    javacOptions in Compile ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint:unchecked", "-Xlint:deprecation"),
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-cluster"                 % akkaVersion,
      "com.typesafe.akka" %% "akka-contrib"                 % akkaVersion,
      "albgorski"         %% "akka-mini-cluster-protocol"   % "1.0"
    ),
    javaOptions in run ++= Seq(
      "-Xms256m", "-Xmx256m"),
    Keys.fork in run := true,
    mainClass in(Compile, run) := Some("albgorski.akka.cluster.StartClusterApp"),
    resolvers ++= Seq(
      Resolver.mavenLocal
    )
  )
)

fork in run := true