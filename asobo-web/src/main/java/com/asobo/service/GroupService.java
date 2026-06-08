package com.asobo.service;

import com.asobo.model.GroupEntity;
import com.asobo.model.GroupMember;
import com.asobo.model.User;
import com.asobo.repository.GroupMemberRepository;
import com.asobo.repository.GroupRepository;
import com.asobo.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final UserRepository userRepository;

    public GroupService(
            GroupRepository groupRepository,
            GroupMemberRepository groupMemberRepository,
            UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.groupMemberRepository = groupMemberRepository;
        this.userRepository = userRepository;
    }

    public List<GroupEntity> findAll() {
        return groupRepository.findAll();
    }

    public GroupEntity findById(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Group not found"));
    }

    public List<GroupMember> getMembers(Long groupId) {
        return groupMemberRepository.findByGroupId(groupId);
    }

    @Transactional
    public GroupEntity createGroup(String name, Long ownerId) {
        GroupEntity group = groupRepository.save(new GroupEntity(name));
        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        groupMemberRepository.save(new GroupMember(group, owner));
        return group;
    }

    @Transactional
    public void addMember(Long groupId, Long userId) {
        if (groupMemberRepository.existsByGroupIdAndUserId(groupId, userId)) {
            return;
        }
        GroupEntity group = findById(groupId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        groupMemberRepository.save(new GroupMember(group, user));
    }
}
