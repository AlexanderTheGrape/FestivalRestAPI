package APIServer;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class APISever extends Application {

    /**
     * Creates a root Restlet that will receive all incoming calls.
     */
    @Override
    public synchronized Restlet createInboundRoot() {
        // Create a router Restlet that routes each call to a new instance of HelloWorldResource.
        Router router = new Router(getContext());

        // Defines only one route
        router.attach("/testPing", TestPing.class);
        router.attach("/wave", Text2Wave.class);
        router.attach("/waveResource", File2Wave.class);

        return router;
    }

}
