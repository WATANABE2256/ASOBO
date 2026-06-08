package com.asobo.service;

import com.asobo.dto.MemberVoteStatus;
import com.asobo.model.GroupEntity;
import com.asobo.model.Plan;
import com.asobo.model.Spot;
import com.asobo.model.User;
import com.asobo.model.Vote;
import com.asobo.model.VoteType;
import com.asobo.repository.PlanRepository;
import com.asobo.repository.VoteRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final GroupService groupService;
    private final PlanRepository planRepository;

    public VoteService(
            VoteRepository voteRepository,
            GroupService groupService,
            PlanRepository planRepository) {
        this.voteRepository = voteRepository;
        this.groupService = groupService;
        this.planRepository = planRepository;
    }

    @Transactional
    public void castVote(GroupEntity group, Spot spot, User user, VoteType voteType) {
        Vote vote = voteRepository.findByGroupIdAndSpotIdAndUserId(
                        group.getId(), spot.getId(), user.getId())
                .orElse(new Vote(group, spot, user, voteType));
        vote.setVoteType(voteType);
        vote.setVotedAt(LocalDateTime.now());
        voteRepository.save(vote);
    }

    public List<MemberVoteStatus> getVoteStatuses(Long groupId, Long spotId) {
        List<User> members = groupService.getMembers(groupId).stream()
                .map(m -> m.getUser())
                .toList();

        Map<Long, VoteType> voteMap = voteRepository.findByGroupIdAndSpotId(groupId, spotId)
                .stream()
                .collect(Collectors.toMap(v -> v.getUser().getId(), Vote::getVoteType));

        List<MemberVoteStatus> statuses = new ArrayList<>();
        for (User member : members) {
            statuses.add(new MemberVoteStatus(member, voteMap.get(member.getId())));
        }
        return statuses;
    }

    public int getVotedCount(Long groupId, Long spotId) {
        return (int) voteRepository.countByGroupIdAndSpotId(groupId, spotId);
    }

    public int getMemberCount(Long groupId) {
        return groupService.getMembers(groupId).size();
    }

    public boolean isAllVoted(Long groupId, Long spotId) {
        return getVotedCount(groupId, spotId) >= getMemberCount(groupId);
    }

    public boolean isUnanimousWant(Long groupId, Long spotId) {
        int memberCount = getMemberCount(groupId);
        long wantCount = voteRepository.countByGroupIdAndSpotIdAndVoteType(
                groupId, spotId, VoteType.WANT);
        return memberCount > 0 && wantCount == memberCount && isAllVoted(groupId, spotId);
    }

    @Transactional
    public Plan createPlanIfDecided(GroupEntity group, Spot spot) {
        if (!isUnanimousWant(group.getId(), spot.getId())) {
            return null;
        }
        boolean exists = planRepository.findAll().stream()
                .anyMatch(p -> p.getGroup().getId().equals(group.getId())
                        && p.getSpot().getId().equals(spot.getId()));
        if (exists) {
            return null;
        }
        Plan plan = new Plan(group, spot, LocalDateTime.now().plusDays(7));
        return planRepository.save(plan);
    }
}
