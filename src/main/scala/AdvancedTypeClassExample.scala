package explorations
import BasicTypeClassExample.Monoid

object AdvancedTypeClassExample extends App{
  
  /*Since we are trying to abstract the `foldLeft` operation over multipe container types we need to create a trait with a generic type
  *parameter `F[_]`. That way the compiler can look up based on the container types ie. List etc
  */
  trait FoldLeft[F[_]]{
  def foldLeft[A](xs:F[A],b:A,f:(A,A)=>A):A
}
 object FoldLeft{
//Subclass of `FoldLeft[F[_]]` that is declared as `implicit` so the compiler finds it
implicit object FoldLeftList extends FoldLeft[List]{
  def foldLeft[A](xs:List[A],b:A,f:(A,A)=>A):A = xs.foldLeft(b)(f)
}
 
}
 //This is where the resolution for the `List` and `Int` takes place
 def sum[M[_],T](xs:M[T])(implicit monoid:Monoid[T],foldLeft:FoldLeft[M]):T = foldLeft.foldLeft(xs,monoid.mzero,monoid.mappend) 
  
  //Stub area
  //Lets say we want an API like this
  System.out.println(sum(List(2,3,4,5)))
  
  
  //Alternatively we could have also written
  //Note the type of `implicitFoldLeft`. I am writing this for clarity. The compiler refers this automatically
  //Also note the type of `implicitListType` as in the basic typeclass example
  val implicitFoldLeft:FoldLeft[List] = implicitly[FoldLeft[List]]
  val implicitListType:Monoid[Int] = implicitly[Monoid[Int]]
}