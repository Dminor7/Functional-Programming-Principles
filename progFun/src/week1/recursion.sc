@scala.annotation.tailrec
def factorial(n: Int, f: Int): Int = if(n == 0) f
else factorial(n - 1, f * n)

factorial(5,1)