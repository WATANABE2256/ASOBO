import { CategoryScreen } from "./components/asobo/CategoryScreen";
import { DecisionScreen } from "./components/asobo/DecisionScreen";
import { GroupCreateScreen } from "./components/asobo/GroupCreateScreen";
import { InviteScreen } from "./components/asobo/InviteScreen";
import { PopularScreen } from "./components/asobo/PopularScreen";
import { ScheduleScreen } from "./components/asobo/ScheduleScreen";
import { SpotDetailScreen } from "./components/asobo/SpotDetailScreen";
import { SwipeScreen } from "./components/asobo/SwipeScreen";
import { VotingScreen } from "./components/asobo/VotingScreen";
import { PhoneFrame } from "./components/asobo/ui";

const screens = [
  { label: "メイン（スワイプ）", component: <SwipeScreen /> },
  { label: "投票状況", component: <VotingScreen /> },
  { label: "決定", component: <DecisionScreen /> },
  { label: "グループ作成", component: <GroupCreateScreen /> },
  { label: "招待", component: <InviteScreen /> },
  { label: "スポット詳細", component: <SpotDetailScreen /> },
  { label: "カテゴリ検索", component: <CategoryScreen /> },
  { label: "人気スポット", component: <PopularScreen /> },
  { label: "予定", component: <ScheduleScreen /> },
];

export default function Home() {
  return (
    <div className="min-h-screen bg-gradient-to-b from-asobo-orange-light to-white">
      <header className="px-6 py-10 text-center">
        <h1 className="text-3xl font-black tracking-tight text-asobo-orange md:text-4xl">
          ASOBO
        </h1>
        <p className="mt-2 text-sm text-asobo-gray md:text-base">
          みんなで決める、おでかけ先マッチングアプリ
        </p>
      </header>

      <div className="mx-auto grid max-w-6xl grid-cols-1 justify-items-center gap-10 px-4 pb-16 sm:grid-cols-2 lg:grid-cols-3">
        {screens.map((screen) => (
          <PhoneFrame key={screen.label} label={screen.label}>
            {screen.component}
          </PhoneFrame>
        ))}
      </div>
    </div>
  );
}
