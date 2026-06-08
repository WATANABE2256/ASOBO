package com.asobo.service;

import com.asobo.model.Spot;
import com.asobo.model.SpotCategory;
import com.asobo.repository.SpotRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SpotService {

    private final SpotRepository spotRepository;

    public SpotService(SpotRepository spotRepository) {
        this.spotRepository = spotRepository;
    }

    public List<Spot> findAllPopular() {
        return spotRepository.findAllByOrderByRatingDesc();
    }

    public List<Spot> findByCategory(SpotCategory category) {
        return spotRepository.findByCategoryOrderByRatingDesc(category);
    }

    public Spot findById(Long id) {
        return spotRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Spot not found"));
    }

    public Spot findNextForGroup(Long groupId, Long currentSpotId) {
        List<Spot> spots = spotRepository.findAllByOrderByRatingDesc();
        if (spots.isEmpty()) {
            throw new IllegalStateException("No spots available");
        }
        if (currentSpotId == null) {
            return spots.get(0);
        }
        for (int i = 0; i < spots.size(); i++) {
            if (spots.get(i).getId().equals(currentSpotId) && i + 1 < spots.size()) {
                return spots.get(i + 1);
            }
        }
        return spots.get(0);
    }
}
