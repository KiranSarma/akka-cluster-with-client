package albgorski.akka.protocol


case class RequestHelloScalaVersion(from: java.lang.String, year: Integer)

object RequestHello {
  import GreeterProtocol.RequestHello

  def apply(from: String) =
    new RequestHello(from)

  def unapply(user: RequestHello) =
    Some(user.from)

  implicit class RequestHelloScalaApi(user: RequestHello) {
  }

}