package com.asobo.repository;

import com.asobo.model.Plan;
import com.asobo.model.PlanStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    List<Plan> findByStatusOrderByScheduledAtAsc(PlanStatus status);
}
