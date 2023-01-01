package dev.pucksmart;

import dev.pucksmart.extract.nhlapi.ShiftsApi;
import dev.pucksmart.extract.nhlapi.StatsApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
public class BackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(BackendApplication.class, args);
  }

  @Bean
  public StatsApi nhlStatsApi() {
    int size = 16 * 1024 * 1024;
    ExchangeStrategies strategies =
        ExchangeStrategies.builder()
            .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
            .build();
    WebClient client =
        WebClient.builder()
            .baseUrl("https://statsapi.web.nhl.com")
            .exchangeStrategies(strategies)
            .build();
    HttpServiceProxyFactory factory =
        HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();

    return factory.createClient(StatsApi.class);
  }

  @Bean
  public ShiftsApi nhlShiftsApi() {
    int size = 16 * 1024 * 1024;
    ExchangeStrategies strategies =
        ExchangeStrategies.builder()
            .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
            .build();
    WebClient client =
        WebClient.builder().baseUrl("https://api.nhle.com").exchangeStrategies(strategies).build();
    HttpServiceProxyFactory factory =
        HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();

    return factory.createClient(ShiftsApi.class);
  }
}
