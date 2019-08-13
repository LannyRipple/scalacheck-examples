package com.sfdc.ljr

import com.sfdc.ljr.syntax.generator._
import org.scalacheck.Arbitrary
import org.scalatest._
import org.scalatest.prop.{Checkers, GeneratorDrivenPropertyChecks}

class PersonSpec extends WordSpec with Matchers with Checkers with GeneratorDrivenPropertyChecks {

  // Arbitrary are wrapped Generators that when put in implicit scope are
  // used as the canonical way to generate data for Property-based testing.
  implicit val arbPerson: Arbitrary[Person] = Arbitrary(PersonGen.genPerson)

  "Address" should {
    "print" in {
      forAll(PersonGen.genAddress, MinSuccessful(2)) { address: Address =>
        println(address)
        succeed
      }
    }
  }

  "Person" should {
    "print" in {
      forAll(MinSuccessful(2)) { person: Person =>
        println(person)
        succeed
      }
    }

    "be demandable" in {
      val person01 = PersonGen.genPerson.demand(42L)
      val person02 = PersonGen.genPerson.demand()
      val person03 = PersonGen.genPerson.demand(42L)

      person01 should not be person02
      person02 should not be person03
      person01 shouldBe person03
    }
  }
}
