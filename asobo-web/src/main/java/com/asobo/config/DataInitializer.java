package com.asobo.config;

import com.asobo.model.GroupEntity;
import com.asobo.model.GroupMember;
import com.asobo.model.Plan;
import com.asobo.model.PlanStatus;
import com.asobo.model.Spot;
import com.asobo.model.SpotCategory;
import com.asobo.model.User;
import com.asobo.repository.GroupMemberRepository;
import com.asobo.repository.GroupRepository;
import com.asobo.repository.PlanRepository;
import com.asobo.repository.SpotRepository;
import com.asobo.repository.UserRepository;
import java.time.LocalDateTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final SpotRepository spotRepository;
    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final PlanRepository planRepository;

    public DataInitializer(
            UserRepository userRepository,
            SpotRepository spotRepository,
            GroupRepository groupRepository,
            GroupMemberRepository groupMemberRepository,
            PlanRepository planRepository) {
        this.userRepository = userRepository;
        this.spotRepository = spotRepository;
        this.groupRepository = groupRepository;
        this.groupMemberRepository = groupMemberRepository;
        this.planRepository = planRepository;
    }

    @Override
    public void run(String... args) {
        try {
            if (userRepository.count() > 0) {
                return;
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(DataInitializer.class)
                    .error("Database not ready for seed data: {}", e.getMessage());
            return;
        }

        try {

        User yuki = userRepository.save(new User("ゆうき", "#FF8A00"));
        User sakura = userRepository.save(new User("さくら", "#FF6B6B"));
        User taro = userRepository.save(new User("たろう", "#4ECDC4"));
        User mika = userRepository.save(new User("みか", "#A78BFA"));

        spotRepository.save(createSpot(
                "Round 1 渋谷店", SpotCategory.PLAY, 4.2, 8, 2000,
                "#屋内,#雨OK,#グループ向け", "🎳",
                "gradient-amber", "東京都渋谷区道玄坂1-2-3",
                "10:00 〜 翌5:00（年中無休）", "渋谷駅から徒歩8分のボウリング場", "渋谷"));
        spotRepository.save(createSpot(
                "渋谷スカイ", SpotCategory.OUTING, 4.5, 12, 3500,
                "#景色,#デート向け", "🌆",
                "gradient-sky", "東京都渋谷区渋谷2-24-12",
                "10:00 〜 22:30", "渋谷の街を一望できる展望施設", "渋谷"));
        spotRepository.save(createSpot(
                "スターバックス 渋谷店", SpotCategory.CAFE, 4.0, 3, 1000,
                "#カフェ,#会話向け", "☕",
                "gradient-green", "東京都渋谷区宇田川町1-1",
                "7:00 〜 23:00", "渋谷駅近くのカフェ", "渋谷"));
        spotRepository.save(createSpot(
                "TOHOシネマズ 渋谷", SpotCategory.MOVIE, 4.3, 5, 2500,
                "#屋内,#エンタメ", "🎬",
                "gradient-purple", "東京都渋谷区道玄坂2-6-17",
                "9:00 〜 24:00", "最新映画が楽しめるシネコン", "渋谷"));
        spotRepository.save(createSpot(
                "宮下パーク", SpotCategory.SHOPPING, 4.1, 6, 3000,
                "#買い物,#おしゃれ", "🛍️",
                "gradient-rose", "東京都渋谷区神宮前6-20-10",
                "11:00 〜 21:00", "ショッピングとグルメが楽しめる複合施設", "渋谷"));

        GroupEntity group = groupRepository.save(new GroupEntity("週末おでかけ部"));
        groupMemberRepository.save(new GroupMember(group, yuki));
        groupMemberRepository.save(new GroupMember(group, sakura));
        groupMemberRepository.save(new GroupMember(group, taro));
        groupMemberRepository.save(new GroupMember(group, mika));

        Spot bowling = spotRepository.findAll().get(0);
        planRepository.save(new Plan(group, bowling, LocalDateTime.of(2026, 6, 14, 14, 0)));

        GroupEntity lunchGroup = groupRepository.save(new GroupEntity("同僚ランチ会"));
        groupMemberRepository.save(new GroupMember(lunchGroup, yuki));
        groupMemberRepository.save(new GroupMember(lunchGroup, taro));
        groupMemberRepository.save(new GroupMember(lunchGroup, mika));

        Spot sky = spotRepository.findAll().get(1);
        Plan pastPlan = new Plan(lunchGroup, sky, LocalDateTime.of(2026, 5, 10, 12, 0));
        pastPlan.setStatus(PlanStatus.PAST);
        planRepository.save(pastPlan);
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(DataInitializer.class)
                    .error("Failed to seed initial data: {}", e.getMessage());
        }
    }

    private Spot createSpot(
            String name, SpotCategory category, double rating, int walk, int price,
            String tags, String emoji, String gradient, String address,
            String hours, String description, String area) {
        Spot spot = new Spot();
        spot.setName(name);
        spot.setCategory(category);
        spot.setRating(rating);
        spot.setWalkMinutes(walk);
        spot.setPriceMax(price);
        spot.setTags(tags);
        spot.setEmoji(emoji);
        spot.setGradient(gradient);
        spot.setAddress(address);
        spot.setHours(hours);
        spot.setDescription(description);
        spot.setArea(area);
        return spot;
    }
}
