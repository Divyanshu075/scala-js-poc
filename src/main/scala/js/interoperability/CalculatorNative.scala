package js.interoperability

import scala.scalajs.js
import scala.scalajs.js.annotation._

@JSExportTopLevel("CalculatorNative")
class CalculatorNative(x: Int, y: Int) extends js.Object {

  def add: Int = x + y

  def absoluteDifference: Int = Math.abs(x - y)

  def product: Int = x * y

  def divide: Float = x.toFloat / y.toFloat

}
