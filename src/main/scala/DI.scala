//All future versions of DAO will extend this
trait AbstractDAO{
  def getRecords:String
  def updateRecords(records:String):Unit
}
//One concrete version
trait concreteDAO extends AbstractDAO{
  override def getRecords={"Here are records"}
  override def updateRecords(records:String){
    println("Updated "+records)
  }
}
//One concrete version
trait concreteDAO1 extends AbstractDAO{
  override def getRecords={"Records returned from DAO2"}
  override def updateRecords(records:String){
    println("Updated via DAO2"+records)
  }
}
//This trait just defines dependencies (in this case an instance of AbstractDAO) and defines operations based over that
trait service{
  this:AbstractDAO =>
    
  def updateRecordsViaDAO(record:String)={  
  updateRecords(record) 
  }
  def getRecordsViaDAO={
  getRecords
  }
}
object DI extends App{
  val wiredObject = new service with concreteDAO //injecting concrete DAO to the service and calling methods
  wiredObject.updateRecordsViaDAO("RECORD1")
  println(wiredObject.getRecordsViaDAO)
  
  val wiredObject1 = new service with concreteDAO1
  wiredObject1.updateRecordsViaDAO("RECORD2")
  println(wiredObject1.getRecordsViaDAO)
}