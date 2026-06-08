const categories = [
  { icon: "🎮", label: "遊ぶ" },
  { icon: "🍽️", label: "食べる" },
  { icon: "☕", label: "カフェ" },
  { icon: "🎬", label: "映画" },
  { icon: "🛍️", label: "買い物" },
  { icon: "🌳", label: "おでかけ" },
  { icon: "⚽", label: "スポーツ" },
  { icon: "🎨", label: "体験" },
  { icon: "🚗", label: "ドライブ" },
];

export function CategoryScreen() {
  return (
    <div className="flex flex-1 flex-col px-4 py-2">
      <h2 className="mb-4 text-center text-sm font-bold">カテゴリからさがす</h2>
      <div className="grid grid-cols-3 gap-3">
        {categories.map((cat) => (
          <button
            key={cat.label}
            type="button"
            className="flex flex-col items-center gap-1.5 rounded-2xl border border-asobo-border bg-white py-4 transition-colors hover:border-asobo-orange hover:bg-asobo-orange-light"
          >
            <span className="text-2xl">{cat.icon}</span>
            <span className="text-[10px] font-medium">{cat.label}</span>
          </button>
        ))}
      </div>
    </div>
  );
}
