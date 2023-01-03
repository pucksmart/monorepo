package dev.pucksmart.game.shift;

import dev.pucksmart.BaseRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftRepository extends BaseRepository<Shift, String> {
  List<Shift> findAllByGameId(String gameId);
}
