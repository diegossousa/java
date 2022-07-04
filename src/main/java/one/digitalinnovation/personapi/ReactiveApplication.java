package one.digitalinnovation.personapi;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.http.server.HttpServer;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

public class ReactiveApplication {

    private static final String HOST = "localhost";
    private static final int PORT = 8080;
    private static WebClient client;

    public void startReactorServer() {
        RouterFunction<ServerResponse> route = RouterFunctions.route(GET("/hello"), request -> ServerResponse.ok().body(fromObject("Hello reactive world!")));
        client = WebClient.builder().baseUrl("http://localhost:8080").build();

//        HttpServer server = HttpServer.create();
//        server.port(PORT);
//        server.host(HOST);
//        server.mapHandle((t, a) -> {
//            t.block();
//            return t;
//        });
//            server.newHandler(new ReactorHttpHandlerAdapter(toHttpHandler(route))).block();
    }

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(ReactiveApplication.class, args);
//        GreetingClient greetingClient = context.getBean(GreetingClient.class);
        // We need to block for the content here or the JVM might exit before the message is logged
//        System.out.println(">> message = " + greetingClient.getMessage().block());
//    }
//        ReactiveApplication server = new ReactiveApplication();
//        server.startReactorServer();
//        System.out.println("Press ENTER to exit.");
//        System.in.read();
    }
}
