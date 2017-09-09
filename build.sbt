name := """run"""
organization := "run"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "com.typesafe.akka" %% "akka-actor" % "2.5.4",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.4" % Test,
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final"
)

libraryDependencies += "com.typesafe.akka" % "akka-camel_2.11" % "2.3.3"

libraryDependencies += "org.apache.camel" % "camel-activemq" % "1.1.0"
// https://mvnrepository.com/artifact/org.apache.activemq/activemq-core
libraryDependencies += "org.apache.activemq" % "activemq-core" % "5.0.0"
// https://mvnrepository.com/artifact/org.apache.activemq/activeio-core
libraryDependencies += "org.apache.activemq" % "activeio-core" % "3.1.4"

libraryDependencies += "com.lightbend.akka" %% "akka-stream-alpakka-jms" % "0.11"

libraryDependencies += "com.typesafe.play" %% "play-mailer" % "6.0.1"
// https://mvnrepository.com/artifact/javax.mail/mail
libraryDependencies += "javax.mail" % "mail" % "1.4.1"





