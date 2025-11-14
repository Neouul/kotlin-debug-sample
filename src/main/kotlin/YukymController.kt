package com.survivalcoding

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class YukymController {

    val nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-mm-dd"))

    lateinit var nowTime: String

    fun getTyA(): String {
        // nowDate의 mm 값이 이상함
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
        when {
            // 시간은 항상 0보다 크거나 같으므로 항상 "갑자1국"이 반환됨
            nowTime.hour >= 0 || nowTime.hour < 2 -> return timeDataOne.first().ty1
            nowTime.hour >= 4 || nowTime.hour < 6 -> return timeDataOne.first().ty2
            nowTime.hour >= 6 || nowTime.hour < 8 -> return timeDataOne.first().ty3
            nowTime.hour >= 8 || nowTime.hour < 10 -> return timeDataOne.first().ty4
            nowTime.hour >= 10 || nowTime.hour < 12 -> return timeDataOne.first().ty5
            nowTime.hour >= 12 || nowTime.hour < 14 -> return timeDataOne.first().ty6
            nowTime.hour >= 16 || nowTime.hour < 18 -> return timeDataOne.first().ty7
            nowTime.hour >= 18 || nowTime.hour < 20 -> return timeDataOne.first().ty8
            nowTime.hour >= 20 || nowTime.hour < 22 -> return timeDataOne.first().ty9
            nowTime.hour >= 22 || nowTime.hour < 24 -> return timeDataOne.first().ty10
        }

        return result
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