import { Avatar, PrimaryButton } from "./ui";

const members = [
  { name: "ゆうき", color: "#FF8A00" },
  { name: "さくら", color: "#FF6B6B" },
  { name: "たろう", color: "#4ECDC4" },
];

export function GroupCreateScreen() {
  return (
    <div className="flex flex-1 flex-col px-4 py-2">
      <h2 className="mb-4 text-center text-sm font-bold">グループを作成</h2>

      <label className="mb-1 text-[11px] font-medium text-asobo-gray">
        グループ名
      </label>
      <input
        type="text"
        defaultValue="週末おでかけ部"
        readOnly
        className="mb-5 rounded-xl border border-asobo-border px-3 py-2.5 text-sm outline-none focus:border-asobo-orange"
      />

      <p className="mb-2 text-[11px] font-medium text-asobo-gray">メンバー</p>
      <ul className="mb-5 flex flex-col gap-2">
        {members.map((m) => (
          <li
            key={m.name}
            className="flex items-center gap-3 rounded-xl border border-asobo-border px-3 py-2"
          >
            <Avatar name={m.name} color={m.color} size="sm" />
            <span className="text-xs font-medium">{m.name}</span>
          </li>
        ))}
        <li>
          <button
            type="button"
            className="flex h-10 w-10 items-center justify-center rounded-full border-2 border-dashed border-asobo-orange text-asobo-orange"
          >
            +
          </button>
        </li>
      </ul>

      <div className="mt-auto">
        <PrimaryButton className="!bg-[#06C755] hover:!bg-[#05b34c]">
          LINEで招待する
        </PrimaryButton>
      </div>
    </div>
  );
}
