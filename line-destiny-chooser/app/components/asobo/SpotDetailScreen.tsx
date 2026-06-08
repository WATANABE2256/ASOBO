import { OutlineButton, PlaceholderImage } from "./ui";

export function SpotDetailScreen() {
  return (
    <div className="flex flex-1 flex-col">
      <PlaceholderImage
        gradient="from-amber-200 via-orange-100 to-yellow-100"
        emoji="🎳"
        className="h-44 w-full shrink-0"
      />
      <div className="flex-1 overflow-y-auto px-4 py-3">
        <h2 className="text-base font-bold">Round 1 渋谷店</h2>
        <p className="mt-1 text-[11px] text-asobo-gray">
          ⭐ 4.2（128件）· ボウリング
        </p>

        <div className="mt-4 flex flex-col gap-3">
          <div>
            <p className="text-[10px] font-bold text-asobo-gray">営業時間</p>
            <p className="text-xs">10:00 〜 翌5:00（年中無休）</p>
          </div>
          <div>
            <p className="text-[10px] font-bold text-asobo-gray">住所</p>
            <p className="text-xs">東京都渋谷区道玄坂1-2-3</p>
          </div>
          <div>
            <p className="text-[10px] font-bold text-asobo-gray">料金目安</p>
            <p className="text-xs">平日 1ゲーム 600円〜 / 土日 800円〜</p>
          </div>
        </div>
      </div>
      <div className="border-t border-asobo-border px-4 py-3">
        <OutlineButton>マップで開く</OutlineButton>
      </div>
    </div>
  );
}
