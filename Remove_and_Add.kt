import java.util.Arrays

fun main() {
    repeat(int()){
        val n = int()
        val arr = Array(n+2) {0}
        val tmp = ai(n)
        for(i in 1..n){
            arr[i] = tmp[i-1]
        }
        arr[n+1] = Int.MAX_VALUE
        val cp = arr.copyOf()
        var check = -1
        for(i in 1..n){
            if(cp[i] < cp[i-1]){
                check = i-1
                break
            } else if(cp[i] == cp[i-1]){
                cp[i]++
            }
        }
        if(check == -1){
            println("YES")
            return@repeat
        }
        if(check(arr, check) || check(arr, check+1)){
            println("YES")
            return@repeat
        }
        println("NO")
    }
}

fun check(arr: Array<Int>, removed: Int): Boolean{
    val cp = arr.copyOf()
    var pre = 0
    for(i in 1 until cp.size){
        if(i == removed) continue
        if(cp[pre] > cp[i]) return false
        if(cp[pre] == cp[i]) cp[i]++
        pre = i
    }
    return true
}

fun int(): Int = readln().trim().toInt()
fun long(): Long = readln().trim().toLong()
fun double(): Double = readln().trim().toDouble()
fun str(): String = readln()
fun ai(n: Int): Array<Int>{
    return readln().trim().split(" ").map { it.toInt() }.toTypedArray()
}
fun al(n: Int): Array<Long>{
    return readln().trim().split(" ").map { it.toLong() }.toTypedArray()
}
