package org.merlin.quick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ComponentScan({ "org.merlin" })
public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext ctx = SpringApplication.run(App.class,
				args);
    }
}
