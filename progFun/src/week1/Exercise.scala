package week1

object Exercise{

  def main(args: Array[String]): Unit = {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(s"${pascal(col, row)} ")
      println()
    }
  }

  /**
   * Exercise 1
   */

  def pascal(c: Int, r: Int): Int = {
    if(c==r || c ==0) 1
    else pascal(c-1,r-1) + pascal(c,r-1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    @scala.annotation.tailrec
    def isBalanced(chars: List[Char], acc: Int): Boolean ={
      if(chars.isEmpty && acc == 0) true
      else if(chars.isEmpty && acc != 0) false
      else{
        if(chars.head == '(' && acc >= 0) isBalanced(chars.tail, acc + 1)
        else if(chars.head == ')' && acc >= 0) isBalanced(chars.tail, acc - 1)
        else isBalanced(chars.tail, acc)
      }
    }
    val lc = chars.filter(c => c == '(' || c == ')')
    isBalanced(lc,0)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {

    def countChanges(money: Int, coins: List[Int], index: Int): Int ={
      if(money == 0) 1
      else if(money < -1 || coins.size == index || index > coins.size) 0
      else{
        val withFirstIndex = countChanges(money - coins(index), coins, index)
        val withOutFirstIndex = countChanges(money,coins, index + 1)
        withFirstIndex + withOutFirstIndex
      }

    }
    countChanges(money, coins, 0)
  }
}

