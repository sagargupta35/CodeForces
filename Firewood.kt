import kotlin.collections.HashMap

private val inp = Reader()

fun main() {
    val t = inp.int()
    repeat(t){
        val (n, k) = readln().split(" ").map { it.toLong() }
        var cur = 1L
        var idx = 0
        while (cur <= k){
            if(cur.and(k) > 0) break
            cur *= 2
            idx++
        }
        println(n-idx)
    }
}

private fun find(a: Long, b: Long, x: Long): Boolean{
    if(a == 0L) return false
    if(x <= b && b.mod(a) == x.mod(a)) return true
    return find(b.mod(a), a, x)
}

fun canBeEqual(target: IntArray, arr: IntArray): Boolean {
    val mp1 = HashMap<Int, Int>()
    for(j in target) mp1[j] = (mp1[j] ?: 0) + 1
    for(j in arr) mp1[j] = (mp1[j] ?: 0) - 1
    for((key, v) in mp1) if(v != 0) return false
    return true
}

class Reader{
    fun int(): Int = readln().toInt()
    fun long(): Long = readln().toLong()
    fun double(): Double = readln().toDouble()
    fun str(): String = readln()
    fun ai(n: Int): Array<Int>{
        return Array(n) {int()}
    }
    fun al(n: Int): Array<Long>{
        return Array(n) {long()}
    }
}



