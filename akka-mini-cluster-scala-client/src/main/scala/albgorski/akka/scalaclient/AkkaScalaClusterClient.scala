package albgorski.akka.scalaclient

import akka.actor.ActorSystem
import akka.contrib.pattern.ClusterClient
import com.typesafe.config.ConfigFactory
import albgorski.akka.protocol.{RequestHello, RequestHelloScalaVersion}

object AkkaScalaClusterClient {
  def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load()
    val system = ActorSystem("ScalaClusterClientSystem", config)

    val initialContacts = Set(
      system.actorSelection("akka.tcp://ClusterSystem@127.0.0.1:2551/user/receptionist"),
      system.actorSelection("akka.tcp://ClusterSystem@127.0.0.1:2552/user/receptionist"))
    val c = system.actorOf(ClusterClient.props(initialContacts))

    c ! ClusterClient.Send("/user/greeter", RequestHello("scala ClusterClient.Send"), localAffinity = true)
    c ! ClusterClient.SendToAll("/user/greeter", RequestHelloScalaVersion("scala ClusterClient.SendToAll", 1975))
  }
}
