package dev.pucksmart.game.event;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenaltyRepository extends CrudRepository<Penalty, String> {}
