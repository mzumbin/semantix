package example

import org.apache.spark
import org.apache.spark.sql.SparkSession

object Hello extends Greeting with App {

  val spark: SparkSession = SparkSession.builder.appName("Simple Application").getOrCreate()
  spark.read.textFile("README.md")
  println(greeting)
}

trait Greeting {
  lazy val greeting: String = "hello"
}


object Parser{
  val regex = """^([^\s]+) - - \[([^\[\]]+)\] "([^"]+)" (\d+) (\d+)$""".r

  def parseLine

  line match {
    case regex(host,timestamp,request,http,bytes) => (host,timestamp,request,http,bytes)
  }
}