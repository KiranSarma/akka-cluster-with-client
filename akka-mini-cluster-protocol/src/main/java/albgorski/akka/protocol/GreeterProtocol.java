package albgorski.akka.protocol;

import java.io.Serializable;

public abstract class GreeterProtocol {

    public static final class RequestHello implements Serializable {
        public final String from;

        public RequestHello(String from) {
            this.from = from;
        }

        @Override
        public String toString() {
            return "RequestHello{" +
                    "from='" + from + '\'' +
                    '}';
        }
    }
}