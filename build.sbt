
lazy val main = "com.mailinvitation.app.QuickStartServerApp"
lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization    := "com.example",
      scalaVersion    := "2.11.8"
    )),
    name := "semantixspark",
    mainClass in (Compile, run) := Some(main),
    libraryDependencies ++= Seq(

      "org.apache.spark" %% "spark-sql" % "2.3.1",

      "org.scalatest"     %% "scalatest"            % "3.0.1"         % Test


    )
  )
