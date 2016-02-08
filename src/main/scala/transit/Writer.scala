package transit

import com.cognitect.transit
import scala.concurrent.{Future, ExecutionContext, blocking}

class Writer[T](underlying: transit.Writer[T])(implicit val exec: ExecutionContext) {

  def write(a: T): Future[Unit] = Future(blocking(underlying.write(a)))

}
