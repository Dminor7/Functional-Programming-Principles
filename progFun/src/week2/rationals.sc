class Rational(n: Int, d: Int){

  require(d != 0, "Denominator must be non-zero")

  private def gcd(a: Int, b: Int): Int = if(b == 0) a else gcd(b, a % b)

  val numer: Int = n
  val denom: Int = d

  def + (that: Rational) = {
    new Rational(
      numer * that.denom + denom * that.numer,
      denom * that.denom
    )
  }
  def - (that: Rational): Rational = this + -that
  def makeString = {
    val g = math.abs(gcd(this.numer, this.denom))
    (this.numer / g).toString + "/" + (this.denom / g).toString
  }
  def unary_- : Rational = new Rational(this.numer * -1, this.denom)
  def < (that: Rational): Boolean = numer * that.denom <  that.numer * denom
  def max(that: Rational): Rational = if(this < that) that else this
}

val x = new Rational(1, 3)
val y = new Rational(5, 7)
val z = new Rational(3, 2)


(x + y).makeString
(x - y - z).makeString
x < y
(x max y).makeString
(x max z).makeString
(-x).makeString


