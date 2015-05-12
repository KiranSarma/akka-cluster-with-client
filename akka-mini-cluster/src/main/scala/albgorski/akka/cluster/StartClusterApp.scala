package albgorski.akka.cluster

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.contrib.pattern.ClusterReceptionistExtension
import albgorski.akka.protocol.RequestHello
import com.typesafe.config.ConfigFactory

object StartClusterApp {
  def main(args: Array[String]): Unit = {
    if (args.isEmpty)
      startup(Seq("2551", "2552", "2553"))
    else
      startup(args)
  }

  def startup(ports: Seq[String]): Unit = {
    ports foreach { port =>
      val config = ConfigFactory.parseString("akka.remote.netty.tcp.port=" + port).
        withFallback(ConfigFactory.load())

      // Create an Akka system
      val system = ActorSystem("ClusterSystem", config)
      val greeter = system.actorOf(Props[Greeter], name = "greeter")
      // add receptionist extension
      ClusterReceptionistExtension(system).registerService(greeter)

      greeter.tell(RequestHello("SimpleCluster sends RequestHello"), ActorRef.noSender)
    }
  }

}

