package example

import org.scalajs.dom
import org.scalajs.dom.document
import scalajs.js
import scala.scalajs.js.annotation.JSExportTopLevel
import org.scalajs.dom.html

object Hello {
  def main(args: Array[String]): Unit = {
    println("Hello world.")
    for (i <- 1 to 10) println(i)

    document.getElementById("title").innerHTML = "Scala.js"
    document.getElementById("content").innerHTML = "This is a tutorial on using Scala.js."

    appendParagraph(document.getElementById("content"), "This is a new paragraph.")
  }

  def appendParagraph(target: dom.Node, text: String): Unit = {
    val p = document.createElement("p")
    val textNode = document.createTextNode(text)
    p.appendChild(textNode)
    target.appendChild(p)
  }
}