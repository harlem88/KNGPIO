package org.dronix.kngpio

import kotlinx.cinterop.*
import org.dronix.kngpio.gpio.Direction
import org.dronix.kngpio.gpio.Edge
import org.dronix.kngpio.gpio.Value
import platform.posix.O_RDWR
import platform.posix.open
import platform.posix.read
import platform.posix.write


class KNGPIO(gpio: Int, direction: Direction, edge: Edge?, option: String?) {
    private val GPIO_ROOT_PATH = "/sys/class/gpio/"
    private val gpioPath: String
    private val gpioValuePath: String
    private var fd : Int = -1
    init {

        //control path exist
        gpioPath = "$GPIO_ROOT_PATH/gpio$gpio"
        gpioValuePath = "$gpioPath/value"
        fd = open(gpioValuePath, O_RDWR)
        val fdDir = open("$gpioPath/direction", O_RDWR)

        if(fdDir == -1){
            println("gpio direction fd open error")
        }else {
            when (direction) {
                Direction.OUT -> write(fdDir, "out\n".wcstr, 4)
                Direction.IN  -> write(fdDir, "in\n".wcstr, 3)
            }
        }

        if(fd == -1) {
            println("gpio fd open error")
        }


    }

    public fun get(): Value? {
//        val p : CPointer<ByteVar>
//        var point : CValuesRef<ByteVar>
//        read(fd, cValuesOf(p),1)
//
        return Value.HIGH
    }


    public fun set(value: Value) {
        when (value) {
            Value.LOW -> write(fd, "0\n".wcstr,  2)
            Value.HIGH -> write(fd, "1\n".wcstr, 2)
        }
    }

    public fun watch() {
//        fd = Epoll.instance.add(gpioValuePath, epoll.EPOLLPRI, {
//            println("change!!!")
////            println(read())
//        })
    }

    public fun unwatch(){
//        if(fd != -1)Epoll.instance.remove(fd)
    }
}

