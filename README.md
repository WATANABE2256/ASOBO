# ASOBO

みんなで決める、おでかけ先マッチングアプリのプロジェクトです。

## プロジェクト構成

| ディレクトリ | 説明 |
|---|---|
| `asobo-web/` | Spring Boot + H2 + Thymeleaf の本番 Web アプリ |
| `line-destiny-chooser/` | Next.js による UI プロトタイプ |

## 起動方法（メインアプリ）

```bash
cd asobo-web
mvn spring-boot:run
```

http://localhost:8080 でアクセスできます。

詳細は [asobo-web/README.md](asobo-web/README.md) を参照してください。

## 必要環境

- Java 17+
- Maven 3.9+（または `mvnw`）
