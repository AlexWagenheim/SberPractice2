package sber.practice.musicgroups.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sber.practice.musicgroups.domain.TrackEntity;

public interface TrackEntityRepository extends JpaRepository<TrackEntity, Long> {
}
