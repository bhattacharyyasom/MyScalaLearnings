

object TraitLinearization extends App{
  
  trait WithLegs {
  def legs: String = "Base"
}

trait TwoLegged extends WithLegs {
  override def legs: String = "Two -> " + super.legs
}
trait FourLegged extends WithLegs {
  override def legs: String = "Four -> " + super.legs
}
trait SixLegged extends TwoLegged {
  override def legs: String = "Six -> " + super.legs
}

class A extends TwoLegged with FourLegged { override def legs = "A -> " + super.legs }
class B extends FourLegged with TwoLegged { override def legs = "B -> " + super.legs }
class C extends SixLegged with FourLegged with TwoLegged  { override def legs = "C -> " + super.legs }
class D extends FourLegged with SixLegged  with TwoLegged  { override def legs = "D -> " + super.legs }

Console.println(new A().legs)
new B().legs
new C().legs
new D().legs
  
}