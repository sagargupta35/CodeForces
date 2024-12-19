
fun main() {
    val t = readln().toInt()
    repeat(t){
        val n = readln().toInt()
        val s = StringBuilder(readln())
        val a = readln()
        var ops = 0
        for(i in a.indices){
            if(a[i] == '1'){
                if(i < a.length-1){
                    if(a[i+1] == '1'){
                        ops = -1
                        break
                    }
                }
                if(i < a.length-3){
                    if(a[i+3] == '1'){
                        ops = -1
                        break
                    }
                }
                if(s[i] == ')') ops++
                if(s[i+3] == '('){
                    ops++
                    s[i+3] = ')'
                }
                if(i < a.length-2){
                    if(a[i+2] == '1'){
                        if(s[i+1] == '(') ops++
                        if(s[i+2] == ')'){
                            ops++
                            s[i+2] = '('
                        }
                        continue
                    }
                }
                var m1 = 0
                var m2 = 0
                if(s[i+1] == '(') m1++
                else m2++
                if(s[i+2] == ')') m1++
                else m2++
                ops += m1.coerceAtMost(m2)
            }
        }
        println(ops)
    }
}
