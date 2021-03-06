import org.dronix.kngpio.KNGPIO
import org.dronix.kngpio.gpio.Direction
import org.dronix.kngpio.gpio.Value
import platform.posix.sleep

fun main(args: Array<String>) {
    var kk = KNGPIO(326, Direction.OUT, null, null)
    var kr = KNGPIO(408, Direction.IN, null, null)
    println("Hello, Native World!")
    var value = Value.HIGH
    while (true){
        kk.set(value)
        if (value == Value.HIGH) value = Value.LOW
        else value = Value.HIGH
        println("kr ${kr.get()}")

        sleep(1)
    }

}