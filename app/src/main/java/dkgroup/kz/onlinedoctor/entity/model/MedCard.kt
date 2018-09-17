package dkgroup.kz.onlinedoctor.entity.model

import android.view.animation.AnimationUtils

class MedCard {
    var weight: Int = 0
    var height: Int = 0
    var chronicalDeseases: String = ""

    fun toMap(): Map<String, Any> {
        var map: MutableMap<String, Any> = HashMap()
        map.put("weight", weight)
        map.put("height", height)
        map.put("chronicalDeseases",chronicalDeseases)
        return map
    }

    companion object {
        fun parseObject(map: Map<String, Any>?): MedCard {
            val medCard = MedCard()
            if (map != null) {
                medCard.height = (map.get("height") as Long).toInt()
                medCard.weight = (map.get("weight") as Long).toInt()
                medCard.chronicalDeseases = map.get("chronicalDeseases") as String
            }
            return medCard
        }
    }
}
