import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "restau"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    //javaJdbc,
    //javaEbean,
    
    "me.prettyprint" % "hector-core" % "1.0-5",
    "org.codehaus.jackson" % "jackson-xc" % "1.9.2"
    
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here
      resolvers += "mc" at "http://central.maven.org/maven2",
      
      //resolvers += "mycompany-releases" at "http://mycompany.com/nexus/content/repositories/mycompany_releases",
      //resolvers += "mycompany-snapshots" at "http://mycompany.com/nexus/content/repositories/mycompany_snapshots",
      //resolvers += "thirdparty" at "http://mycompany.com/nexus/content/repositories/thirdparty",
      //resolvers += "public" at "http://mycompany.com:8081/nexus/content/groups/public",
      //resolvers += "public-snapshots" at "http://mycompany:8081/nexus/content/groups/public-snapshots",
      //resolvers += "nexus" at "http://mycompany.com:8080/",
      
      //resolvers +=  "Local Jenkins Maven Repository" at "file://"+"/var/lib/jenkins"+"/.m2/repository",      

      resolvers +=  "Local Maven Repository" at "file://"+"/Users/sgandh1"+"/.m2/repository"

  )

}
