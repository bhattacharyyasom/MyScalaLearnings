package explorations

object BasicTypeClassExample extends App{
/*  We want to generalize a Monoid. A monoid basically is a container type that has a `zero` function and a `append` function. 
    We want to create a type class that generalizes these functions across datatypes
*/
trait Monoid[A]{
  def mappend(a:A,b:A):A
  def mzero:A
}
//Companion object for the trait
object Monoid{
  //Create a `Int` specific implementation of Monoid[A]
  implicit object IntMonoid extends Monoid[Int]{
    def mappend(a:Int,b:Int):Int = a+b
    def mzero:Int = 0
  }
  //Similarly create a `String` specific implementation
  implicit object StringMonoid extends Monoid[String]{
    def mappend(a:String,b:String):String= a.concat(b)
    def mzero:String=""
  }
}
//We could define a function `plus` that further abstracts the append operation
def plus[A](a:A,b:A)(implicit monoidApplicable:Monoid[A])=System.out.println("Plus method : "+monoidApplicable.mappend(a, b))


  //Stub area for testing

  //This line pulls out the impliict object of type `Monoid[Int]` from scope. If you remove the "implicit" from the definition of object `IntMonoid` this line would error out at compile time
  //Needless to say this gives compile time safety
  val implicitIntMonoid = implicitly[Monoid[Int]]
  System.out.println("SumOfInt : "+implicitIntMonoid.mappend(1, 2)) //prints 3

  //Lets try a sumofStrings now
  val implicitStringMonoid = implicitly[Monoid[String]]
  System.out.println("SumOfStrings : "+implicitStringMonoid.mappend("So", "m"))
  
 //Now call plus
  plus(2,3)(Monoid.IntMonoid) //If we want to pass the specific Monoid explicitly we can do this and get the job done
  plus(4,7)(implicitly[Monoid[Int]])//Or you could do this 
  
  //However if we want the compiler to secretly find out the applicable Monoid we could do this
  plus(5,13) // Note, we do not pass the specific `Monoid` here. We just declare the second parameter list of the `plus` method as `implicit`
}