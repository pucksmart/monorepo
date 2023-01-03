package dev.pucksmart.game;

import dev.pucksmart.BaseRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends BaseRepository<Game, String> {
  Iterable<Game> findAllBySeasonId(Sort sort, String seasonId);
}
