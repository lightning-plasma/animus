# animus - Spring Boot Webflux Application Sample

Spring Boot WebFluxのシンプルなCRUD アプリケーション   
Rを実装済み

## 技術要素

- Spring Boot WebFlux
- Kotlin
- R2DBC
- WebFlux MVC Controller
- WebFlux Kotlin DSL
- Thymeleaf
- BlockHound

Controller / Router の切り替えはAnnotationを付け替える

## application 起動

### postgres 起動

```shell
$ cd postgres
$ docker-compose up -d --build
$ psql -h localhost -p 5432 -U jung -d animus
```

### browser access

- http://localhost:8080/animus/books
- http://localhost:8080/animus/books/9784422114361
