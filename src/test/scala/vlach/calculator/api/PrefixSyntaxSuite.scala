package vlach.calculator.api

import collection.mutable.Stack
import org.scalatest._
import java.util.Optional

class PrefixSyntaxSuite extends FlatSpec with Matchers with TryValues {

  "A PrefixSyntax transformer" should "support 'add' operator" in {
    val input = """|add 1
                   |apply 2""".stripMargin
    val result = new PrefixSyntax(input).Input.run()
    result.success.value should be(Seq("2", "+1"))
  }

  it should "support 'plus' operator" in {
    val input = """|plus 1
			             |apply 2""".stripMargin
    val result = new PrefixSyntax(input).Input.run()
    result.success.value should be(Seq("2", "+1"))
  }

  it should "support '+' operator" in {
    val input = """|+ 1
                   |apply 2""".stripMargin
    val result = new PrefixSyntax(input).Input.run()
    result.success.value should be(Seq("2", "+1"))
  }

  it should "support 'minus' operator" in {
    val input = """|minus 1
                   |apply 2""".stripMargin
    val result = new PrefixSyntax(input).Input.run()
    result.success.value should be(Seq("2", "-1"))
  }

  it should "support 'subtract' operator" in {
    val input = """|subtract 1
                   |apply 2""".stripMargin
    val result = new PrefixSyntax(input).Input.run()
    result.success.value should be(Seq("2", "-1"))
  }

  it should "support '-' operator" in {
    val input = """|- 1
                   |apply 2""".stripMargin
    val result = new PrefixSyntax(input).Input.run()
    result.success.value should be(Seq("2", "-1"))
  }

  it should "support 'times' operator" in {
    val input = """|times 1
                   |apply 2""".stripMargin
    val result = new PrefixSyntax(input).Input.run()
    result.success.value should be(Seq("2", "*1"))
  }

  it should "support 'multiply' operator" in {
    val input = """|multiply 1
                   |apply 2""".stripMargin
    val result = new PrefixSyntax(input).Input.run()
    result.success.value should be(Seq("2", "*1"))
  }

  it should "support '*' operator" in {
    val input = """|* 1
                   |apply 2""".stripMargin
    val result = new PrefixSyntax(input).Input.run()
    result.success.value should be(Seq("2", "*1"))
  }

  it should "support 'divided by' operator" in {
    val input = """|divided by 1
                   |apply 2""".stripMargin
    val result = new PrefixSyntax(input).Input.run()
    result.success.value should be(Seq("2", "/1"))
  }

  it should "support 'divide' operator" in {
    val input = """|divide 1
                   |apply 2""".stripMargin
    val result = new PrefixSyntax(input).Input.run()
    result.success.value should be(Seq("2", "/1"))
  }

  it should "support '/' operator" in {
    val input = """|/ 1
                   |apply 2""".stripMargin
    val result = new PrefixSyntax(input).Input.run()
    result.success.value should be(Seq("2", "/1"))
  }

  it should "support negativ numbers" in {
    val input = """|plus -1
                   |apply -2""".stripMargin
    val result = new PrefixSyntax(input).Input.run()
    result.success.value should be(Seq("-2", "+-1"))
  }

  it should "support decimal numbers" in {
    val input = """|plus 1.2
                   |apply 2.3""".stripMargin
    val result = new PrefixSyntax(input).Input.run()
    result.success.value should be(Seq("2.3", "+1.2"))
  }

  it should "support negative decimal numbers" in {
    val input = """|plus -1.2
                   |apply -2.3""".stripMargin
    val result = new PrefixSyntax(input).Input.run()
    result.success.value should be(Seq("-2.3", "+-1.2"))
  }

  it should "support syntax without white spaces between operator and operand" in {
    val input = """|plus1.2
			  |apply3.4""".stripMargin
    val result = new PrefixSyntax(input).Input.run()
    result.success.value should be(Seq("3.4", "+1.2"))
  }

  it should "support syntax with arbitrary white spaces (tab or space) between operator and operand" in {
    val input = """|plus             1.2
                   |apply                      3.4""".stripMargin
    val result = new PrefixSyntax(input).Input.run()
    result.success.value should be(Seq("3.4", "+1.2"))
  }

  it should "support syntax with white spaces (tab or space) at the beginning of lines" in {
    val input = """|           plus1.2
                   |           apply3.4""".stripMargin
    val result = new PrefixSyntax(input).Input.run()
    result.success.value should be(Seq("3.4", "+1.2"))
  }

  it should "support syntax with white spaces (tab or space) at the end of lines" in {
    val input = """|plus1.2          
                   |apply3.4             """.stripMargin
    val result = new PrefixSyntax(input).Input.run()
    result.success.value should be(Seq("3.4", "+1.2"))
  }
}