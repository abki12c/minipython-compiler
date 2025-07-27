# minipython compiler
This is a compiler built using SableCC for a subset of the Python Language called minipython

## Run Instrustions
To generate the minipython package using the grammar file use the following command:
```
sablecc minipython.grammar
```
The minipython package is already included in this repository


To run some tests included in the example.py use the following commands:
```
javac -cp . ParserTest.java
java -cp . ParserTest example.py
```
Before you run the example tests, remove the comments for each test case you want to test

## BNF
Below is the BNF for the minipython language

![Image](https://github.com/user-attachments/assets/f8c346dd-ddc5-4bd9-b617-c8702553d158)
