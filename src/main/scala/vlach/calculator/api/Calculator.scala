package vlach.calculator.api

import org.parboiled2._

/**
 * Trait defining rules for DecimalNumbers
 */
trait DecimalNumberSupport {
  this: Parser =>
  def Digits = rule { oneOrMore(CharPredicate.Digit) }
  def Frac = rule { "." ~ Digits }
  def DecimalNumber = rule { optional("-") ~ Digits ~ optional(Frac) }
}

/**
 * Parse and transform prefix syntax to syntax supported by CalculatorStandard
 * Prefix syntax looks like this:
 * <operator> a
 * <operator> b
 * <operator> c
 * apply d
 * 
 * where a,b,c,d are numbers which can be negative|positive and supported are also integer and decimal numbers. 
 * 
 * Supported operators are: +, -, *, /
 * Every operator has defined aliases: 
 * 	for addition you can use plus|add|+ 
 * 	for substraction you can use minus|subtract|-
 * 	for multiplication you can use times|multiply|*
 * 	for division you can use divide|divided by|/
 * 
 * For example input:
 * 
 * add 1
 * plus 2
 * multiply -1.5
 * apply 6
 * 
 * will be transfromed to: Seq("6", "+1", "+2", "*-1.5")
 * 
 */
class PrefixSyntax(val input: ParserInput) extends Parser with DecimalNumberSupport {

  val delims = "\n"
  def Input = rule {
    WhiteSpace ~
      oneOrMore(Row).separatedBy(delims) ~
      Apply ~> ((stack, number) => number +: stack) ~
      WhiteSpace ~ EOI
  }

  def Row = rule {
    WhiteSpace ~ capture("plus" | "add" | "+") ~ WhiteSpace ~ Number ~ WhiteSpace ~> ((s, i) => "+" + i) |
      WhiteSpace ~ capture("minus" | "subtract" | "-") ~ WhiteSpace ~ Number ~ WhiteSpace ~> ((s, i) => "-" + i) |
      WhiteSpace ~ capture("times" | "multiply" | "*") ~ WhiteSpace ~ Number ~ WhiteSpace ~> ((s, i) => "*" + i) |
      WhiteSpace ~ capture("divided by" | "divide" | "/") ~ WhiteSpace ~ Number ~ WhiteSpace ~> ((s, i) => "/" + i)
  }

  def Apply = rule { delims ~ WhiteSpace ~ "apply" ~ WhiteSpace ~ Number }

  def Number = rule { capture(DecimalNumber) }

  def WhiteSpace: Rule0 = rule {
    zeroOrMore(anyOf(" \t"));
  }
}

/**
 * Simple calculator operating on input which look like this: a+b-c*d/f where letters a,b,c,d,f are numbers.
 * Supported numbers are
 *    - positive|negative
 *    - integer|decimal
 *
 * Supported operation are: +, -, *, /
 */
class CalculatorStandard(val input: ParserInput) extends Parser with DecimalNumberSupport {
  def InputLine = rule { Expression ~ EOI }

  def Expression: Rule1[Double] = rule {
    Number ~ zeroOrMore(
      '+' ~ Number ~> ((_: Double) + _) |
        '-' ~ Number ~> ((_: Double) - _) |
        '*' ~ Number ~> ((_: Double) * _) |
        '/' ~ Number ~> ((_: Double) / _))
  }

  def Number = rule { capture(DecimalNumber) ~> (_.toDouble) }
}

object Calculator {
  import java.util.Optional
  import scala.util._
  /**
   * Parse input String and if it will match syntax defined by PrefixSyntax, then it will 
   * be transformed into input into CalculatorStandard and then the resuklt of computation will 
   * be returned like Optional(Double). In case there is a syntax error, Optional.empty() will be returned.     
   */
  def process(input: String): Optional[java.lang.Double] = {
    // remove from input string windows file endings and replace them with linux file ensding
    val unixLineFeedInput = input.replaceAll("\r\n", "\n") 
    val result: Try[Double] = for {
      transformed: Seq[String] <- new PrefixSyntax(unixLineFeedInput).Input.run()
      res <- new CalculatorStandard(transformed.mkString).InputLine.run()
    } yield res
    result match {
      case Success(r) => Optional.of(r)
      case Failure(error) => Optional.empty()
    }
  }
}
