package com.sfdc.ljr

import org.scalacheck._

object PersonGen {

  def strOfN(n: Int, g: Gen[Char]): Gen[String] =
    Gen.listOfN(n, g).map(_.mkString)

  def genPerson: Gen[Person] =
    for {
      name <- Gen.alphaStr
      age <- Gen.choose(1, 99)
      address <- genAddress
    } yield
      Person(name.capitalize, age, address)

  def genAddress: Gen[Address] =
    for {
      streetNum <- Gen.resize(4, Gen.numStr)
      streetName <- Gen.alphaStr
      city <- Gen.alphaStr
      state <- Gen.frequency(1 -> "TX", 1 -> "CA", 2 -> "NY", 9 -> "RI")
      zip <- Gen.frequency(1 -> Gen.const(""), 19 -> strOfN(5, Gen.numChar))
    } yield {
      val street =
        if (streetName.isEmpty) streetName else s"$streetNum $streetName"

      Address(street, city, state, zip)
    }

}
