package albgorski.akka.javaclient;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.contrib.pattern.ClusterClient;
import albgorski.akka.protocol.GreeterProtocol;
import albgorski.akka.protocol.RequestHelloScalaVersion;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.duration.Duration;

import java.util.HashSet;
import java.util.Set;

public class AkkaJavaClusterClient {

    public static void main(String[] args) throws InterruptedException {
        Config config = ConfigFactory.load("akka-application");
        ActorSystem system = ActorSystem.create("JavaClusterClientSystem", config);

        Set<ActorSelection> initialContacts = new HashSet<>();
        initialContacts.add(system.actorSelection("akka.tcp://ClusterSystem@127.0.0.1:2551/user/receptionist"));
        initialContacts.add(system.actorSelection("akka.tcp://ClusterSystem@127.0.0.1:2552/user/receptionist"));

        ActorRef clusterClient = system.actorOf(ClusterClient.defaultProps(initialContacts), "clusterClient");

        clusterClient.tell(new ClusterClient.Send("/user/greeter", new GreeterProtocol.RequestHello("java ClusterClient.Send"), true), clusterClient);

        clusterClient.tell(new ClusterClient.SendToAll("/user/greeter", new RequestHelloScalaVersion("java ClusterClient.SendToAll", 123)), ActorRef.noSender());

        system.scheduler().schedule(
                Duration.Zero(),
                Duration.create(2, "seconds"),
                clusterClient,
                new ClusterClient.Send("/user/greeter", new RequestHelloScalaVersion("java ClusterClient.Send with scheduler", 456)),
                system.dispatcher(),
                ActorRef.noSender()
        );
    }
}
