package vlach.calculator.api

import collection.mutable.Stack
import org.scalatest._
import java.util.Optional

class CalculatorStandardSuite extends FlatSpec with Matchers with TryValues {

  "A CalculatorStandard" should "add two numbers" in {
    val input = "1+1"
    val result = new CalculatorStandard(input).InputLine.run()
    result.success.value should be(2.0)
  }

  it should "add two decimal numbers" in {
    val input = "1.5+1.2"
    val result = new CalculatorStandard(input).InputLine.run()
    result.success.value should be(2.7)
  }

  it should "add two negative numbers" in {
    val input = "-1+-1"
    val result = new CalculatorStandard(input).InputLine.run()
    result.success.value should be(-2.0)
  }

  it should "add two negative decimal numbers" in {
    val input = "-1.5+-1.2"
    val result = new CalculatorStandard(input).InputLine.run()
    result.success.value should be(-2.7)
  }

  it should "add negative number and positive number" in {
    val input = "1+-2"
    val result = new CalculatorStandard(input).InputLine.run()
    result.success.value should be(-1.0)
  }

  it should "add negative decimal number and positive decimal number" in {
    val input = "1.5+-2.0"
    val result = new CalculatorStandard(input).InputLine.run()
    result.success.value should be(-0.5)
  }

  it should "add negative decimal number and positive integer number" in {
    val input = "1.5+-2"
    val result = new CalculatorStandard(input).InputLine.run()
    result.success.value should be(-0.5)
  }

  it should "subtract two numbers" in {
    val input = "1-1"
    val result = new CalculatorStandard(input).InputLine.run()
    result.success.value should be(0.0)
  }

  it should "subtract two decimal numbers" in {
    val input = "1.7-1.2"
    val result = new CalculatorStandard(input).InputLine.run()
    result.success.value should be(0.5)
  }

  it should "subtract two negative numbers" in {
    val input = "-1--1"
    val result = new CalculatorStandard(input).InputLine.run()
    result.success.value should be(0.0)
  }

  it should "subtract two negative decimal numbers" in {
    val input = "-1.7--1.2"
    val result = new CalculatorStandard(input).InputLine.run()
    result.success.value should be(-0.5)
  }

  it should "subtract negative number and positive number" in {
    val input = "1--2"
    val result = new CalculatorStandard(input).InputLine.run()
    result.success.value should be(3.0)
  }

  it should "subtract negative decimal number and positive decimal number" in {
    val input = "1.5--2.0"
    val result = new CalculatorStandard(input).InputLine.run()
    result.success.value should be(3.5)
  }

  it should "subtract negative decimal number and positive integer number" in {
    val input = "1.5--2"
    val result = new CalculatorStandard(input).InputLine.run()
    result.success.value should be(3.5)
  }

  it should "multiply two numbers" in {
    val input = "2*3"
    val result = new CalculatorStandard(input).InputLine.run()
    result.success.value should be(6.0)
  }

  it should "multiply two decimal numbers" in {
    val input = "1.5*1.2"
    val result = new CalculatorStandard(input).InputLine.run()
    result.success.value should be(1.8 +- 1e-15)
  }

  it should "multiply two negative numbers" in {
    val input = "-2*-3"
    val result = new CalculatorStandard(input).InputLine.run()
    result.success.value should be(6.0)
  }

  it should "multiply negative number and positive number" in {
    val input = "-2*3"
    val result = new CalculatorStandard(input).InputLine.run()
    result.success.value should be(-6.0)
  }

  it should "multiply negative decimal number and positive decimal number" in {
    val input = "-1.5*2.0"
    val result = new CalculatorStandard(input).InputLine.run()
    result.success.value should be(-3.0)
  }

  it should "multiply negative decimal number and positive integer number" in {
    val input = "-1.5*2"
    val result = new CalculatorStandard(input).InputLine.run()
    result.success.value should be(-3.0)
  }

  it should "divide two numbers" in {
    val input = "4/2"
    val result = new CalculatorStandard(input).InputLine.run()
    result.success.value should be(2.0)
  }

  it should "divide two decimal numbers" in {
    val input = "1.5/1.2"
    val result = new CalculatorStandard(input).InputLine.run()
    result.success.value should be(1.25)
  }

  it should "divide two negative numbers" in {
    val input = "-4/-2"
    val result = new CalculatorStandard(input).InputLine.run()
    result.success.value should be(2.0)
  }

  it should "divide positive number with negative number" in {
    val input = "4/-2"
    val result = new CalculatorStandard(input).InputLine.run()
    result.success.value should be(-2.0)
  }

  it should "divide negative number and positive number" in {
    val input = "-4/2"
    val result = new CalculatorStandard(input).InputLine.run()
    result.success.value should be(-2.0)
  }

  it should "divide negative decimal number and positive decimal number" in {
    val input = "-1.5/2.0"
    val result = new CalculatorStandard(input).InputLine.run()
    result.success.value should be(-0.75)
  }

  it should "divide negative decimal number and positive integer number" in {
    val input = "-2.1/3"
    val result = new CalculatorStandard(input).InputLine.run()
    result.success.value should be(-0.7 +- 1e-15)
  }

  it should "return infinite when divided by zero" in {
    val input = "4/0"
    val result = new CalculatorStandard(input).InputLine.run()
    result.success.value.isPosInfinity should be(true)
    result.success.value should be(Double.PositiveInfinity)
  }

  it should "return zero when zero is divided by any other number (other than zero)" in {
    val input = "0/4"
    val result = new CalculatorStandard(input).InputLine.run()
    result.success.value should be(0.0)
  }
}