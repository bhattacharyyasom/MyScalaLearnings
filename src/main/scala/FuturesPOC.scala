import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class FuturesPOC extends App{
  //Test Stubs here
  
  val readFile:Future[String] = readFromFile("path here")
  
  //Mapping a function that takes a string and does something
  //ReadFile returns a Future[String] . I take the possible string and count number of characters. So the end result if a Future[Int]
  val mappedOperation:Future[Int] = readFile.map { fileContentsText => fileContentsText.length }   
  
  //Here we are taking the File contents and looking up via another function that could take some time and hence is implemented as  Future
  //As you see what happens is we get a return of Future[Future[Int]]
  val MappedOperationWithNestedFuture =  readFile.map{
    fileContentsText => LookUPString(fileContentsText) 
  }
  //So we do a FlatMap on the outcome of readFile
   val FlatMapForNestedFutures =  readFile.flatMap{
    fileContentsText => LookUPString(fileContentsText) 
  }
   
  //This allows a for-comprehension to be used
   for{
     fileContents <- readFile
     number       <- LookUPString(fileContents)
   }yield(number)
   
  
  //Define some operations that take time
  def readFromFile(filePath:String):Future[String] = Future{
    //Do some file reading here
    if(filePath.equals("")) throw FileReadException("File path is empty")
    else
    "File Text"
  }
 
  def readFromDB(dbStr:String):Future[String]=Future{
    if(dbStr.equals("")) throw DBReadException("Conn String is empty")
    else
    "Records from DB"
  }
  
  def LookUPString(text:String):Future[Int]=Future{
  //Lookup logic to find instances of a string pattern repeating
    text.indexOf("DB")
   }
  
  case class DBReadException(message:String) extends Exception
  case class FileReadException(message:String) extends Exception
  
}