package example

object StreamsExamples extends App{
  /*Creating Streams*/
  /*These are the building blocks of Stream*/
  val testStream = Stream.Empty
  val testStreamCons          = Stream.cons
   
  //Using cons 
  val xs:Stream[Int] = Stream.cons(1,Stream.cons(2,Stream.empty))
  
  //Saying `Stream[Int]` is the same as `Stream.Cons[Int]`
  val xs1:Stream[Int] = Stream(1,2,3)
  
  //From collections
  val xs2 = List(1,2,3).toStream
   
  def streamRange(lo:Int,hi:Int):Stream[Int]={
    if(lo < hi)  Stream.empty
    else Stream.cons(lo,streamRange(lo+1,hi))
    
  }
  /*End creating streams*/
  lazy val sampleStream = Stream(1,2,3,4,5,6,7,8,9).force
  sampleStream.tail.tail
  /*Lazy Evaluation*/
   
  /*Infinite Streams*/
  def from (n:Int):Stream[Int] = n #:: from(n+1)
  val infiniteNaturalNumbers = from(1)
  /*The same thing done to list will blow the stack and the entire list will be eval`d then and there*/
  def fromList(n:Int):List[Int] = n :: fromList(n+1)
  //val infiniteList = fromList(1)
  
  val multStream = infiniteNaturalNumbers.map { x => x *2 }
  //println(multStream(5))
  
  //infiniteNaturalNumbers.foreach { number => System.out.println(number) }
  
}