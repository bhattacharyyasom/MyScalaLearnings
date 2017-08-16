import scalaz._
import Scalaz._

trait Foo1[A] {
  type B
  def value: B
}



object Foo1 extends App{
  
  type Aux[A,B0]=Foo1[A]{type B=B0}
  
  
  implicit object Foo1Int extends Foo1[Int]{
  type B = String
  def value = "2"
}
  implicit object Foo1String extends Foo1[String]{
  type B = Int
  def value=2
}
  
  def doOperation[T,R](t:T)(implicit f:Foo1.Aux[T,R]):R = f.value
  
  doOperation("")
  doOperation(2)
 
  //Foo1(2) proceeds like so ,
  //2 resolves to a Int. So compiler looks up Foo1 Int . The type R resolves to the type B in the implicit object . So R becomes String.
   
  
  
}