package example

import example.ParseDate.getYearDateMonthString
import org.apache.spark.sql.{Dataset, SparkSession}
import org.apache.spark.sql.functions.desc

class ProcesDataSpark(spark: SparkSession,dataPath:String) {

  import spark.implicits._
  private val lines: Dataset[String] = spark.read.textFile(dataPath)
  private val nasaData: Dataset[NasaRequestLog] = lines.map(Parser.parseLine(_))

  def distinctHostsCount(): Long ={
    nasaData.map(_.host).distinct().count()
  }

  val respondesWithError = nasaData.filter(_.httpResponse == 404).cache()

  def totalRequestswithError(): Long ={
    respondesWithError.count()
  }

 def maxHostsWith404Response(): Array[(String, Long)] ={
    respondesWithError
     .groupByKey(nasaData => nasaData.host)
     .count().map { case (host, count) => (host, count) }.toDF("host", "count")
     .orderBy(desc("count"))
     .as[(String, Long)]
     .take(5)
 }



  def erroResponsePerDay(): Array[ (String, Int)] ={
      respondesWithError.map(data => getYearDateMonthString(data.timestamp))
      .groupByKey(v=>v)
      .mapValues( v=> (v,1))
      .reduceGroups((a,b)=>(a._1,a._2+b._2))
      .map(_._2)
      .collect()
  }

  def totalOfBytesTrasmited(): Long ={
    nasaData.map(_.bytes.toLong).reduce((a,b)=>a+b)
  }





}