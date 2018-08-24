package semantixtest


import org.apache.spark.sql.{ SparkSession}

object SemantixTest {

  def main(args: Array[String]) = {
    println("Test Started")
    val spark: SparkSession = SparkSession.builder.appName("Semantix Test")
      .config("spark.master", "local")
      .getOrCreate()

    val path = args(0)
    val procesDataSpark = new ProcesDataSpark(spark,path)
    val totalRequestswithError: Long = procesDataSpark.totalRequestswithError()
    println(s" total Requests with Error:$totalRequestswithError")

    val maxHostWith404 = procesDataSpark.maxHostsWith404Response().mkString(",")
    println(s"hosts that had returned max number of 404:$maxHostWith404  ")


    val ErrorPerDay = procesDataSpark.erroResponsePerDay().mkString(",")
    println(s"number of erros groupeb by day: $ErrorPerDay")

    val totalOfBytes = procesDataSpark.totalOfBytesTrasmited()

    println(s"total of bytes transmited: $totalOfBytes")
    spark.stop()
  }
}








