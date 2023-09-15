# java-check-bracket

<details>
  <summary><h4> Contents </h4></summary>

1. [Getting Started](#getting-started)
    - [Short instruction](#short-instruction)
    - [Basic instruction](#basic-instruction)
        - [Preparation](#preparation)
        - [Running application](#running-application)
2. [Documentation](#documentation)
    - [Reference Documentation](#reference-documentation)
    - [Guides](#guides)
    - [Additional Links](#additional-links)
    - [Docker Compose support](#docker-compose-support)
3. [Language and tools](#language-and-tools)
4. [Example type inputText](#example-type-inputtext)
   - [Request](#request)
   - [Responses](#responses)
   - [Examples](#examples)

</details>



---
<details>
  <summary><h3>  Getting Started </h3></summary>

#### Getting Started

#### Short instruction

```text
Start Docker, in the console navigate to the programme folder and use the command `docker compose up`.
```

#### Basic instruction

_Потребуется Java 17, Docker, Git, Gradle_

#### Preparation

Для работы приложение требуется установленный и запущенный Docker daemon.
Для проверки его наличия введите следующую команду в консоли

* console
  ```sh
  docker version
  ```

Если выводится информация об установленной системе, переходим к следующему шагу.

#### Running application

_Далее описаны пункты для запуска проекта_

1. С клонировать
    ```shell
    git clone https://github.com/xseeeds/spring-java-checkBracketSber
    ```
2. Перейдите в корневую папку проекта
   ```shell
   cd {путь да корневой директории}/spring-java-checkBracketSber
   ```
3. Собрать проект
    ```shell
    gradle clean build
    ```
4. Вызовите утилиту
    ```shell
    docker compose up
    ```

<p align="right">(<a href="#java-check-bracket">Back to Top</a>)</p>
</details>

---
<details>
  <summary><h3> Documentation </h3></summary>

#### Documentation

#### Reference Documentation

For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.3/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.3/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.3/reference/htmlsingle/index.html#web)
* [Docker Compose Support](https://docs.spring.io/spring-boot/docs/3.1.3/reference/htmlsingle/index.html#features.docker-compose)

#### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

#### Additional Links

These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

#### Docker Compose support

This project contains a Docker Compose file named `compose.yaml`.

However, no services were found. As of now, the application won't start!

Please make sure to add at least one service in the `compose.yaml` file.

<p align="right">(<a href="#java-check-bracket">Back to Top</a>)</p>
</details>

---
<details>
  <summary><h3> Language and tools </h3></summary>

#### Language and tools

* ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
* ![Spring Boot](https://img.shields.io/badge/spring%20Boot-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
* ![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
* ![Gradle](https://img.shields.io/badge/Gradle-02303a.svg?style=for-the-badge&logo=Gradle&logoColor=white)

<p align="right">(<a href="#java-check-bracket">Back to Top</a>)</p>
</details>

---
<details>
  <summary><h3> Example type inputText </h3></summary>

#### Example type inputText

#### Request

```http request
  GET /checkBracket
  with body json `inputText`
```

#### Responses

| Code | Description                                                                                                                                                                                                                                                                                                                                                                                                                                           |
|------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 200  | true                                                                                                                                                                                                                                                                                                                                                                                                                                                  |
| 200  | false                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| 400  | {<br/>&ensp;"violations":<br/>&emsp;[<br/>&emsp;&emsp;{<br/>&emsp;&emsp;&emsp;"timestamp": "yyyy-MM-dd HH:mm:ss",<br/>&emsp;&emsp;&emsp;"status": "BAD_REQUEST",<br/>&emsp;&emsp;&emsp;"reason": "ErrorHandler => errorMethodArgumentNotValidException",<br/>&emsp;&emsp;&emsp;"fieldName": "inputText",<br/>&emsp;&emsp;&emsp;"message": "Не должно быть пустым и содержать только пробелы, не должно быть null"<br/>&emsp;&emsp;}<br/>&emsp;]<br/>} |

#### Examples

| Input Type String                      | Answer  |
|:---------------------------------------|:--------|
| `1) => (`                              | `false` |
| `2) => ()[]{}`                         | `false` |
| `3) => ([{}])`                         | `false` |
| `4) => ([{a}])`                        | `true`  |
| `5) => ()[(])`                         | `false` |
| `6) => ({[}]())`                       | `false` |
| `7) => abc(def)`                       | `true`  |
| `8) => abc(def`                        | `false` |
| `9) => (abc(def)`                      | `false` |
| `10) => (text)`                        | `true`  |
| `11) => text`                          | `false` |
| `12) => ()`                            | `false` |
| `13) => (   )`                         | `false` |
| `14) => (  text  )`                    | `true`  |
| `15) => (text)(abc( )def)`             | `false` |
| `16) => (text)(abc(  def  )ghi (jkl))` | `true`  |
| `17) => (text)(abc(  def  )ghi ( ))`   | `false` |
| `18) => (text)(`                       | `false` |
| `19) => text)`                         | `false` |
| `20) => )text)`                        | `false` |
| `21) => )(text)`                       | `false` |

<p align="right">(<a href="#java-check-bracket">Back to Top</a>)</p>
</details>