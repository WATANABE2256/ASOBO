package com.asobo.dto;

import com.asobo.model.User;
import com.asobo.model.VoteType;

public class MemberVoteStatus {

    private final User user;
    private final VoteType voteType;

    public MemberVoteStatus(User user, VoteType voteType) {
        this.user = user;
        this.voteType = voteType;
    }

    public User getUser() {
        return user;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public boolean isPending() {
        return voteType == null;
    }
}
