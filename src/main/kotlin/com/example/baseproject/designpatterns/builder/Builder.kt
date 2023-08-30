package com.example.baseproject.designpatterns.builder

//建造者模式

class BreakfastShop {
      var food1 : Food? = null
      var food2 : Food? = null
      var food3 : Food? = null
    override fun toString(): String {
        return "[today,breakfast is {${food1?.let { it.foodName+"," } } ${food2?.let { it.foodName+"," }} ${food3?.foodName}}]"
    }
}

class Waiter {
    private val breakfastShop = BreakfastShop()

     fun order1(food: Food):Waiter{
        breakfastShop.food1 = food
        return this
    }

     fun order2(food: Food):Waiter{
        breakfastShop.food2 = food
        return this
    }

     fun order3(food: Food):Waiter{
        breakfastShop.food3 = food
        return  this
    }
    fun build ():BreakfastShop {
        return breakfastShop
    }

}

interface Food {val foodName : String}

class ChangFen() : Food {
    override val foodName: String
        get() = "ChangFen"
}

class DouJiang() : Food {
    override val foodName: String
        get() = "DouJiang"
}
class BaoZi() : Food {
    override val foodName: String
        get() = "BaoZi"
}

class Milk() : Food {
    override val foodName: String
        get() = "Milk"
}

class YuMi() : Food {
    override val foodName: String
        get() = "YuMi"
}

