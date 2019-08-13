package com.sfdc.ljr

case class Person(name: String, age: Int, address: Address)

case class Address(street: String, city: String, state: String, zip: String)
