# Gilded Rose starting position in Java

## Run the Unit Tests (`./src/test/java/com/gildedrose/GildedRoseTest.java`) from Command-Line

```
./gradlew -q test
```

## Run the Text Fixture Test (`./src/test/java/com/gildedrose/TexttestFixture.java`) from Command-Line

```
./gradlew -q texttest
```

### You Can Specify Number of Days

For e.g. 10 days:

```
./gradlew -q texttest --args 10
```

## Troubleshooting

Failures like

```
java.lang.IllegalAccessError: class org.gradle.internal.compiler.java.ClassNameCollector
```

probably mean that there is some incompatibility between Gradle version set in `./gradle/wrapper/gradle-wrapper.properties` (this project is using `6.5`), and the JDK version that is being used, so you should either:
- Change the Gradle version in `./gradle/wrapper/gradle-wrapper.properties`
- Change the JDK version:
  - Change `JAVA_HOME` environment variable with a path to a JDK that is compatible with the current Gradle version
  - Run the tests as:
  ```
  ./gradlew -q texttest -Dorg.gradle.java.home={COMPATIBLE_JDK_VERSION_PATH}
  ```
  e.g. on my machine I was running the tests using:
  ```
  ./gradlew -q texttest -Dorg.gradle.java.home=$HOME/.jdks/liberica-11.0.18
  ```
