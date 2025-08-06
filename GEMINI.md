# Gemini Guidelines for the Airbag Project

This document provides guidelines for interacting with the Airbag project.

## About This Project

Airbag is a Java library for testing ANTLR grammars. It helps compare token lists and generate human-readable diffs to verify lexer and parser behavior.

- **Language:** Java 21
- **Build Tool:** Maven
- **Key Dependencies:** ANTLR4, JUnit 5, java-diff-utils

## Build Commands

- **Clean, build, and install:** `mvn clean install`
- **Run tests:** `mvn test`
- **Generate ANTLR sources:** `mvn generate-sources`

The build process uses the `antlr4-maven-plugin` to generate Java code from the `.g4` grammar files.

## Code Generation

- **Main Grammars:**
    - Source: `src/main/antlr4/`
    - Output: `src/main/java/io/github/agoss94/airbag/parser/`
- **Test Grammars:**
    - Source: `src/test/antlr4/`
    - Output: `src/test/java/io/github/agoss94/airbag/grammar/`

When modifying grammar files (`.g4`), you may need to run `mvn generate-sources` to regenerate the corresponding Java code. 
Update and check javadoc everytime you alter any code and implementation. Every generated Code should be fully documented. 
Each class should have javadoc on a class level, each field should be documented and every method and constructor should have 
documentation.

## Key Files

- `pom.xml`: Defines the project structure, dependencies, and build process.
- `src/main/java/io/github/agoss94/airbag/Airbag.java`: Core class of the library.
- `src/main/antlr4/`: Contains the ANTLR grammars for the project.
- `src/test/java/`: Contains the unit tests.
- `src/test/antlr4/`: Contains ANTLR grammars used for testing purposes.

## Generated Code

Do not modify the files in the following directories directly, as they are generated from the ANTLR grammar files:
- `src/main/java/io/github/agoss94/airbag/parser/`
- `src/test/java/io/github/agoss94/airbag/grammar/`
