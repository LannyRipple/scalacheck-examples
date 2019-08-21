import org.scalacheck._

val gen = Gen.choose(1, 10)

println(gen.sample)

val cities = List("San Francisco", "Phoenix", "Houston")
val states = List("CA", "AZ", "TX")

val genCity = Gen.oneOf(cities)
val genState = Gen.oneOf(states)

val genLocation =
  for {
    city <- genCity
    state <- Gen.oneOf(states)
  } yield
    s"$city, $state"

println(genLocation.sample)
