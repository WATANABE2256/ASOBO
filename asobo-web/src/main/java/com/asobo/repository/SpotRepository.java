package com.asobo.repository;

import com.asobo.model.Spot;
import com.asobo.model.SpotCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotRepository extends JpaRepository<Spot, Long> {

    List<Spot> findByCategoryOrderByRatingDesc(SpotCategory category);

    List<Spot> findAllByOrderByRatingDesc();
}
