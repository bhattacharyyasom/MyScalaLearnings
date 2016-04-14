/* Implementation of Magnet Pattern*/
package examples
sealed trait TupleMagnet {
  def apply():Unit
}

object TupleMagnet{
  implicit def tuple1Magnet[A](t: Tuple1[A]):TupleMagnet = new TupleMagnet() {
    def apply() =  { 
      println(t._1)
      }
  }
  implicit def tuple2Magnet[A](t: (A, A)):TupleMagnet = new TupleMagnet() {
    def apply() = t match { 
      case (a, b) =>  println(""+a + b)
      }

  }
  implicit def tuple3Magnet[A](t: (A, A, A)):TupleMagnet = new TupleMagnet() {
    def apply() = t match { 
      case (a, b, c) =>  println(""+a + b + c)
      }
  }
}
/* Test Method Stub*/
object TestStub extends App{
  def f[T](magnet: TupleMagnet) = {
    magnet()
  }
  val i = (1, 2, 3)
  f(i)

  val j = (1, 2)
  f(j)
}
