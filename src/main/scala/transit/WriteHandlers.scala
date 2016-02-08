package transit

import java.util

import com.cognitect.transit.impl.AbstractWriteHandler
import com.cognitect.transit.{TaggedValue, TransitFactory}
import collection.immutable
import collection.JavaConverters._

object WriteHandlers {

  private[this] def unsupportedMarshalType(tag: String, a: AnyRef) = {
    new UnsupportedOperationException(s"Cannot marshal type as $tag: " + a.getClass.getSimpleName)
  }

  object SeqWriteHandler extends AbstractWriteHandler[AnyRef, TaggedValue[util.List[AnyRef]]] {
    def tag(a: AnyRef): String = a match {
      case _: immutable.Seq[_] => "list"
      case _ => throw unsupportedMarshalType("seq", a)
    }

    def rep(a: AnyRef): TaggedValue[util.List[AnyRef]] = a match {
      case s: immutable.Seq[AnyRef @unchecked] => TransitFactory.taggedValue("array", s.asJava)
      case _ => throw unsupportedMarshalType("seq", a)
    }
  }

  object SetWriteHandler extends AbstractWriteHandler[AnyRef, TaggedValue[util.Set[AnyRef]]] {

    def tag(a: AnyRef) = a match {
      case _: immutable.Set[_] => "set"
      case _ => throw unsupportedMarshalType("set", a)
    }

    def rep(a: AnyRef): TaggedValue[util.Set[AnyRef]] = a match {
      case s: immutable.Set[AnyRef @unchecked] => TransitFactory.taggedValue("array", s.asJava)
      case _ => throw unsupportedMarshalType("set", a)
    }

  }

}
