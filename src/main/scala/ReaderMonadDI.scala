import scalaz._
import Scalaz._

object ReaderMonadDI {
trait AbstractDAO{
  def getRecords:String
  def updateRecords(records:String):Unit
}

trait concreteDAO extends AbstractDAO{
  override def getRecords={"Here are records"}
  override def updateRecords(records:String){
    println("Updated "+records)
  }
}

trait concreteDAO1 extends AbstractDAO{
  override def getRecords={"Records returned from DAO2"}
  override def updateRecords(records:String){
    println("Updated via DAO2"+records)
  }
}

trait service{
      
  def updateRecordsViaDAO(record:String)={  
  Reader((dao:AbstractDAO) => dao.updateRecords(record)) //Returns a function that updates records given a DAO implementation 
  }
  def getRecordsViaDAO={
  Reader((dao:AbstractDAO) => dao.getRecords)   // returns a Reader wrapping a function that gets records given a DAO implementation
  }
}

class myTestService extends service{
  def updateRecordsViaService(record:String)={
    for{
    x <- getRecordsViaDAO
    y <- updateRecordsViaDAO(x)
    }yield x
  }
  def getRecordsViaService={
    getRecordsViaDAO
  }
}
object ReaderMonadDI extends App{
  
  val wiredObject = new myTestService //with concreteDAO //Wiring up the DAOs to the service here
  wiredObject.updateRecordsViaService("RECORD1")
  println(wiredObject.getRecordsViaService)
  
  val wiredObject1 = new service with concreteDAO1//Wiring
  wiredObject1.updateRecords("RECORD2")
  println(wiredObject1.getRecords)
}
}