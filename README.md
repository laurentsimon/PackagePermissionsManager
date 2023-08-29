# Permissions Manager for Java Dependencies

This project aims to enable the definition of permissions for dependencies in a Java application. This will curtail exploitations of vulnerable dependencies used in an application.

## How to Build
1. Clone the repository
2. In the cloned repository, run `mvn clean package`.
The built agent jars will be located in the target directory.

## How to Use
1. First, modify the `sample-permissions.json` file with the appropriate permissions if necessary
2. Use the argument `-javaagent:<path-to-built-agent>` when running the java command. You may also need to pass the path to jars containing the ASM and Jackson dependencies.

