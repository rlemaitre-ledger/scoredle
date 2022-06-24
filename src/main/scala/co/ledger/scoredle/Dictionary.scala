package co.ledger.scoredle

import scala.io.Source

case class Dictionary(private val words: List[Word]):
  def contains(word: Word): Boolean = words.contains(word)  
  def +(word: Word): Dictionary = copy(words = words :+ word)


object Dictionary:
  val empty: Dictionary = Dictionary(Nil)
  def fromFile(path: String) = 
    Source.fromInputStream(getClass.getResourceAsStream(path)).getLines().foldLeft(empty)((dic, line) => dic + line.trim())

