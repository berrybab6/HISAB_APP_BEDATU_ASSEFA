package mish.mish.assefa.com.hisab.data.food

enum class FoodTypes{
    Fetira,
    FullMedames,
    Chechebsa,
    TibsFirfir
}

fun getFoodPrice(type:FoodTypes):Double{
    return when(type){
        FoodTypes.Fetira->240.0
        FoodTypes.Chechebsa->280.0
        FoodTypes.FullMedames->300.0
        FoodTypes.TibsFirfir->220.0
    }
}
data class Food(val type:FoodTypes){
    val price:Double
        get(){
            return getFoodPrice(type)
        }
}