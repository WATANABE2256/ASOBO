import { OutlineButton, PlaceholderImage, PrimaryButton } from "./ui";

export function DecisionScreen() {
  return (
    <div className="flex flex-1 flex-col items-center px-4 py-4 text-center">
      <span className="mb-2 text-4xl">🎉</span>
      <h2 className="mb-1 text-base font-black">決定しました！</h2>
      <p className="mb-4 text-[11px] text-asobo-gray">
        全員が「行きたい」と回答しました
      </p>

      <div className="w-full overflow-hidden rounded-2xl bg-white shadow-md">
        <PlaceholderImage
          gradient="from-amber-200 via-orange-100 to-yellow-100"
          emoji="🎳"
          className="h-36 w-full"
        />
        <div className="p-3 text-left">
          <h3 className="text-sm font-bold">Round 1 渋谷店</h3>
          <p className="mt-1 text-[10px] text-asobo-gray">
            ⭐ 4.2 · 徒歩8分 · 〜2,000円
          </p>
          <p className="mt-1 text-[10px] text-asobo-gray">
            東京都渋谷区道玄坂1-2-3
          </p>
        </div>
      </div>

      <div className="mt-5 w-full flex flex-col gap-2">
        <PrimaryButton>詳細を見る</PrimaryButton>
        <OutlineButton>マップで開く</OutlineButton>
      </div>
    </div>
  );
}
