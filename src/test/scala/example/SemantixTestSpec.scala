package example

import org.apache.spark.sql.SparkSession
import org.scalatest._

class Tests extends FunSuite with BeforeAndAfterEach with Matchers {

  var sparkSession : SparkSession = _
  var procesDataSpark: ProcesDataSpark = _
  override def beforeEach() {
    sparkSession = SparkSession.builder().appName("udf testings")
      .master("local")
      .config("", "")
      .getOrCreate()
    procesDataSpark = new ProcesDataSpark(sparkSession,"text")
  }


  test("ProcesDataSpark should calc the correct number of distinc hosts"){
    //your unit test assert here like below
     val result  = procesDataSpark.distinctHostsCount()
     result should be(12)

  }

  test("ProcesDataSpark should calc the correct total number of returned with error"){
    val result = procesDataSpark.totalRequestswithError
    result should be (6)
  }

  test("ProcesDataSpark should calc   hosts with max  number of returned  errors"){
    val result = procesDataSpark.maxHostsWith404Response
    result should contain theSameElementsAs( List(("199.117.206.10",4),( "199.117.206.33",2)))
  }

  test("ProcesDataSpark should calc the numer of error returned per day"){
    val result = procesDataSpark.erroResponsePerDay()
    result should contain theSameElementsAs(List(("31/Aug/1995",1), ("30/Aug/1995",4), ("29/Aug/1995",1)))
  }

  test("5"){
    val result = procesDataSpark.totalOfBytesTrasmited()
    result should be (93262)
  }

  override def afterEach() {
    sparkSession.stop()
  }
}