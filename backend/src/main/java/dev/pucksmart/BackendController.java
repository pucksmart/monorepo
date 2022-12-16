package dev.pucksmart;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.pucksmart.extract.nhlapi.StatsApi;
import dev.pucksmart.extract.nhlapi.stats.StatsSeason;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
public class BackendController {

    final KafkaTemplate<String, String> kafkaTemplate;
    final ObjectMapper objectMapper;
    final StatsApi statsApi;

    public BackendController(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper, StatsApi statsApi) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.statsApi = statsApi;
    }

    @GetMapping("/stats/list-seasons")
    String listSeasons() throws IOException {
        List<StatsSeason> seasons = Objects.requireNonNull(statsApi.listSeasons().execute().body()).getSeasons();
        for (StatsSeason season : seasons) {
            kafkaTemplate.send("seasons", objectMapper.writeValueAsString(season));
        }
        return "OK";
    }
}
