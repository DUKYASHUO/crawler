name := """reptile"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play"         % "1.5.1" % Test,
  "mysql" % "mysql-connector-java"                         % "5.1.39",
  "org.scalikejdbc" %% "scalikejdbc"                       % "3.0.0",
  "org.scalikejdbc" %% "scalikejdbc-config"                % "3.0.0",
  "org.scalikejdbc" % "scalikejdbc-play-initializer_2.10"  % "2.4.5",
  "net.ruippeixotog" %% "scala-scraper"                    % "2.0.0-RC2"
)

