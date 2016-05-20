package examples

trait FoldLeft[F[_]]{
  def foldLeft[A,B](xs:F[A],b:B,f:(B,A)=>B):B
}

object FoldLeft{

implicit object FoldLeftList extends FoldLeft[List]{
  def foldLeft[A,B](xs:List[A],b:B,f:(B,A)=>B):B = xs.foldLeft(b)(f)
}
  
}
trait Monoid[A]{
  def mappend(a:A,b:A):A
  def mzero:A
}
trait Identity[A]{
  val value:A
  def |+|(b:A)(implicit monoid:Monoid[A]) ={
     monoid.mappend(value, b)
     }
}
object Monoid{
implicit object IntMonoid extends Monoid[Int]{
   def mappend(a:Int,b:Int):Int = a + b
   def mzero:Int=0
 }
implicit object StringMonoid extends Monoid[String]{
  def mappend(a:String,b:String):String = a+b
  def mzero:String=""
} 
}
object Main extends App{
  val multiMonoid:Monoid[Int] = new Monoid[Int]{
    def mappend(a:Int,b:Int):Int = a * b
    def mzero:Int=1
  }
  implicit def liftValuetoIdentity[A](a:A):Identity[A]=new Identity[A]{
    val value=a
  }    
  
  def sum[M[_],T](xs:M[T])(implicit monoid:Monoid[T],foldLeft:FoldLeft[M]):T = foldLeft.foldLeft(xs,monoid.mzero,monoid.mappend)
  
  /*def plus[A](a:A,b:A)(implicit monoid:Monoid[A]):A={
    monoid.mappend(a, b)
  }*/
  //  Test Area
  //implicit val intMonoid = IntMonoid // In scope now
  //implicit val stringMonoid = StringMonoid
  Console.println(sum(List(1,2,3,4)))
  Console.println(sum(List("a","b","c","d")))
  Console.println(sum(List(1,2,3,4)))
  println(2.|+|(3))
}