import scalaz._
import Scalaz._

/**
 * Here we describe two ways of wiring up beans using dependency injection using Reader monad.
 *  1.The AbstractDAO trait is extended by concrete DAOs.
 *  2.The Service trait provides methods that return Readers of methods that perform some action "given" the dependency.
 *  3.MyTestService extends the Service trait and provides methods to operate on the Readers returned from the DAO. So in effect the Service trait in this case
 *    acts as the "bridge" if you will
 *  4.Lastly we show two ways of getting the dependency injected from the outside ie. the stub
 */

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
  serviceObject.updateRecordsViaService("RECORD1").run(new concreteDAO {}) //Provide the dep. here and execute
  println(serviceObject.getRecordsViaService(new concreteDAO {})) //We provide that function the dep. here and execute it

  val serviceObject1 = new Service {} //Instantiate a new instance of the Service trait and use directly
  serviceObject1.updateRecordsViaDAO("RECORD2")(new concreteDAO1 {}) //Inject the concreteDao here.
  println(serviceObject1.getRecordsViaDAO(new concreteDAO1 {}))
}