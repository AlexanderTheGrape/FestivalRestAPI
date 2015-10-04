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
        // Create a router Restlet that routes each call to a new instance of Resource.
        Router router = new Router(getContext());

        // Defines only one route
        router.attach("/txt2waveResource", Text2Wave.class);
        router.attach("/file2waveResource", File2Wave.class);

        return router;
    }

}
