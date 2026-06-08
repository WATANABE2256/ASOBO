# ASOBO Web

グループでおでかけ先を一緒に決める Spring Boot Web アプリです。

## 技術スタック

- Java 17
- Spring Boot 3.3
- Spring Data JPA
- H2 Database（ローカル開発）
- PostgreSQL（本番 / Railway）
- Thymeleaf

## ローカル起動

```bash
cd asobo-web
mvn spring-boot:run
```

ブラウザで http://localhost:8080 を開いてください（H2 プロファイル `local` がデフォルト）。

## Railway デプロイ

### 1. GitHub リポジトリを Railway に接続

1. [Railway](https://railway.app/) で New Project → Deploy from GitHub repo
2. `WATANABE2256/ASOBO` を選択

### 2. PostgreSQL を追加（必須）

1. プロジェクトで **New** → **Database** → **PostgreSQL**
2. Web サービスの **Variables** で PostgreSQL サービスを **Add Reference** して以下を接続:
   - `DATABASE_URL`（推奨）
   - または `PGHOST`, `PGPORT`, `PGUSER`, `PGPASSWORD`, `PGDATABASE`

PostgreSQL を接続しないとアプリは起動しません。

### 3. 環境変数（Web サービス）

| 変数 | 値 |
|------|-----|
| `SPRING_PROFILES_ACTIVE` | `prod` |

`SPRING_PROFILES_ACTIVE=prod` は Dockerfile にも設定済みです。

### 4. ビルド設定

リポジトリルートの `Dockerfile` と `railway.toml` を使用します。  
**Root Directory は空のまま**（リポジトリルート）にしてください。

> Root Directory を `asobo-web` に設定する場合は、`asobo-web/railway.toml` が使われます。

### 5. デプロイ確認

- ヘルスチェック: `/health`（`OK` が返れば成功）
- アプリ: Railway の URL で `/swipe` にアクセス

ログに `Tomcat started on port` が表示され、`Connected to PostgreSQL` 相当の DB 接続が成功していることを確認してください。

## 主な機能

- **スワイプ投票** — スポットに対して「行きたい」「パス」を投票
- **投票状況** — グループメンバーの投票状態を確認
- **決定** — 全員が「行きたい」の場合、行き先が決定
- **人気スポット / カテゴリ検索** — DB からスポット一覧を表示
- **予定** — 決定したおでかけ予定を管理
- **グループ管理** — グループ作成・切り替え

## データベース

### ローカル（H2）

`./data/asobo.mv.db` にファイル保存。

H2 コンソール: http://localhost:8080/h2-console

- JDBC URL: `jdbc:h2:file:./data/asobo`
- ユーザー: `sa`
- パスワード: （空）

### 本番（PostgreSQL / Railway）

`application-prod.properties` が Railway の PostgreSQL 変数を使用します。

## 初期データ

起動時に DB が空の場合、以下が自動投入されます。

- ユーザー 4 名（ゆうき、さくら、たろう、みか）
- スポット 5 件（渋谷エリア）
- グループ 2 件
- 予定データ
