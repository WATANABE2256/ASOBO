export function PhoneFrame({
  children,
  label,
}: {
  children: React.ReactNode;
  label?: string;
}) {
  return (
    <div className="flex flex-col items-center gap-2">
      {label && (
        <p className="text-xs font-medium text-asobo-gray">{label}</p>
      )}
      <div className="relative w-[280px] overflow-hidden rounded-[2rem] border-[6px] border-gray-800 bg-white shadow-xl">
        <div className="absolute top-0 left-1/2 z-10 h-5 w-24 -translate-x-1/2 rounded-b-xl bg-gray-800" />
        <div className="flex h-[580px] flex-col overflow-hidden pt-5">
          {children}
        </div>
      </div>
    </div>
  );
}

export function Tag({ children }: { children: React.ReactNode }) {
  return (
    <span className="rounded-md bg-asobo-gray-light px-2 py-0.5 text-[10px] text-asobo-gray">
      {children}
    </span>
  );
}

export function FilterChip({
  children,
  active,
}: {
  children: React.ReactNode;
  active?: boolean;
}) {
  return (
    <span
      className={`shrink-0 rounded-full border px-3 py-1 text-[10px] font-medium ${
        active
          ? "border-asobo-orange bg-asobo-orange-light text-asobo-orange"
          : "border-asobo-border bg-white text-asobo-gray"
      }`}
    >
      {children}
    </span>
  );
}

export function PrimaryButton({
  children,
  className = "",
}: {
  children: React.ReactNode;
  className?: string;
}) {
  return (
    <button
      type="button"
      className={`w-full rounded-full bg-asobo-orange py-3 text-sm font-bold text-white transition-colors hover:bg-asobo-orange-dark ${className}`}
    >
      {children}
    </button>
  );
}

export function OutlineButton({
  children,
  className = "",
}: {
  children: React.ReactNode;
  className?: string;
}) {
  return (
    <button
      type="button"
      className={`w-full rounded-full border-2 border-asobo-orange py-3 text-sm font-bold text-asobo-orange transition-colors hover:bg-asobo-orange-light ${className}`}
    >
      {children}
    </button>
  );
}

type TabId = "search" | "popular" | "schedule" | "mypage";

export function BottomNav({ active = "search" }: { active?: TabId }) {
  const tabs: { id: TabId; icon: string; label: string }[] = [
    { id: "search", icon: "🔍", label: "さがす" },
    { id: "popular", icon: "⭐", label: "人気" },
    { id: "schedule", icon: "📅", label: "予定" },
    { id: "mypage", icon: "👤", label: "マイページ" },
  ];

  return (
    <nav className="mt-auto flex border-t border-asobo-border bg-white px-2 py-2">
      {tabs.map((tab) => (
        <div
          key={tab.id}
          className={`flex flex-1 flex-col items-center gap-0.5 py-1 ${
            active === tab.id ? "text-asobo-orange" : "text-asobo-gray"
          }`}
        >
          <span className="text-base">{tab.icon}</span>
          <span className="text-[9px] font-medium">{tab.label}</span>
        </div>
      ))}
    </nav>
  );
}

export function ScreenHeader({
  title,
  showMenu,
  showBell,
}: {
  title?: string;
  showMenu?: boolean;
  showBell?: boolean;
}) {
  return (
    <header className="flex items-center justify-between px-4 py-2">
      {showMenu ? (
        <button type="button" className="text-lg text-gray-700">
          ☰
        </button>
      ) : (
        <div className="w-6" />
      )}
      {title ? (
        <span className="text-base font-black text-asobo-orange">{title}</span>
      ) : (
        <div />
      )}
      {showBell ? (
        <button type="button" className="text-lg">
          🔔
        </button>
      ) : (
        <div className="w-6" />
      )}
    </header>
  );
}

export function Avatar({
  name,
  color,
  size = "md",
}: {
  name: string;
  color: string;
  size?: "sm" | "md";
}) {
  const sizes = { sm: "h-8 w-8 text-xs", md: "h-10 w-10 text-sm" };
  return (
    <div
      className={`flex ${sizes[size]} shrink-0 items-center justify-center rounded-full font-bold text-white`}
      style={{ backgroundColor: color }}
    >
      {name[0]}
    </div>
  );
}

export function PlaceholderImage({
  gradient,
  emoji,
  className = "",
}: {
  gradient: string;
  emoji: string;
  className?: string;
}) {
  return (
    <div
      className={`flex items-center justify-center bg-gradient-to-br ${gradient} ${className}`}
    >
      <span className="text-5xl">{emoji}</span>
    </div>
  );
}
