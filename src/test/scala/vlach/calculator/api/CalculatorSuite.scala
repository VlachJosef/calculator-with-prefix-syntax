package vlach.calculator.api

import collection.mutable.Stack
import org.scalatest._
import java.util.Optional

class CalculatorSuite extends FlatSpec with Matchers {

  "A Calculator" should "add two numbers with 'add' operator" in {
    val input = """|add 1
                   |apply 1""".stripMargin
    val result = Calculator.process(input)
    result should be(Optional.of(2.0))
  }

  it should "add two numbers with 'plus' operator" in {
    val input = """|plus 1
                   |apply 1""".stripMargin
    val result = Calculator.process(input)
    result should be(Optional.of(2.0))
  }

  it should "add two numbers with '+' operator" in {
    val input = """|+ 1
                   |apply 1""".stripMargin
    val result = Calculator.process(input)
    result should be(Optional.of(2.0))
  }

  it should "subtract two numbers with 'subtract' operator" in {
    val input = """|subtract 1	
                   |apply 1""".stripMargin
    val result = Calculator.process(input)
    result should be(Optional.of(0.0))
  }

  it should "subtract two numbers with 'minus' operator" in {
    val input = """|minus 1
                   |apply 1""".stripMargin
    val result = Calculator.process(input)
    result should be(Optional.of(0.0))
  }

  it should "subtract two numbers with '-' operator" in {
    val input = """|- 1
                   |apply 1""".stripMargin
    val result = Calculator.process(input)
    result should be(Optional.of(0.0))
  }

  it should "multiply two numbers with 'multiply' operator" in {
    val input = """|multiply 2
                   |apply 2""".stripMargin
    val result = Calculator.process(input)
    result should be(Optional.of(4.0))
  }

  it should "multiply two numbers with 'times' operator" in {
    val input = """|times 2
                   |apply 2""".stripMargin
    val result = Calculator.process(input)
    result should be(Optional.of(4.0))
  }
  
  it should "multiply two numbers with '*' operator" in {
    val input = """|* 2
                   |apply 2""".stripMargin
    val result = Calculator.process(input)
    result should be(Optional.of(4.0))
  }

  it should "divide two numbers with 'divide' operator" in {
    val input = """|divide 4
                   |apply 2""".stripMargin
    val result = Calculator.process(input)
    result should be(Optional.of(0.5))
  }

  it should "divide two numbers with 'divided by' operator" in {
    val input = """|divided by 4
                   |apply 2""".stripMargin
    val result = Calculator.process(input)
    result should be(Optional.of(0.5))
  }

  it should "divide two numbers with '/' operator" in {
    val input = """|/ 4
                   |apply 2""".stripMargin
    val result = Calculator.process(input)
    result should be(Optional.of(0.5))
  }

  it should "support addition of negativ numbers" in {
    val input = """|plus -1
                   |apply -1""".stripMargin
    val result = Calculator.process(input)
    result should be(Optional.of(-2.0))
  }

  it should "return infinity when one of operation is division by zero" in {
    val input = """|plus 1
                   |/ 0
    		       |minus 1
                   |apply 5""".stripMargin
    val result = Calculator.process(input)
    result should be(Optional.of(Double.PositiveInfinity))
  }

  it should "return empty result when given a wrong input" in {
    val input = """nothing"""
    val result = Calculator.process(input)
    result should be(Optional.empty())
  }

  it should "return empty result when missing apply input" in {
    val input = """|add 2""".stripMargin
    val result = Calculator.process(input)
    result should be(Optional.empty())
  }
}