# GWT support for Vavr

### Running the example

* Install all necessary artifacts by running the following command in the main project's directory:

```
mvn clean install -DskipTests=true -Dmaven.javadoc.skip=true
```

* Start GWT super development mode inside the `vavr-gwt-example` directory by executing:

```
mvn gwt:devmode
```

* Go to `http://localhost:8888/VavrExample/index.html` to see the example in action.