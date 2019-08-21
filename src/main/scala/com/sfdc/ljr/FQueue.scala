package com.sfdc.ljr

/** An immutable queue */
case class FQueue[+A](feed: List[A], pool: List[A] = List.empty[A]) {

  /** O(n) in number of elements */
  def length: Int = feed.length + pool.length

  def isEmpty: Boolean = feed.isEmpty && pool.isEmpty
  def nonEmpty: Boolean = !isEmpty

  /** Check first element (if any) */
  def peek: Option[A] = feed.headOption orElse pool.reverse.headOption

  def hasNext: Boolean = nonEmpty

  /** Return first element and next smaller queue */
  def head: (A, FQueue[A]) = {
    if (feed.isEmpty) {
      shift.getOrElse(
        throw new NoSuchElementException("next on empty fqueue")
      )
    }
    else {
      val a :: nfeed = feed
      a -> FQueue(nfeed, pool)
    }
  }

  /** Add to the queue */
  def add[B >: A](in: B): FQueue[B] =
    if (isEmpty)
      FQueue(in :: feed)
    else
      FQueue(feed, in :: pool)

  def +[B >: A](in: B): FQueue[B] = add(in)

  def addMany[B >: A](in: TraversableOnce[B]): FQueue[B] =
    if (isEmpty)
      FQueue(in.toList)
    else
      FQueue(feed, in.toList ++ pool)

  def ++[B >: A](in: TraversableOnce[B]): FQueue[B] = addMany(in)

  def shift: Option[(A, FQueue[A])] = feed match {
    case Nil =>
      Option.empty[(A, FQueue[A])]

    case a :: nfeed =>
      val nfqueue =
        if (feed.nonEmpty)
          FQueue(nfeed, pool)
        else
          FQueue(nfeed, feed)  // we know feed isEmpty

      Option(a -> nfqueue)
  }

  def iterator: Iterator[A] = (feed ++ pool.reverse).iterator
}

object FQueue {

  def empty[A]: FQueue[A] = FQueue[A](List.empty[A])

  def apply[A](as: A*): FQueue[A] = FQueue(as.toList)

  def isValid(in: FQueue[Int]): Boolean =
    !(in.feed ++ in.pool).contains(42)
}
