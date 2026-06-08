package com.asobo.model;

public enum SpotCategory {
    PLAY("遊ぶ", "🎮"),
    FOOD("食べる", "🍽️"),
    CAFE("カフェ", "☕"),
    MOVIE("映画", "🎬"),
    SHOPPING("買い物", "🛍️"),
    OUTING("おでかけ", "🌳"),
    SPORTS("スポーツ", "⚽"),
    EXPERIENCE("体験", "🎨"),
    DRIVE("ドライブ", "🚗");

    private final String label;
    private final String emoji;

    SpotCategory(String label, String emoji) {
        this.label = label;
        this.emoji = emoji;
    }

    public String getLabel() {
        return label;
    }

    public String getEmoji() {
        return emoji;
    }
}
