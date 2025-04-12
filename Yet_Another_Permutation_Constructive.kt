import java.util.*
import kotlin.math.cbrt

private val que: Queue<String> = LinkedList()

fun main() {
    val t = int()
    repeat(t) {
        val (n, k) = ai(2)
        val res = mutableListOf<Int>()
        res.addAll(listOf(n-1, n))
        var min = n-1
        var cnt = 1
        for (i in 0 until k-1){
            insert(res, min-cnt until min)
            min -= cnt
            cnt *= 2
            if(min < 1){
                break
            }
        }
        for(i in min-1 downTo 1){
            res.add(0, i)
        }
        if(res.any { it < 1 }){
            println(-1)
            return@repeat
        }
        val out = StringBuilder()
        for(i in res){
            out.append(i).append(' ')
        }
        println(out)
    }
}

fun insert(res: MutableList<Int>, ran: IntRange){
    var ins = 0
    for(i in ran){
        res.add(ins+1, i)
        ins += 2
    }
}

fun int(): Int {
    if(que.isEmpty()){
        que.addAll(readln().split(" "))
    }
    return que.poll().toInt()
}

fun long(): Long {
    if(que.isEmpty()){
        que.addAll(readln().split(" "))
    }
    return que.poll().toLong()
}
fun double(): Double{
    if(que.isEmpty()){
        que.addAll(readln().split(" "))
    }
    return que.poll().toDouble()
}

fun str(): String{
    if(que.isEmpty()) que.addAll(readln().split(" "))
    return que.poll()
}

fun ai(n: Int): Array<Int>{
    val arr = Array(n){0}
    repeat(n){ arr[it] = int() }
    return arr
}
fun al(n: Int): Array<Long>{
    val arr = Array(n){0L}
    repeat(n){ arr[it] = long() }
    return arr
}
