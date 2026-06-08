import { Avatar, OutlineButton, PrimaryButton } from "./ui";

export function InviteScreen() {
  return (
    <div className="flex flex-1 flex-col items-center justify-center px-6 text-center">
      <Avatar name="ゆうき" color="#FF8A00" size="md" />
      <p className="mt-3 text-sm font-bold">ゆうきさんから招待が届きました</p>
      <p className="mt-1 text-[11px] text-asobo-gray">
        「週末おでかけ部」に参加しますか？
      </p>

      <div className="mt-8 w-full flex flex-col gap-2">
        <PrimaryButton>参加する</PrimaryButton>
        <OutlineButton>あとで参加</OutlineButton>
      </div>
    </div>
  );
}
