package albgorski.akka.cluster

import akka.actor.{Actor, ActorLogging}
import albgorski.akka.protocol.{RequestHello, RequestHelloScalaVersion}

class Greeter extends Actor with ActorLogging {

  def receive = startReceive

  def startReceive: Receive = {
    case RequestHello(from) =>
      log.info(s">>> RequestHello $from")
    case RequestHelloScalaVersion(from, year) =>
      log.info(s">>> RequestHelloScalaVersion $from, $year")
  }

}