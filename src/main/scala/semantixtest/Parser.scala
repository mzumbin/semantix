package semantixtest

object Parser {
  val withByte = """^([^\s]+) - - \[([^\[\]]+)\] "([^"]+)" (\d+) (\d+)$""".r
  val noByte = """^([^\s]+) - - \[([^\[\]]+)\] "([^"]+)" (\d+) -$""".r

  def parseLine(line: String): NasaRequestLog = {
    line match {
      case withByte(host, timestamp, request, http, bytes) => NasaRequestLog(host, timestamp, request, http.toInt, bytes.toInt)
      case noByte(host, timestamp, request, http) => NasaRequestLog(host, timestamp, request, http.toInt, 0)
      case _ => throw new Exception("error parsing data")
    }
  }
}