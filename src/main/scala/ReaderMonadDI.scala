import scalaz._
import Scalaz._

object ReaderMonadDITest extends App {
  trait AbstractDAO {
    def getRecords: String
    def updateRecords(records: String): Unit
  }
  trait concreteDAO extends AbstractDAO {
    override def getRecords = { "Here are records from DAO1" }
    override def updateRecords(records: String) {
      println("Updated via DAO1" + records)
    }
  }
  trait concreteDAO1 extends AbstractDAO {
    override def getRecords = { "Here are records from DAO2" }
    override def updateRecords(records: String) {
      println("Updated via DAO2" + records)
    }
  }
  trait Service {
    def updateRecordsViaDAO(record: String) = {
      Reader((dao: AbstractDAO) => dao.updateRecords(record)) //Returns a function that updates records given a DAO implementation 
    }
    def getRecordsViaDAO = {
      Reader((dao: AbstractDAO) => dao.getRecords) // Returns a Reader wrapping a function that gets records given a DAO implementation
    }
  }

  class myTestService extends Service {
    def updateRecordsViaService(record: String) = {
      for {
        x <- getRecordsViaDAO
        y <- updateRecordsViaDAO(x)
      } yield x
    }
    def getRecordsViaService = {
      getRecordsViaDAO
    }
  }

  //Stub to test out DI
  val serviceObject = new myTestService
  serviceObject.updateRecordsViaService("RECORD1")(new concreteDAO {}) //Provide the dep. here and execute
  println(serviceObject.getRecordsViaService(new concreteDAO {})) //We provide that function the dep. here and execute it

  val serviceObject1 = new Service {}
  serviceObject1.updateRecordsViaDAO("RECORD2")(new concreteDAO1 {})
  println(serviceObject1.getRecordsViaDAO(new concreteDAO1 {}))
}