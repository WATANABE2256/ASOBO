package com.asobo.service;

import com.asobo.model.Plan;
import com.asobo.model.PlanStatus;
import com.asobo.repository.PlanRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PlanService {

    private final PlanRepository planRepository;

    public PlanService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public List<Plan> findByStatus(PlanStatus status) {
        return planRepository.findByStatusOrderByScheduledAtAsc(status);
    }
}
