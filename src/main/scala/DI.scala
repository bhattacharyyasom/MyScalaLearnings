package examples
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
  this:AbstractDAO =>
    
  def updateRecordsViaDAO(record:String)={  
  updateRecords(record) 
  }
  def getRecordsViaDAO={
  getRecords
  }
}


object DI extends App{
  val wiredObject = new service with concreteDAO //Wiring up the DAOs to the service here
  wiredObject.updateRecords("RECORD1")
  println(wiredObject.getRecords)
  
  val wiredObject1 = new service with concreteDAO1//Wiring
  wiredObject1.updateRecords("RECORD2")
  println(wiredObject1.getRecords)
}