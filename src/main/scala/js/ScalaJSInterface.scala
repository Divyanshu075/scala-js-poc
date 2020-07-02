package js

import js.interoperability.CalculatorNative
import org.scalajs.dom._
import org.scalajs.dom.html.Input

import scala.scalajs.js.annotation._

object ScalaJSInterface {

  @JSExportTopLevel("handleSubtractEvent")
  def handleSubtractEvent(id1: String, id2: String): Unit = {
    val input1: Int = document.querySelector(s"#$id1").asInstanceOf[Input].value.toInt
    val input2: Int = document.querySelector(s"#$id2").asInstanceOf[Input].value.toInt
    val output: String = new CalculatorNative(input1, input2).absoluteDifference.toString
    window.alert(output)
  }

}
