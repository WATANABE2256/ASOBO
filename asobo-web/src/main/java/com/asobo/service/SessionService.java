package com.asobo.service;

import com.asobo.model.GroupEntity;
import com.asobo.model.User;
import com.asobo.repository.GroupRepository;
import com.asobo.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    private static final String USER_ID = "currentUserId";
    private static final String GROUP_ID = "currentGroupId";

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public SessionService(UserRepository userRepository, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    public User getCurrentUser(HttpSession session) {
        Long userId = (Long) session.getAttribute(USER_ID);
        if (userId == null) {
            User defaultUser = userRepository.findAll().stream().findFirst()
                    .orElseThrow(() -> new IllegalStateException("No users found"));
            session.setAttribute(USER_ID, defaultUser.getId());
            return defaultUser;
        }
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User not found"));
    }

    public void setCurrentUser(HttpSession session, Long userId) {
        session.setAttribute(USER_ID, userId);
    }

    public GroupEntity getCurrentGroup(HttpSession session) {
        Long groupId = (Long) session.getAttribute(GROUP_ID);
        if (groupId == null) {
            GroupEntity defaultGroup = groupRepository.findAll().stream().findFirst()
                    .orElseThrow(() -> new IllegalStateException("No groups found"));
            session.setAttribute(GROUP_ID, defaultGroup.getId());
            return defaultGroup;
        }
        return groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalStateException("Group not found"));
    }

    public void setCurrentGroup(HttpSession session, Long groupId) {
        session.setAttribute(GROUP_ID, groupId);
    }
}
