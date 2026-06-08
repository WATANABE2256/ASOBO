import { BottomNav, PlaceholderImage } from "./ui";

const tabs = ["すべて", "遊ぶ", "食べる", "カフェ"];
const spots = [
  {
    name: "Round 1 渋谷店",
    rating: "4.2",
    info: "徒歩8分 · 〜2,000円",
    emoji: "🎳",
    gradient: "from-amber-200 to-orange-100",
  },
  {
    name: "渋谷スカイ",
    rating: "4.5",
    info: "徒歩12分 · 〜3,500円",
    emoji: "🌆",
    gradient: "from-sky-200 to-blue-100",
  },
  {
    name: "スターバックス 渋谷店",
    rating: "4.0",
    info: "徒歩3分 · 〜1,000円",
    emoji: "☕",
    gradient: "from-green-100 to-emerald-50",
  },
];

export function PopularScreen() {
  return (
    <>
      <div className="flex-1 overflow-y-auto px-4 py-2">
        <h2 className="mb-3 text-sm font-bold">人気スポット</h2>
        <div className="mb-3 flex gap-2 overflow-x-auto">
          {tabs.map((tab, i) => (
            <span
              key={tab}
              className={`shrink-0 rounded-full px-3 py-1 text-[10px] font-medium ${
                i === 0
                  ? "bg-asobo-orange text-white"
                  : "bg-asobo-gray-light text-asobo-gray"
              }`}
            >
              {tab}
            </span>
          ))}
        </div>
        <ul className="flex flex-col gap-3">
          {spots.map((spot) => (
            <li
              key={spot.name}
              className="flex gap-3 rounded-xl bg-white p-2 shadow-sm"
            >
              <PlaceholderImage
                gradient={spot.gradient}
                emoji={spot.emoji}
                className="h-16 w-16 shrink-0 rounded-lg"
              />
              <div className="flex flex-col justify-center">
                <p className="text-xs font-bold">{spot.name}</p>
                <p className="text-[10px] text-asobo-gray">
                  ⭐ {spot.rating} · {spot.info}
                </p>
              </div>
            </li>
          ))}
        </ul>
      </div>
      <BottomNav active="popular" />
    </>
  );
}
