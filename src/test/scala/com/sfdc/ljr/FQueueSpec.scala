package com.sfdc.ljr

import org.scalacheck.Arbitrary._
import org.scalacheck.Gen
import org.scalatest._
import org.scalatest.prop.{Checkers, GeneratorDrivenPropertyChecks}

class FQueueSpec extends WordSpec with Matchers with Checkers with GeneratorDrivenPropertyChecks {

  def empty[A]: FQueue[A] = FQueue.empty[A]

  "an FQueue" when {
    "empty" should {
      "add an element when added" in {
        forAll { in: Int =>
          val fqueue = empty[Int] + in

          fqueue.feed.nonEmpty shouldBe true
          fqueue.feed.head shouldBe in
        }
      }

      "return as many elements as added" in {
        forAll { ins: List[Int] =>
          (empty[Int] ++ ins).length shouldBe ins.length
        }
      }

      // Example of filtering the `forAll` inputs
      // Filtering can fail tests.  E.g.., `whenever(ins.contains(1234567890)`
      "return as many elements as added (using Filter)" in {
        forAll { ins: List[Int] =>

          // Without this filter iterator.size would throw Exception
          whenever(ins.nonEmpty) {
            val fqueue = empty[Int] ++ ins

            fqueue.iterator.nonEmpty shouldBe true
            fqueue.iterator.next shouldBe ins.head
          }
        }
      }
    }

    // Automatic failure minimization
    "full" should {
      "be valid" ignore {
        forAll(Gen.listOf(Gen.choose(0, 100))) { ins: List[Int] =>
            if (ins.contains(42))
              println(s"!! $ins")
            else
              println(ins)

            FQueue.isValid(empty[Int] ++ ins) shouldBe true
          }
      }
    }
  }

}
