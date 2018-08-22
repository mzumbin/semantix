import java.time.temporal.TemporalAccessor
import java.time.{Instant, ZonedDateTime}

//"""""""
 val line = """uplherc.upl.com - - [01/Aug/1995:00:00:07-0400] "GET / HTTP/1.0" 304 0"""
val regex = """^([^\s]+) - - \[([^\[\]]+)\] "([^"]+)" (\d+) (\d+)$""".r
case class NasaData(host: String)

import java.time.format.DateTimeFormatter

val formatter = DateTimeFormatter.ofPattern("dd/MMM/YYYY:HH:mm:ss Z")
val t: TemporalAccessor = formatter.parse("01/Aug/1995:00:00:07 -0400")
//ZonedDateTime.from(t)
//ZonedDateTime.parse("01/Aug/1995:00:00:07 -0400",formatter)
line match {
  case regex(host,timestamp,request,http,bytes) => (host,timestamp,request,http,bytes)
}