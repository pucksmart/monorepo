package dev.pucksmart;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.pucksmart.extract.nhlapi.ShiftsApi;
import dev.pucksmart.extract.nhlapi.StatsApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportRuntimeHints;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@ImportRuntimeHints(GraalHints.class)
@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    // All Retrofit interfaces need to be added to the GraalHints
    @Bean
    public StatsApi nhlStatsApi(ObjectMapper objectMapper) {
        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl("https://statsapi.web.nhl.com")
                        .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                        .build();

        return retrofit.create(StatsApi.class);
    }

    @Bean
    public ShiftsApi nhlShiftsApi(ObjectMapper objectMapper) {
        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl("https://api.nhle.com")
                        .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                        .build();

        return retrofit.create(ShiftsApi.class);
    }
    // All Retrofit interfaces need to be added to the GraalHints
}
