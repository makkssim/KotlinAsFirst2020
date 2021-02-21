@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

import java.lang.Integer.max
import java.lang.NumberFormatException

/**
 * Класс "комплексное число".
 *
 * Общая сложность задания -- лёгкая, общая ценность в баллах -- 8.
 * Объект класса -- комплексное число вида x+yi.
 * Про принципы работы с комплексными числами см. статью Википедии "Комплексное число".
 *
 * Аргументы конструктора -- вещественная и мнимая часть числа.
 */
class Complex(var re: Double, var im: Double) {

    /**
     * Конструктор из вещественного числа
     */
    constructor(x: Double) : this(x, 0.0)

    /**
     * Конструктор из строки вида x+yi
     */
    constructor(str: String) : this(cC(str).re, cC(str).im)

    companion object ConstComplex {
        fun cC(str: String): Complex {
            var re = 0.0
            var im = 0.0
            when {
                str.matches(Regex("""-?\d+\.?\d*""")) -> re = str.toDouble()
                str.matches(Regex("""-?\d+\.?\d*i""")) -> im = str.dropLastWhile { it == 'i' }.toDouble()
                str.matches(Regex("""-?\d+\.?\d*[+-]\d*\.?\d*i""")) -> {
                    val s = max(str.lastIndexOf('+'), str.lastIndexOf('-'))
                    re = str.substring(0, s).toDouble()
                    im = str.substring(s, str.lastIndex).toDouble()
                }
                else -> throw NumberFormatException()
            }
            return Complex(re, im)
        }
    }

    /**
     * Сложение.
     */
    operator fun plus(other: Complex): Complex = Complex(this.re + other.re, this.im + other.im)


    /**
     * Смена знака (у обеих частей числа)
     */
    operator fun unaryMinus(): Complex = Complex(-this.re, -this.im)

    /**
     * Вычитание
     */
    operator fun minus(other: Complex): Complex = Complex(this.re - other.re, this.im - other.im)


    /**
     * Умножение
     */
    operator fun times(other: Complex): Complex =
        Complex(this.re * other.re - this.im * other.im, this.re * other.im + this.im * other.re)


    /**
     * Деление
     */
    operator fun div(other: Complex): Complex = Complex(
        (this.re * other.re + this.im * other.im) / (other.im * other.im + other.re * other.re),
        (this.im * other.re - this.re * other.im) / (other.im * other.im + other.re * other.re)
    )


    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean = other is Complex && this.re == other.re && this.im == other.im

    /**
     * Преобразование в строку
     */
    override fun toString(): String {
        if (this.re == 0.0 && this.im == 0.0) return "0" else {
            if (this.re == 0.0) return "${this.im}i"
            if (this.im == 0.0) return this.re.toString()
        }
        return if (this.im > 0.0) "${this.re}+${this.im}i" else "${this.re}-${-this.im}i"
    }
}
