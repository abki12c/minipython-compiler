# minipython compiler
This is a compiler built using SableCC for a subset of the Python Language called minipython

## Run Instrustions
To generate the minipython package using the grammar file use the following command:
```
sablecc minipython.grammar
```


To perform Syntax Analysis using some test cases use the following commands:
```
javac -cp . ParserTest.java
java -cp . ParserTest example.py
```
Before you run the example tests, remove the comments for each test case you want to test. The results will be the syntax errors identified, the location of the errors and the total number of errors.

To perform Lexical Analysis use the following commands:
```
javac -cp . LexerTest1.java
java -cp . LexerTest1 minipythonexample.py
```
The result will be the all the tokens

To show the abstract syntax tree use the following commands:
```
javac -cp . ASTTest1.java
java -cp . ASTTest1 minipythonexample.py
```


## BNF
Below is the BNF for the minipython language

![Image](https://github.com/user-attachments/assets/f8c346dd-ddc5-4bd9-b617-c8702553d158)
