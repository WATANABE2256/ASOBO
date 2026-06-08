import { Avatar, PlaceholderImage } from "./ui";

const members = [
  { name: "ゆうき", color: "#FF8A00", vote: "want" as const },
  { name: "さくら", color: "#FF6B6B", vote: "want" as const },
  { name: "たろう", color: "#4ECDC4", vote: "pass" as const },
  { name: "みか", color: "#A78BFA", vote: "pending" as const },
];

export function VotingScreen() {
  return (
    <div className="flex flex-1 flex-col px-4 py-2">
      <h2 className="mb-1 text-center text-sm font-bold">投票状況</h2>
      <p className="mb-4 text-center text-[10px] text-asobo-gray">
        Round 1 渋谷店 — 3人中2人が投票済み
      </p>

      <div className="mb-4 overflow-hidden rounded-xl">
        <PlaceholderImage
          gradient="from-amber-200 to-orange-100"
          emoji="🎳"
          className="h-28 w-full"
        />
      </div>

      <ul className="flex flex-col gap-3">
        {members.map((m) => (
          <li
            key={m.name}
            className="flex items-center justify-between rounded-xl bg-asobo-gray-light px-3 py-2"
          >
            <div className="flex items-center gap-2">
              <Avatar name={m.name} color={m.color} size="sm" />
              <span className="text-xs font-medium">{m.name}</span>
            </div>
            {m.vote === "want" && (
              <span className="flex items-center gap-1 text-[11px] font-bold text-asobo-orange">
                ♥ 行きたい
              </span>
            )}
            {m.vote === "pass" && (
              <span className="flex items-center gap-1 text-[11px] text-asobo-gray">
                ✕ パス
              </span>
            )}
            {m.vote === "pending" && (
              <span className="text-[11px] text-asobo-gray">投票待ち…</span>
            )}
          </li>
        ))}
      </ul>

      <div className="mt-auto rounded-xl bg-asobo-orange-light p-3 text-center">
        <p className="text-[11px] font-medium text-asobo-orange">
          あと1人の投票を待っています
        </p>
      </div>
    </div>
  );
}
