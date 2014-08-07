calculator-with-prefix-syntax
=============================

Calculator for prefix syntax notation.


Parse and transform prefix syntax notation.
Prefix syntax notation looks like this:
```
<operator> a
<operator> b
<operator> c
apply d
```
 
where `a`, `b`, `c`, `d` are numbers which can be negative or positive and supported are also integer and decimal numbers. 
 
Supported operators are: `+`, `-`, `*`, `/`
Every operator has defined aliases: 
- 	for addition you can use `plus`. `add`, `+` 
- 	for substraction you can use `minus`, `subtract`, `-`
- 	for multiplication you can use `times`, `multiply`, `*`
- 	for division you can use `divided by`, `divide`, `/`
 

You can build and run the code either with sbt:

    $ sbt "run input.txt"

Or with maven:

    $ mvn compile exec:java -Dexec.mainClass="vlach.calculator.CalculatorService" -Dexec.args="input.txt"

Or you can build the program with a maven and then run it like a jar:

    $ mvn package
    $ java -cp calculator-1.0-jar-with-dependencies.jar vlach.calculator.CalculatorService input.txt 


Requirements:

* JDK 8
* SBT 0.13.5 (for running with sbt)
* Maven (for running with maven)
