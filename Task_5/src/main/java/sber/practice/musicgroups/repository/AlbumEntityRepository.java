package sber.practice.musicgroups.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sber.practice.musicgroups.domain.AlbumEntity;

public interface AlbumEntityRepository extends JpaRepository<AlbumEntity, Long> {
}
