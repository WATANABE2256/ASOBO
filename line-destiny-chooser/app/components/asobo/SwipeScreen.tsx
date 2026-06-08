import {
  BottomNav,
  FilterChip,
  PlaceholderImage,
  ScreenHeader,
  Tag,
} from "./ui";

export function SwipeScreen() {
  return (
    <>
      <ScreenHeader title="ASOBO" showMenu showBell />
      <div className="flex-1 overflow-y-auto px-4 pb-2">
        <div className="mb-3 flex gap-2 overflow-x-auto pb-1">
          <FilterChip active>📍 渋谷</FilterChip>
          <FilterChip>👥 4人</FilterChip>
          <FilterChip>💰 〜3000円</FilterChip>
          <FilterChip>🕐 今日</FilterChip>
        </div>

        <div className="overflow-hidden rounded-2xl bg-white shadow-md">
          <PlaceholderImage
            gradient="from-amber-200 via-orange-100 to-yellow-100"
            emoji="🎳"
            className="h-44 w-full"
          />
          <div className="p-3">
            <h2 className="mb-1 text-base font-bold">Round 1 渋谷店</h2>
            <div className="mb-2 flex items-center gap-3 text-[11px] text-asobo-gray">
              <span>⭐ 4.2</span>
              <span>🚶 徒歩8分</span>
              <span>💴 〜2,000円</span>
            </div>
            <div className="flex flex-wrap gap-1">
              <Tag>#屋内</Tag>
              <Tag>#雨OK</Tag>
              <Tag>#グループ向け</Tag>
            </div>
          </div>
        </div>

        <div className="mt-5 flex items-center justify-center gap-10">
          <button
            type="button"
            className="flex h-14 w-14 items-center justify-center rounded-full border-2 border-gray-300 bg-white text-xl text-gray-400 shadow-sm"
          >
            ✕
          </button>
          <button
            type="button"
            className="flex h-16 w-16 items-center justify-center rounded-full bg-asobo-orange text-2xl text-white shadow-lg shadow-asobo-orange/30"
          >
            ♥
          </button>
        </div>
        <p className="mt-2 text-center text-[10px] text-asobo-gray">
          パス / 行きたい！
        </p>
      </div>
      <BottomNav active="search" />
    </>
  );
}
