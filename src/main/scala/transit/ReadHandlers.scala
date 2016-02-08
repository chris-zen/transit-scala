package transit

import com.cognitect.transit.{ArrayReader, ArrayReadHandler}
import scala.collection.{immutable, mutable}

object ReadHandlers {

  type GestationalSet = mutable.HashSet[Any]

  object SetReadHandler extends ArrayReadHandler[GestationalSet, Set[Any], Any, Set[Any]] {
    def arrayReader() = new ArrayReader[GestationalSet, Set[Any], Any] {
      def init: GestationalSet = mutable.HashSet.empty[Any]

      def init(size: Int): GestationalSet = init

      def add(set: GestationalSet, item: Any): GestationalSet = {
        set += item
      }

      def complete(set: GestationalSet): Set[Any] = set.toSet
    }

    def fromRep(rep: Set[Any]) = rep
  }

  type GestationalSeq = mutable.ArrayBuffer[Any]

  object SeqReadHandler extends ArrayReadHandler[GestationalSeq, immutable.Seq[Any], Any, immutable.Seq[Any]] {
    def arrayReader() = new ArrayReader[GestationalSeq, immutable.Seq[Any], Any] {
      def init: GestationalSeq = mutable.ArrayBuffer.empty[Any]

      def init(size: Int): GestationalSeq = new mutable.ArrayBuffer[Any](size)

      def add(buffer: GestationalSeq, item: Any): GestationalSeq = {
        buffer += item
      }

      def complete(buffer: GestationalSeq): immutable.Seq[Any] = buffer.to[immutable.Seq]
    }

    def fromRep(rep: immutable.Seq[Any]) = rep
  }
}
