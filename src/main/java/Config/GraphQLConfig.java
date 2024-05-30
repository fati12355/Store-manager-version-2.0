package Config;

import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

    @Configuration
    public class GraphQLConfig {

        @Bean
        public GraphQLScalarType localDateTimeScalar() {
            return GraphQLScalarType.newScalar()
                    .name("LocalDateTime")
                    .description("Custom Scalar for Java 8 LocalDateTime")
                    .coercing(new LocalDateTimeCoercing())
                    .build();
        }

        // ... Other GraphQL configuration beans
    }


