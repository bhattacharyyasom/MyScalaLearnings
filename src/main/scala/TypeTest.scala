class Foo
{
  class bar
}

trait test{
  trait test2
  def testbar:test2
}

trait Status
trait Open extends Status
trait Closed extends Status

trait Door[S <: Status]

object Door{
  def apply[S <: Status]()={
    new Door[S]{}
  }
  def open[S <: Closed](door :Door[S])=Door[Open]
  def closed[S<:Open](door:Door[S])=Door[Closed]
}

object TypeTest extends App{
  val foo1 = new Foo
  val foo2 = new Foo
   
  val atype:Foo#bar = new foo2.bar  //foo#bar means any bar type inside any Foo
  val btype:Foo#bar = new foo1.bar
   
  val a:foo1.bar = new foo1.bar
  val b:foo2.bar = new foo2.bar
  //val c:foo1.bar=new foo2.bar   this line will not compile
  
  //-----------------------------------------------------------------//
  def testFoo(f: test) = f.testbar 
  //-----------------------------------------------------------------//
  val closedDoor = Door[Closed]
  val openDoor = Door.open(closedDoor)
  val closedAgainDoor = Door.closed(openDoor)
  
  //Door.closed(closedDoor)
  
 implicitly[Int =:= Int]
}

