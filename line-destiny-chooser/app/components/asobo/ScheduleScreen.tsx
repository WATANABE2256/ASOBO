import { BottomNav, PlaceholderImage } from "./ui";

const plans = [
  {
    group: "週末おでかけ部",
    spot: "Round 1 渋谷店",
    date: "6/14（土）14:00",
    members: 4,
    emoji: "🎳",
    gradient: "from-amber-200 to-orange-100",
  },
  {
    group: "同僚ランチ会",
    spot: "渋谷スカイ",
    date: "6/21（土）12:00",
    members: 3,
    emoji: "🌆",
    gradient: "from-sky-200 to-blue-100",
  },
];

export function ScheduleScreen() {
  return (
    <>
      <div className="flex-1 overflow-y-auto px-4 py-2">
        <h2 className="mb-3 text-sm font-bold">予定</h2>
        <div className="mb-4 flex rounded-full bg-asobo-gray-light p-0.5">
          <span className="flex-1 rounded-full bg-asobo-orange py-1.5 text-center text-[10px] font-bold text-white">
            これから
          </span>
          <span className="flex-1 py-1.5 text-center text-[10px] font-medium text-asobo-gray">
            過去
          </span>
        </div>
        <ul className="flex flex-col gap-3">
          {plans.map((plan) => (
            <li
              key={plan.spot}
              className="flex gap-3 rounded-xl bg-white p-2 shadow-sm"
            >
              <PlaceholderImage
                gradient={plan.gradient}
                emoji={plan.emoji}
                className="h-16 w-16 shrink-0 rounded-lg"
              />
              <div className="flex flex-col justify-center">
                <p className="text-[10px] text-asobo-orange">{plan.group}</p>
                <p className="text-xs font-bold">{plan.spot}</p>
                <p className="text-[10px] text-asobo-gray">
                  📅 {plan.date} · 👥 {plan.members}人
                </p>
              </div>
            </li>
          ))}
        </ul>
      </div>
      <BottomNav active="schedule" />
    </>
  );
}
