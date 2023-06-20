package sber.practice.musicgroups.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sber.practice.musicgroups.domain.GroupEntity;

public interface GroupEntityRepository extends JpaRepository<GroupEntity, Long> {
}
