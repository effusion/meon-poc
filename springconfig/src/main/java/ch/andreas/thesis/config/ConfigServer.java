package ch.andreas.thesis.config;

import org.eclipse.jgit.transport.SshSessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

import java.net.URL;

/**
 * Created by heuby on 13.01.17.
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigServer {

    public static void main(String[] args) {
        URL keys = ConfigServer.class.getClassLoader().getResource( "keys/id_rsa" );
        URL knownHosts = ConfigServer.class.getClassLoader().getResource( "keys/known_hosts" );
        SshSessionFactory.setInstance( new CustomSshSessionFactory(keys, knownHosts));
        SpringApplication.run(ConfigServer.class, args);
    }
}
