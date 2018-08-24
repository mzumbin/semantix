import java.text.SimpleDateFormat
import java.time.temporal.TemporalAccessor
import java.time.{Instant, ZonedDateTime}
import java.util.Date

//"""""""
 val line = """uplherc.upl.com - - [01/Aug/1995:00:00:07-0400] "GET / HTTP/1.0" 304 0"""
val regex = """^([^\s]+) - - \[([^\[\]]+)\] "([^"]+)" (\d+) (\d+)$""".r
case class NasaData(host: String)

import java.time.format.DateTimeFormatter

val formatter = DateTimeFormatter.ofPattern("dd/MMM/YYYY:HH:mm:ss ZZZ")
val t: TemporalAccessor = formatter.parse("01/Aug/1995:00:00:07 -0400")
val form = new SimpleDateFormat("dd/MMM/YYYY:HH:mm:ss ZZZZ")
val date: Date = form.parse("01/Aug/1995:00:00:07 -0400")
val dateString =  form.format(form.parse("01/Aug/1995:00:00:07 -0400"))
date.toInstant
import java.time.LocalDateTime
import java.time.ZoneId

object ParseDate{
 private  val formater = new SimpleDateFormat("dd/MMM/YYYY:HH:mm:ss ZZZZ")
 def apply(dateString:String)={
   val instant = formater.parse(dateString).toInstant
   LocalDateTime.ofInstant(instant, ZoneId.systemDefault)
 }
}

ParseDate("01/Aug/1995:00:00:07 -0400").getMonthValue
"01/Aug/1995:00:00:07 -0400".substring(0,11)

//ZonedDateTime.from(t)
//Instant.parse("01/Aug/1995:00:00:07 -0400",formatter)
line match {
  case regex(host,timestamp,request,httpResponse,bytes) => (host,timestamp,request,httpResponse,bytes)
}