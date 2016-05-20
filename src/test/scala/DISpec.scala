import org.scalatest._
import basetests.BasicUnitTest

class DISpec extends BasicUnitTest{
  "An instance of ConcreteDAO" must "return String when getRecords is invoked" in{
    assert(new concreteDAO{}.getRecords.equalsIgnoreCase("Here are records"))
  } 
  
  
}