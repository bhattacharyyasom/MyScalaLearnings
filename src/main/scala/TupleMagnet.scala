/* Implementation of Magnet Pattern using Tuples as an example*/
package examples

/*We need this trait to define the `apply` method. So when inside the function `f` we get a `TupleMagnet` we can
 * do a invocation `magnet()` we run the `apply` method of the corresponding `TupleMagnet`. So instead of having multiple 
 * overloaded versions of a function we have a single hinge ie. the `apply` method in the `TupleMagnet` */
sealed trait TupleMagnet {
  def apply():Unit
}

object TupleMagnet{
  //This implicit conversion runs only when a Tuple1 needs conversion
  implicit def tuple1Magnet[A](t: Tuple1[A]):TupleMagnet = new TupleMagnet() {
    def apply() =  { 
      println(t._1)
      }
  }
  //This implicit conversion runs only when a Tuple2 needs conversion
  implicit def tuple2Magnet[A](t: (A, A)):TupleMagnet = new TupleMagnet() {
    def apply() = t match { 
      case (a, b) =>  println(""+a + b)
      }

  }
  //This implicit conversion runs only when a Tuple3 needs conversion
  implicit def tuple3Magnet[A](t: (A, A, A)):TupleMagnet = new TupleMagnet() {
    def apply() = t match { 
      case (a, b, c) =>  println(""+a + b + c)
      }
  }
}
/* Test Method Stub*/
object TestStub extends App{
 /* This method named `f` accepts a parameter of type `TupleMagnet` . So the compiler looks to 
  * find a suitable implicit conversion function in scope that converts a tuple to a TupleMagnet*/
  def f[T](magnet: TupleMagnet) = {
    magnet()
  }
  
  
  val i = (1, 2, 3) //Declare a Tuple of three elements
  f(i)              //call a function `f` with the tuple

  val j = (1, 2)//Declare a Tuple of two elements
  f(j)          //call a function `f` with the tuple
}
