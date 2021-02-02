# ErisExamples
Eris Terminal Examples, this contains a set of examples for use of the Eris Terminal Framework. Please view the [Eris Terminal Framework](https://github.com/brunomancuso/ErisTerminal) for better understanding how it works.

- st_examples.
    - Database example.
    - Third party example.
- st_restful
    - Restful example.

Each System Test, contains:
- test case source directory: 
- pom.xml and a build.bat (to build and copy dependecies)
- runtime directory, where all the configuration is located, here the test cases are executed.
     - runtime/bin, some useful unix commands
     - runtime/eris.yaml, eris configuration
     - runtime/terminal.bat, run the terminal.
     - runtime/modules.json, modules configuration file
      