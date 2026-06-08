# ASOBO Web

グループでおでかけ先を一緒に決める Spring Boot Web アプリです。

## 技術スタック

- Java 17
- Spring Boot 3.3
- Spring Data JPA
- H2 Database（ファイル永続化）
- Thymeleaf

## 起動方法

```bash
cd asobo-web
mvn spring-boot:run
```

ブラウザで http://localhost:8080 を開いてください。

## 主な機能

- **スワイプ投票** — スポットに対して「行きたい」「パス」を投票
- **投票状況** — グループメンバーの投票状態を確認
- **決定** — 全員が「行きたい」の場合、行き先が決定
- **人気スポット / カテゴリ検索** — DB からスポット一覧を表示
- **予定** — 決定したおでかけ予定を管理
- **グループ管理** — グループ作成・切り替え

## データベース

H2 ファイル DB を使用します（`./data/asobo.mv.db`）。

H2 コンソール: http://localhost:8080/h2-console

- JDBC URL: `jdbc:h2:file:./data/asobo`
- ユーザー: `sa`
- パスワード: （空）

## 初期データ

起動時に以下が自動投入されます。

- ユーザー 4 名（ゆうき、さくら、たろう、みか）
- スポット 5 件（渋谷エリア）
- グループ 2 件
- 予定データ
