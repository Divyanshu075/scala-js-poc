package js.classes

case class ElementInfo(name: String,
                       id: Option[String] = None,
                       classes: List[String] = List.empty,
                       attributes: Map[String, String] = Map.empty,
                       text: Option[String] = None)
