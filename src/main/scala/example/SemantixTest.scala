package example

import java.text.SimpleDateFormat
import java.time.{LocalDateTime, ZoneId}

import org.apache.spark.sql.SQLContext._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{Dataset, SparkSession}
import org.apache.spark.sql.types._
object SemantixTest {

  import ParseDate._

  def main(args: Array[String]) = {
    val spark: SparkSession = SparkSession.builder.appName("Sematix Test")
      .config("spark.master", "local")
      .getOrCreate()
    import spark.implicits._
    val lines: Dataset[String] = spark.read.textFile("text")
    val nasaData: Dataset[NasaRequestLog] = lines.map(Parser.parseLine(_))
    val distinctHosts = nasaData.map(_.host).distinct().count()
    val respondesWithError = nasaData.filter(_.httpResponse == 404).cache()

    val totalRequestswithError: Long = respondesWithError.count()

    val maxHostWith404 = respondesWithError
      .groupByKey(nasaData => nasaData.host)
      .count().map { case (host, count) => (host, count) }.toDF("host", "count")
      .orderBy(desc("count"))
      .as[(String, Long)]
      .take(5)



    val ErrorPerDay = respondesWithError.map(data => getYearDateMonthString(data.timestamp))
      .groupByKey(v=>v)
      .mapValues( v=> (v,1))
      .reduceGroups((a,b)=>(a._1,a._2+b._2))
      .take(3)


    val totalOfBytes =nasaData.map(_.bytes.toLong).reduce((a,b)=>a+b)

    println("d")
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





