package com.sfdc.ljr
package syntax

import org.scalacheck.Gen

import scala.language.implicitConversions

trait GeneratorSyntax {
  implicit final def ljrSyntaxGeneratorOps[A](in: Gen[A]): GeneratorOps[A] = new GeneratorOps(in)
}

final class GeneratorOps[A](private val gen: Gen[A]) extends AnyVal {

  /**
   * Demand a pure sample of the Generator.
   *
   * Calls pureApply with default values.  This will attempt to sample up
   * to 100 times looking for a Some[A].
   *
   * @throws Exception [[org.scalacheck.Gen.RetrievalError]] if all attempts to .sample fail
   */
  def demand(seed: Long = scala.util.Random.nextLong()): A = {
    val scSeed = org.scalacheck.rng.Seed(seed) // -vs- rng.Seed.random()
    gen.pureApply(Gen.Parameters.default, scSeed)
  }
}

