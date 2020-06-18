package pl.wsb.students.main;

import org.glassfish.jersey.server.ResourceConfig;
import pl.wsb.students.security.annotation.AuthenticateFilter;

public class ServerConfig extends ResourceConfig {
    public ServerConfig() {
        register(AuthenticateFilter.class);
    }
}