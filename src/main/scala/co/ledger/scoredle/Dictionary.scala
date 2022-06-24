package co.ledger.scoredle

import scala.io.Source

case class Dictionary():
  def contains(word: Word): Boolean = ???
  def +(word: Word): Dictionary = ???


object Dictionary:
  val empty: Dictionary = Dictionary()
  def fromFile(path: String) = ???

