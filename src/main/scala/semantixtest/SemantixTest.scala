package semantixtest

import java.text.SimpleDateFormat
import java.time.{LocalDateTime, ZoneId}

import org.apache.spark.sql.SQLContext._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{Dataset, SparkSession}
import org.apache.spark.sql.types._
object SemantixTest {

  def main(args: Array[String]) = {
    println("Test Started")
    val spark: SparkSession = SparkSession.builder.appName("Sematix Test")
      .config("spark.master", "local")
      .getOrCreate()

    val path = args(0)
    val procesDataSpark = new ProcesDataSpark(procesDataSpark,path)
    val totalRequestswithError: Long = procesDataSpark.totalRequestswithError()
    println(s" total Requests with Error:$totalRequestswithError")

    val maxHostWith404 = procesDataSpark.maxHostsWith404Response().mkString(",")
    println(s"hosts that had returned max number of 404:$maxHostWith404  ")


    val ErrorPerDay = procesDataSpark.erroResponsePerDay().mkString(",")
    println(s"number of erros groupeb by day: $ErrorPerDay")

    val totalOfBytes = procesDataSpark.totalOfBytesTrasmited()

    print(s"total of bytes transmited: $totalOfBytes")
  }
}

object ParseDate{
  private  val formater = new SimpleDateFormat("dd/MMM/YYYY:HH:mm:ss ZZZZ")
  def parseDate(dateString:String): LocalDateTime ={
    val instant = formater.parse(dateString).toInstant
    LocalDateTime.ofInstant(instant, ZoneId.systemDefault)
  }
  def getYearDateMonthString(date:String): String = date.substring(0,11)
}
case class NasaRequestLog(host:String, timestamp:String, request:String, httpResponse:Int, bytes:Int)





