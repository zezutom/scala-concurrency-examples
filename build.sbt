name := "concurrency-examples"

version := "1.0"

scalaVersion := "2.11.2"

resolvers ++= Seq(
  "Sonatype OSS Snapshots" at
    "https://oss.sonatype.org/content/repositories/snapshots",
  "Sonatype OSS Releases" at
    "https://oss.sonatype.org/content/repositories/releases",
  "Typesafe Repository" at
    "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies += "commons-io" % "commons-io" % "2.4"

// ensure all examples run in the same JVM as SBT does,
// so that concurrent computations always complete
fork := false