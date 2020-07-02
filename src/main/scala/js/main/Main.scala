package js.main

import js.classes.{DomHandler, ElementInfo}
import js.interoperability.CalculatorNative
import org.scalajs.dom.html.Input
import org.scalajs.dom.raw.{Element, MouseEvent}

import scala.scalajs.js.annotation._
import scala.util.{Failure, Success}

object Main extends App {

  @JSExportTopLevel("domHandler")
  val domHandler: DomHandler = new DomHandler

  val element: Option[Element] = domHandler.createDOMElement(ElementInfo("div"))
  val element1: Option[Element] = domHandler.createDOMElement(ElementInfo("div"))
  val element2: Option[Element] = domHandler.createDOMElement(ElementInfo("div"))

  addElementToDocument(domHandler, element, List(
    ElementInfo(name = "h1", text = Some("Scala JS")),
    ElementInfo(name = "p", text = Some("Welcome to scala-js!"))))

  addElementToDocument(domHandler, element1, List(
    ElementInfo(name = "input", id = Some("input1")),
    ElementInfo(name = "input", id = Some("input2")),
    ElementInfo(name = "button", id = Some("button"), text = Some("add"))))

  addElementToDocument(domHandler, element2, List(
    ElementInfo(name = "input", id = Some("input3")),
    ElementInfo(name = "input", id = Some("input4")),
    ElementInfo(name = "button", id = Some("button"), text = Some("AbsoluteDifference"), attributes = Map("onclick" -> "handleSubtractEvent('input3', 'input4')"))))

  domHandler.addHandler("#button", "click", { event: MouseEvent =>
    domHandler.alert(new CalculatorNative(
      domHandler.querySelector("#input1").asInstanceOf[Input].value.toInt,
      domHandler.querySelector("#input2").asInstanceOf[Input].value.toInt).add.toString)
  })

  def addElementToDocument(domHandler: DomHandler, element: Option[Element], childNodesInfo: List[ElementInfo]): Unit =
    element match {

      case Some(node) =>
        domHandler.appendElements(node, childNodesInfo) match {

          case Nil => println("failed")

          case _ :: _ =>
            println("Elements added")
            domHandler.appendElementToDocument(node) match {
              case Success(value) => println("added to doc" + value)
              case Failure(exception) => println(exception.getMessage)
            }
        }

      case None => println("failed to add element to root")
    }
}
