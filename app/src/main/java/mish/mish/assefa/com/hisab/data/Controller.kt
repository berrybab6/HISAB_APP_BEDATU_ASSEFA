package mish.mish.assefa.com.hisab.data


import mish.mish.assefa.com.hisab.data.food.Food
import mish.mish.assefa.com.hisab.data.food.FoodTypes
import mish.mish.assefa.com.hisab.data.meal.Meal
import mish.mish.assefa.com.hisab.data.meal.MealType
import mish.mish.assefa.com.hisab.framework.util.logD

class Controller(
    val foods:MutableList<Food> = mutableListOf()
,
    var meal:Meal=Meal(MealType.Breakfast),
    var peopleDiscount:Double=0.0
){
    fun addPeopleDiscount(people:Int){
        peopleDiscount=when(people){
            in 0..4->0.0
            in 5..14->0.03
            else->0.05
        }
        log()
    }
    fun addFoods(type:FoodTypes){
        val food=Food(type)
        if (food !in foods){
            foods.add(food)
        }
        log()
    }
   /* fun getFoods():food{
        return foods
    }*/
    fun removeFoods(type: FoodTypes){
        val food=Food(type)
        if (food !in foods){
            foods.remove(food)
        }
    }

    fun addMealType(type:MealType){
        meal=Meal(type)
        log()

    }

    fun totalDiscount():Double{
        val total=getTotal()
        return (meal.discount+ peopleDiscount)*total
    }
   private fun getTotal():Double{
        return foods.sumByDouble { it.price }
    }
    fun calculator():Double{
       // var totalPeople=0
        val totalPrice=getTotal()
        val totalDiscount=totalDiscount()
        return totalPrice-totalDiscount
    }



    private fun log(){
        d("Foods:$foods")
        d("Meal: $meal")
        d("People Discount: $peopleDiscount")
        //d("calculate: ${calculate()}")
    }
    private fun d(message:String){
        logD(this,message)
    }
}