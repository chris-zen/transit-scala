package transit

import com.cognitect.transit
import scala.concurrent.{Future, ExecutionContext, blocking}

class Reader(underlying: transit.Reader)(implicit val exec: ExecutionContext) {

  def read[A](): Future[A] = Future(blocking(underlying.read[A]()))

}
