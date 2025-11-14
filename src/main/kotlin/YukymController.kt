package com.survivalcoding

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class YukymController {

    // nowDate의 mm 값이 이상함
    // ISO 8601 표준 yyyy-MM-dd'T'HH:mm:ss
    // 즉, MM이 아닌 mm을 써서 분이 입력된 것
    val nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    lateinit var nowTime: String

    fun getTyA(): String {
        val timeDataOne = _getTimeDataOne(nowDate)

        if (timeDataOne.isNotEmpty()) {
            // nowTime은 무조건 "갑자1국"
            nowTime = timeDataOne.first().ty1

            // nowDate의 month값이 이상해서 항상 else로 처리됨 => "갑자1국"이 리턴됨
            val month = nowDate.substring(5, 7)
            return when (month) {
                "01", "02" -> "경오1국"
                "03", "04" -> "경오2국"
                "05", "06" -> "경오3국"
                "07", "08" -> "경오4국"
                "09", "10" -> "경오5국"
                "11", "12" -> "경오6국"
                else -> nowTime
            }
        } else {
            return "경오7국"
        }
    }

    fun getTyB(): String {
        val timeDataOne = _getTimeDataOne(nowDate)
        var result = timeDataOne.first().ty12

        val nowTime = LocalDateTime.now()
        return when (nowTime.hour) {
            // 시간은 항상 0보다 크거나 같으므로 항상 "갑자1국"이 반환됨
            // 2~4, 14~16 시간대가 빠져있음
            in 0 until 2 ->  timeDataOne.first().ty1
            in 2 until 4 -> timeDataOne.first().ty2
            in 4 until 6 -> timeDataOne.first().ty3
            in 6 until 8 -> timeDataOne.first().ty4
            in 8 until 10 -> timeDataOne.first().ty5
            in 10 until 12 -> timeDataOne.first().ty6
            in 12 until 14 -> timeDataOne.first().ty7
            in 14 until 16 -> timeDataOne.first().ty8
            in 16 until 18 -> timeDataOne.first().ty9
            in 18 until 20 -> timeDataOne.first().ty10
            in 20 until 22 -> timeDataOne.first().ty11
            in 22 until 24 -> timeDataOne.first().ty12
            else -> result
        }
    }

    // 인자인 nowDate 값을 사용하지 않음
    // timeDataOne 리스트에 YukymTimeModel의 인스턴스를 25개 넣어서 리턴
    private fun _getTimeDataOne(nowDate: String): List<YukymTimeModel> {
        val timeDataOne = mutableListOf<YukymTimeModel>()
        for (i in 0..24) {
            timeDataOne.add(YukymTimeModel())
        }
        return timeDataOne
    }
}

data class YukymTimeModel(
    val ty1: String = "갑자1국",
    val ty2: String = "갑자2국",
    val ty3: String = "갑자3국",
    val ty4: String = "갑자4국",
    val ty5: String = "갑자5국",
    val ty6: String = "갑자6국",
    val ty7: String = "갑자7국",
    val ty8: String = "갑자8국",
    val ty9: String = "갑자9국",
    val ty10: String = "갑자10국",
    val ty11: String = "갑자11국",
    val ty12: String = "갑자12국"
)