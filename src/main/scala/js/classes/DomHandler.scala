package js.classes

import org.scalajs.dom.raw.{Element, Event, Text}
import org.scalajs.dom.{Node, document, window}

import scala.scalajs.js
import scala.util.Try

class DomHandler {

  def alert(message: String): Unit = window.alert(message)

  def createDomElements(elementsInfo: List[ElementInfo]): List[Element] = {

    elementsInfo.map {
      case ElementInfo(name, idOption, classes, attributes, textOption) =>
        val element: Element = document.createElement(name)
        idOption.foreach { id => element.id = id }
        classes.foreach { className => element.classList.add(className) }
        attributes.foreach {
          case (name, value) => element.setAttribute(name, value)
        }
        textOption.map(createTextNode).foreach { textNode => element.appendChild(textNode) }
        element
    }
  }

  def createDOMElement(elementInfo: ElementInfo): Option[Element] = createDomElements(List(elementInfo)).headOption

  def createTextNode(text: String): Text = document.createTextNode(text)

  def appendElements(parent: Node, elementsInfo: List[ElementInfo]): List[Node] = {

    val elements: List[Element] = createDomElements(elementsInfo)
    elements.map { element => parent.appendChild(element) }
  }

  def appendElement(parent: Node, elementInfo: ElementInfo): Option[Node] = createDOMElement(elementInfo).map { node => parent.appendChild(node) }

  def appendElementsToDocument(elementsInfo: List[ElementInfo]): List[Node] = appendElements(body, elementsInfo)

  def appendElementToDocument(elementInfo: ElementInfo): Option[Node] = appendElement(body, elementInfo)

  def appendElementToDocument(node: Node): Try[Node] = appendElement(body, node)

  def appendElement(parent: Node, child: Node): Try[Node] = Try {

    parent.appendChild(child)
  }

  def body: Element = document.querySelector("body")

  def head: Element = document.querySelector("head")

  def addHandler[T <: Event](selector: String, event: String, listener: js.Function1[T, _]): Unit = {

    document.querySelector(selector).addEventListener(event, listener)
  }

  def querySelector(selector: String): Element = document.querySelector(selector)

  def writeln(args: Any*): Unit = args.map(_.toString).foreach(arg => document.writeln(arg))

  def write(args: Any*): Unit = args.map(_.toString).foreach(arg => document.write(arg))
}
