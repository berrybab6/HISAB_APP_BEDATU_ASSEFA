package mish.mish.assefa.com.hisab.data.meal

enum class MealType{
    Breakfast,
    Lunch,
    Dinner
}

private fun getDiscount(meal:MealType):Double{
    return when(meal){
        MealType.Breakfast->0.05
        MealType.Lunch->0.08
        MealType.Dinner->0.04
    }
}

data class Meal(val meal:MealType){
    val discount:Double
        get() {
            return getDiscount(meal)
        }
}