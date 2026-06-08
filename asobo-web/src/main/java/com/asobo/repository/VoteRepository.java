package com.asobo.repository;

import com.asobo.model.Vote;
import com.asobo.model.VoteType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    List<Vote> findByGroupIdAndSpotId(Long groupId, Long spotId);

    Optional<Vote> findByGroupIdAndSpotIdAndUserId(Long groupId, Long spotId, Long userId);

    long countByGroupIdAndSpotId(Long groupId, Long spotId);

    long countByGroupIdAndSpotIdAndVoteType(Long groupId, Long spotId, VoteType voteType);
}
