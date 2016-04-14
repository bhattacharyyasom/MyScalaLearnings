package examples

object ContravarianceExample {
  abstract class Fruit { def name: String }
class Orange extends Fruit { def name = "Orange" }
class Apple extends Fruit { def name = "Apple" }
 
class Box[-F >: Apple](afruit:F) {
  def fruit:Apple=new Apple()
  //def contains(aFruit: F) = fruit.name.equals(aFruit.name)
}
 
/*class OrangeBox[Orange](orange: Orange) extends Box {
  def fruit: Orange = orange
}
 
class AppleBox[Apple](apple: Apple) extends Box {
  // subclass Apple instead of Fruit as return type is allowed.
  def fruit: Fruit = apple
  //override def contains(fruit: Fruit)={fruit.name.equals(fruit.name)}
}*/


object Test extends App{
  val box1:Box[Fruit] = new Box[Fruit](new Apple)
 // val box2:Box[Orange] = new Box[Orange](new Orange)
  
  testMethod(box1)
  //testMethod(box2)
  
  def testMethod(box:Box[Apple]):Box[Apple]={
    new Box[Apple](new Apple)
  }
}
}