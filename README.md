<p>
  <h1>40Chess</h1>
  <p>
    40Chess is an UCI-compatible chess engine written in Java
  </p>
</p>

### Installation

If you want ot skip the build process, go to the <a href="https://github.com/andrea321123/40Chess/releases">release</a> page.
Here are the steps to build the engine from sources:

1. Clone the repo
```sh
git clone https://github.com/andrea321123/40Chess
```
2. Compile with Maven
```sh
mvn clean compile
mvn package
```

Jar file should be found in target directory. <br>

## Usage

40Chess is only an engine, therefore it must be used with a chess GUI program. 40Chess is tested using the <a href=http://www.playwitharena.de/>Arena GUI</a>.
