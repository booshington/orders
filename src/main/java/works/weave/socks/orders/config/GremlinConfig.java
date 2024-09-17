package works.weave.socks.orders.config;

import com.gremlin.*;
import works.weave.socks.orders.OrderApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class GremlinConfig {
    //.withType(String.format("%s%s", OrderApplication.class.getSimpleName(), ApplicationCoordinates.class.getSimpleName()))
    @Bean
    public GremlinCoordinatesProvider gremlinCoordinatesProvider() {
        return new GremlinCoordinatesProvider() {
            @Override
            public ApplicationCoordinates initializeApplicationCoordinates() {
                return new ApplicationCoordinates.Builder()
                        .withType("TestAppCoords")
                        .withField("service", "to-do")
                        .build();
            }
        };
    }

    @Bean
    public GremlinServiceFactory gremlinServiceFactory() {
        return new GremlinServiceFactory(gremlinCoordinatesProvider());
    }

    @Bean
    public GremlinService gremlinService() {
        return gremlinServiceFactory().getGremlinService();
    }


    @PostConstruct
    public void init() {
        // register gremlin with the control plane when the application loads

        gremlinService().applyImpact(new TrafficCoordinates.Builder()
                .withType("GremlinInit")
                .build());
    }
}