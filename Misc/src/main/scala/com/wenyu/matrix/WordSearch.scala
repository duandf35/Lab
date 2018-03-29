package com.wenyu.matrix

object WordSearch {
  def exist(board: Array[Array[Char]], word: String): Boolean = {
    for (x <- board.indices; y <- board(0).indices) {
      val exist = search(board, (x, y), word)
      if (exist) {
        return exist
      }
    }

    false
  }

  private def search(board: Array[Array[Char]], crood: (Int, Int), word: String): Boolean = {
    if (word.isEmpty) {
      return true
    }

    val (x, y) = crood
    var exist = false

    if (x >= 0 && x < board.length && y >= 0 && y < board(0).length && board(x)(y) == word.charAt(0)) {

      board(x)(y) = (board(x)(y) ^ 256).toChar

      exist = search(board, (x + 1, y), word.substring(1)) ||
        search(board, (x - 1, y), word.substring(1)) ||
        search(board, (x, y + 1), word.substring(1)) ||
        search(board, (x, y - 1), word.substring(1))

      // to return to the upper level, this line must be executed which is resetting the path
      board(x)(y) = (board(x)(y) ^ 256).toChar
    }

    exist
  }

  def main(args: Array[String]): Unit = {
//    [
//      ['A','B','C','E'],
//      ['S','F','C','S'],
//      ['A','D','E','E']
//    ]

    val board = Array(Array('A', 'B', 'C', 'E'), Array('S', 'F', 'C', 'S'), Array('A', 'D', 'E', 'E'))
    val word1 = "ABCCED"
    val word2 = "SEE"
    val word3 = "ABCB"

    println(s"$word1: ${WordSearch.exist(board, word1)}")
    println(s"$word2: ${WordSearch.exist(board, word2)}")
    println(s"$word3: ${WordSearch.exist(board, word3)}")
  }
}
