# ASOBO

みんなで決める、おでかけ先マッチングアプリのプロジェクトです。

## プロジェクト構成

| ディレクトリ | 説明 |
|---|---|
| `asobo-web/` | Spring Boot + PostgreSQL/H2 + Thymeleaf の本番 Web アプリ |
| `line-destiny-chooser/` | Next.js による UI プロトタイプ |

## ローカル起動

```bash
cd asobo-web
mvn spring-boot:run
```

http://localhost:8080 でアクセスできます。

## Railway デプロイ

リポジトリルートの `Dockerfile` と `railway.toml` で Railway にデプロイできます。

1. Railway で GitHub リポジトリ `WATANABE2256/ASOBO` を接続
2. **PostgreSQL** データベースを追加
3. Web サービスに PostgreSQL の変数（`PGHOST` 等）を参照設定
4. デプロイ後、付与された URL でアクセス

詳細は [asobo-web/README.md](asobo-web/README.md) を参照してください。

## 必要環境

- Java 17+
- Maven 3.9+
